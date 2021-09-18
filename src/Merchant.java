public class Merchant {
    Player player;

    public Merchant(Player player) {
        this.player = player;
    }

    public boolean buyHpPotion(int price) {
        if (player.pay(price)) {
            player.drinkPotion();
            return true;
        } else {
            return false;
        }
    }

    public boolean buyKey(int price) {
        if (player.pay(price)) {
            player.gotKey();
            return true;
        } else {
            return false;
        }
    }
}
