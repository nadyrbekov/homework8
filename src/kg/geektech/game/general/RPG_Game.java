package kg.geektech.game.general;

    import kg.geektech.game.players.*;

    import java.util.Random;

public class RPG_Game {
    private static int roundNumber;
    public static Random random = new Random();

    public static void start() {
        Boss boss = new Boss(10000, 150, "Rahim");
        Warrior warrior = new Warrior(100, 10, "Tilek");
        Medic doc = new Medic(250, 5, 15, "Aziret");
        Magic magic = new Magic(280, 20, 5, "Aigerim");
        Berserk berserk = new Berserk(270, 15, "Sanjar");
        Medic junior = new Medic(260, 5, 5, "Jun");
        Thor thor = new Thor(230, 15, "Artur");
        Golem golem = new Golem(1000, 5, "Adil");
        Witcher witcher = new Witcher(500, 0, "Argen");
        Hero[] heroes = {warrior, doc, magic, berserk, junior, thor, golem, witcher,};

        printStatistics(boss, heroes);
        while (!isGameFinished(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    private static void playRound(Boss boss, Hero[] heroes) {
        if (!Thor.Thor) boss.setDamage(50);
        roundNumber++;

        boss.chooseDefence(heroes);
        boss.attack(heroes);
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0 &&
                    boss.getDefence() != heroes[i].getAbility()) {
                heroes[i].attack(boss);
                heroes[i].applySuperPower(boss, heroes);
            }
        }
        printStatistics(boss, heroes);
    }

    private static boolean isGameFinished(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    private static void printStatistics(Boss boss, Hero[] heroes) {
        System.out.println("ROUND " + roundNumber + " -----------");
        System.out.println(boss);
        for (int i = 0; i < heroes.length; i++) {
            System.out.println(heroes[i]);
        }
    }
}

