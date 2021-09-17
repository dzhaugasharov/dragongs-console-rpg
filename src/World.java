import java.util.Scanner;

public class World {

    private Player player;
    private Seller seller;
    private Scanner scanner;

    public World(Player player) {
        this.player = player;
        this.seller = new Seller();
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
            case 1: goToSeller(); break;
            case 2: goToForest(); break;
            case 3: exitGame(); break;
            default:
                System.out.println("Вы ввели не верную команду.");
                this.showMenu();
        }
    }

    public void goToSeller() {
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
        // fighting
        Thread thread = new Thread(() -> {
            double v = Math.random() * 1;
            Monster ch = null;
            if (v == 1) ch = new Monster.Goblin("Goblin", 100, 30, 5);
            else ch = new Monster.Skelet("Skelet", 100, 50, 3);
            System.out.println("Монстр " + ch.getName());
            this.fight(ch);
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
                    this.goToForest();
                    return;
                default:
                    System.out.println("Вы ввели не верную команду");
            }
        }
    }

    public void exitGame() {
        System.out.println("Игра завершена");
    }

    public void fight(Monster monster) {
        while (player.getHealth() > 0 && monster.getHealth() > 0) {
            player.attack(monster);
            if (monster.getHealth() < 1) break;

            monster.attack(player);
        }
        if (monster.getHealth() <= 0) {
            System.out.println(String.format("Игрок %s убил монстра %s", this.player.getName(), monster.getName()));
        }
        else {
            System.out.println(String.format("Игрок %s был убит", player.getName()));
            this.exitGame();
        }
    }
}
