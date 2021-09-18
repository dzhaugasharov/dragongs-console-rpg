import java.util.Random;
import java.util.Scanner;

public class World {

    private Player player;
    private Merchant merchant;
    private Scanner scanner;

    public World(Player player) {
        this.player = player;
        this.merchant = new Merchant(player);
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        showMenu();
    }

    public void showMenu() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К торговцу");
        System.out.println("2. В тёмный лес");
        System.out.println("3. На выход");
        int variant = this.scanner.nextInt();
        switch (variant) {
            case 1: goToMerchant(); break;
            case 2: fight(); break;
            case 3: exitGame(); break;
            default:
                System.out.println("Вы ввели не верную команду.");
                this.showMenu();
        }
    }

    public void goToMerchant() {
        System.out.println("Торговец еще не вышел на работу");

        while (true) {
            System.out.println("Куда вы хотите пойти?");
            System.out.println("1. Вернуться в город");
            int variant = this.scanner.nextInt();
            if (variant == 1) {
                this.showMenu();
                return;
            }
            System.out.println("Вы ввели не верную команду!");
        }
    }

    public void goToForest() {
        while (true) {
            System.out.println("Куда вы хотите пойти?");
            System.out.println("1. Вернуться в город");
            System.out.println("2. Продолжить бой");
            int variant = this.scanner.nextInt();
            switch (variant) {
                case 1:
                    this.showMenu();
                    return;
                case 2:
                    this.fight();
                    return;
                default:
                    System.out.println("Вы ввели не верную команду");
            }
        }
    }

    public void exitGame() {
        System.out.println("Игра завершена");
    }

    public void fight() {
        Character monster;
        if (new Random().nextInt(2) == 1) monster = new Goblin("Goblin", 25, 25, 5, 12, 1, 200, 5);
        else monster = new Skelet("Skelet", 80, 80, 10, 4, 2, 500, 20);

        // fighting
        Battle battle = new Battle(this.player, monster);
        try {
            battle.start();
            battle.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (player.isAlive()) {
            System.out.printf("Игрок %s убил монстра %s%n", this.player.getName(), monster.getName());
            this.goToForest();
        }
        else {
            System.out.printf("Игрок %s был убит%n", player.getName());
            this.exitGame();
        }
    }
}
