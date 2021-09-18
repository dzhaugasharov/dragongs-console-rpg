import java.util.Random;

public class Battle extends Thread {
    Character player;
    Character monster;
    private boolean isPlayerTurn = true;

    public Battle(Character player, Character monster) {
        this.player = player;
        this.monster = monster;
    }

    @Override
    public void run() {
        while (player.isAlive() && monster.isAlive()) {
            if (isPlayerTurn) {
                attack(player, monster);
            } else {
                attack(monster, player);
            }
            isPlayerTurn = !isPlayerTurn;

            try {
                sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (player.isAlive()){
            int exp = monster.getExp();
            int maxLoot = monster.getGold();
            int loot = new Random().nextInt(maxLoot) + 5;

            player.increaseGold(loot);
            System.out.println(monster.name + " убит. (+ " + exp + " опыта, +" + loot + " золото)");
            player.increaseExp(exp);
        }
        System.out.println();
    }

    private void attack(Character attacker, Character target) {
        int attackerAgi = attacker.getAgility();
        int attackerStr = attacker.getPower();
        int targetAgi = target.getAgility();


        if (isDodge(attackerAgi, targetAgi)) {
            System.out.println(attacker.name + " промахнулся. " + target.name + " увернулся от атаки");
        } else if (isCriticalStrike(attackerStr, attackerAgi)) {
            target.decreaseHealth(attackerStr * 2);
            System.out.print(attacker.name + " нанес критическую атаку и нанес " + attackerStr * 2 + " урона. ");
            System.out.println(target.name + "[===" + target.health + "/" + target.maxHealth + "===]");
        } else {
            target.decreaseHealth(attackerStr);
            System.out.print(attacker.name + " нанес " + attackerStr + " урона. ");
            System.out.println(target.name + "[===" + target.health + "/" + target.maxHealth + "===]");

        }
    }


    private boolean isDodge(int attackerAgi, int targetAgi) {
        if (targetAgi <= attackerAgi) {
            return false;
        } else return (targetAgi - attackerAgi) * 5 > new Random().nextInt(100);
    }

    private boolean isCriticalStrike(int attackerStr, int attackerAgi) {
        return (attackerStr + attackerAgi) > new Random().nextInt(120);
    }
}
