public class Player extends Character {

    private boolean haveKey = false;

    public Player(String name, int health, int maxHealth, int power, int agility, int gold, int level, int exp) {
        super(name, health, maxHealth, power, agility, gold, level, exp);
    }

    public boolean pay(int price) {
        if (this.gold < price) {
            return false;
        } else {
            this.gold -= price;
            return true;
        }
    }

    public void drinkPotion() {
        this.health += 50;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    public void gotKey() {
        this.haveKey = true;
    }



    public void status() {
        System.out.print("level:" + this.level);
        System.out.print(" strength:" + this.power);
        System.out.print(" agility:" + this.agility);
        System.out.println(" gold:" + this.gold);

        System.out.println("HP[==" + this.health + "/" + this.maxHealth + "==]");
        System.out.println("EXP[==" + this.exp + "/1000==]");
    }

    public boolean isHaveKey() {
        return haveKey;
    }
}
