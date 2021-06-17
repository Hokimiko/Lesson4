package com.company;


import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = " ";
    public static int[] heroesHealth = {250, 250, 250, 200};
    public static int[] heroesDamage = {20, 20, 20, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental", "Medical"};
    public static Random random = new Random();

    public static void main(String[] args) {

        fightInfo();
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        changeBossDefence();
        bossHit();
        medicHit();
        heroesHit();
        fightInfo();
    }


    public static void changeBossDefence() {
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        boolean allHeroesDead = true;

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boos won!");
        }
        return allHeroesDead;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {

                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        int koef = random.nextInt(8) + 2;

        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] <= 0) {
                continue;
            } else if (bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    if (bossHealth - heroesDamage[i] * koef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth -= heroesDamage[i] * koef;
                        System.out.println("\n" + heroesAttackType[i] + " crit damage " + (heroesDamage[i] * koef));
                    }
                } else {
                    bossHealth -= heroesDamage[i];
                }

            }
        }
    }

    // Статистика боя
    public static void fightInfo() {
        System.out.println("_________________________");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]\n");

        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " Health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        /*System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Doctor health: " + heroesHealth[3]);
        System.out.println("_________________________");*/
    }

    public static void medicHit() {
        int hp = 15;
        if (heroesHealth[3] < 0) {
            heroesHealth[3] = 0;
        } else {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 3) {
                    continue;
                } else if (heroesHealth[i] < 0) {
                    continue;
                } else {
                    heroesHealth[i] += hp;
                }
            }
            System.out.println("_________________________");
            System.out.println("Medic hit: " + hp);
        }
    }


}
