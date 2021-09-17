public abstract class Character {
    private final String name;
    int health;
    int power;
    int skill;

    public Character(String name, int health, int power, int skill) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.skill = skill;
    }

    public void attack(Character character) {
        // TODO implement fighting logic
        character.health -= 30;
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

    public int getSkill() {
        return skill;
    }
}
