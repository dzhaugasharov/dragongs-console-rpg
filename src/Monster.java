public class Monster extends Character {

    public Monster(String name, int health, int power, int skill) {
        super(name, health, power, skill);
    }

    public static class Skelet extends Monster {

        public Skelet(String name, int health, int power, int skill) {
            super(name, health, power, skill);
        }
    }

    public static class Goblin extends Monster {

        public Goblin(String name, int health, int power, int skill) {
            super(name, health, power, skill);
        }
    }

}
