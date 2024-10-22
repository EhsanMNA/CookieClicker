package ir.ehsanmna.cookiclicker.utils.economy;

public class EconomyManager {

    public static EconomyType economyType;
    public static Economy economy;





    public static void setup(EconomyType type) {
        economyType = type;
        economy = new VaultEconomy();
        economy.setupEconomy();
    }


}
