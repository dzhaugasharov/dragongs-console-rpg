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
        System.out.println("3. Показать статус");
        if (player.isHaveKey()) System.out.println("4. На завод драконов");

        switch (scanner.nextInt()) {
            case 1:
                goToMerchant();
                break;
            case 2:
                this.fight(false);
                break;
            case 3:
                this.player.status();
                this.showMenu();
                break;
            case 4:
                if (this.player.isHaveKey()) {
                    this.fight(true);
                    break;
                }
            default:
                System.out.println("Вы ввели не верную команду.");
                this.showMenu();
        }
    }

    public void goToMerchant() {
        System.out.println("1. Купить здоровье (5 золота)");
        System.out.println("2. Купить ключь от завода дракона (100 золота)");
        System.out.println("3. Вернуться в город");
        System.out.println("4. Показать статус");

        switch (this.scanner.nextInt()) {
            case 1:
                if (!merchant.buyHpPotion(5)) {
                    System.out.println("Не достаточно золота");
                } else {
                    System.out.println("Вы купили здоровье");
                }
                this.goToMerchant();
                break;
            case 2:
                if (merchant.buyKey(100)) {
                    System.out.println("Теперь вы можете попасть на завод даконов.");
                } else {
                    System.out.println("Не достаточно золота");
                }
                this.goToMerchant();
                break;
            case 3:
                this.showMenu();
                break;
            case 4:
                player.status();
                this.goToMerchant();
                break;
            default:
                System.out.println("Вы ввели не верную команду!");
                this.goToMerchant();
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
                    this.fight(false);
                    return;
                default:
                    System.out.println("Вы ввели не верную команду");
            }
        }
    }

    public void goToDragonsFactory() {
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
                    this.fight(true);
                    return;
                default:
                    System.out.println("Вы ввели не верную команду");
            }
        }
    }

    public void exitGame() {
        System.out.println("Игра завершена");
    }

    public void fight(boolean withDragon) {
        Character monster;
        if (withDragon) {
            monster = new Dragon();
        }
        else {
            if (new Random().nextInt(2) == 1) monster = new Goblin("Goblin", 25, 25, 5, 12, 1, 200, 5);
            else monster = new Skelet("Skelet", 80, 80, 10, 4, 2, 500, 20);
        }

        System.out.printf("Бой с %s%n", monster.getName());

        // fighting
        Battle battle = new Battle(this.player, monster);
        try {
            battle.start();
            battle.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (player.isAlive()) {
            System.out.printf("%s был убит%n", monster.getName());
            if (withDragon) goToDragonsFactory();
            else this.goToForest();
        } else {
            System.out.printf("Игрок %s был убит%n", player.getName());
            this.exitGame();
        }
    }
}
