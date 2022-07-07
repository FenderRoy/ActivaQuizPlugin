package shinyquizesplugin.shinyquizesplugin.rewards.rewardType;

public class MoneyReward implements RewardType{

    private final double amount;

    public MoneyReward(double amount){
        this.amount = amount;
    }

    @Override
    public Object get() {
        return amount;
    }
}
