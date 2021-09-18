public abstract class Character {
    final String name;
    int health, maxHealth, power, agility, gold, level, exp;

    public Character(String name, int health, int maxHealth, int power, int agility, int gold, int level, int exp) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.power = power;
        this.agility = agility;
        this.gold = gold;
        this.level = level;
        this.exp = exp;
    }

    public Character(String name, int health, int power, int skill) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.agility = skill;
    }

    public void decreaseHealth(int delta) {
        health -= delta;
        if (health < 0) health = 0;
    }

    public void increaseGold(int delta) {
        gold += delta;
    }

    public void increaseExp(int delta) {
        exp += delta;
        while (exp >= 1000) {
            exp -= 1000;
            lvlUp();
        }
    }

    public void lvlUp() {
        System.out.println("Поздравляем! Вы вышли на новый уровень!");
        level++;
        power++;
        agility++;
        maxHealth += 10;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getPower() {
        return power;
    }

    public int getAgility() {
        return agility;
    }

    public int getGold() {
        return gold;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }
}
