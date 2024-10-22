package ir.ehsanmna.cookiclicker.model;

import ir.ehsanmna.cookiclicker.utils.ConfigWrapper;
import org.bukkit.entity.Player;

public class MenuManager {

    public static void openMenu(Player player,String menu){
        MenuModel.getModels().get(menu).openMenu(player);
    }

    public static void refreshConfig(){
        ConfigWrapper.refreshData();
    }

    public static void createMenuFile(){
        ConfigWrapper.setup();
    }

    public static void loadMenus(){
        ConfigWrapper.wrap();
    }
}
