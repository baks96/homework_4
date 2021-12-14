package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = new int[]{270, 260, 250, 350, 400, 200, 450, 350};
    public static int[] heroesDamage = new int[]{15, 20, 25, 0, 10, 25, 19, 35};
    public static String[] heroesAttackType = new String[]{"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int round_number = 0;

    public Main() {
    }

    public static void main(String[] args) {
        printStatistics();

        while(!isGameFinished()) {
            round();
        }

    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void medic() {
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (i != 3 && heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                int[] var10000 = heroesHealth;
                var10000[i] += 50;
                String var10001 = heroesAttackType[i];
                System.out.println("Medic za hilil " + var10001);
                break;
            }
        }

    }

    public static void round() {
        ++round_number;
        changeBossDefence();
        bossHits();
        heroesHits();
        medic();
        Golem();
        Lucky();
        Berserk();
        Thor();
        printStatistics();
    }

    public static void Golem() {
        int dmg = bossDamage / 5;
        int colGeroi = 0;

        int[] var10000;
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (i != 4 && heroesHealth[i] > 0 && heroesHealth[4] > 0) {
                ++colGeroi;
                var10000 = heroesHealth;
                var10000[i] += dmg;
            }
        }

        var10000 = heroesHealth;
        var10000[4] -= dmg * colGeroi;
        System.out.println("Golem po1/5 hero" + dmg * colGeroi);
    }

    public static void Lucky() {
        Random random = new Random();
        boolean randomm = random.nextBoolean();
        if (heroesHealth[5] > 0 && randomm) {
            int[] var10000 = heroesHealth;
            var10000[5] += bossDamage;
            System.out.println("\nblow prick" + randomm);
        }

    }

    public static void Berserk() {
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (heroesHealth[6] > 0) {
                int[] var10000 = heroesHealth;
                var10000[6] += bossDamage / 2;
                bossHealth -= bossDamage / 2;
                System.out.println("Magic" + bossDamage / 2);
                break;
            }
        }

    }

    public static void Thor() {
        Random random = new Random();
        boolean nur = random.nextBoolean();
        if (heroesHealth[7] > 0 && nur) {
            bossDamage = 0;
            System.out.println(" ");
        } else {
            bossDamage = 50;
        }

    }

    public static void heroesHits() {
        for(int i = 0; i < heroesDamage.length; ++i) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int f = random.nextInt(8);
                    if (bossHealth - heroesDamage[i] * f < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth -= heroesDamage[i] * f;
                    }

                    System.out.println("Critical damage " + heroesDamage[i] * f);
                }
            } else if (bossHealth - heroesDamage[i] < 0) {
                bossHealth = 0;
            } else {
                bossHealth -= heroesDamage[i];
            }
        }

    }

    public static void bossHits() {
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
            }

            if (heroesHealth[i] - bossDamage < 0) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] -= bossDamage;
            }
        }

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Herous won!!!");
            return true;
        } else {
            boolean allHeroesDead = true;

            for(int i = 0; i < heroesHealth.length; ++i) {
                if (heroesHealth[i] > 0) {
                    allHeroesDead = false;
                    break;
                }
            }

            if (allHeroesDead) {
                System.out.println("Boss won");
            }

            return allHeroesDead;
        }
    }

    public static void printStatistics() {
        System.out.println(round_number + "ROUND ");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");

        for(int i = 0; i < heroesHealth.length; ++i) {
            String var10001 = heroesAttackType[i];
            System.out.println(var10001 + " health: " + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }

        System.out.println("с горем пополам");
    }
}