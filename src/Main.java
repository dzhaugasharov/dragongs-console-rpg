import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name");
        String name = scanner.nextLine();

        Player player = new Player(name, 100,100, 10, 10, 1, 0, 10);
        World world = new World(player);
        world.startGame();
    }
}
