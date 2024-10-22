package ir.ehsanmna.cookiclicker.model;

import ir.ehsanmna.cookiclicker.handler.CMSGHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MenuAction {
    Action act;
    String action;

    public Action getAct() {
        return act;
    }

    public void setAct(Action act) {
        this.act = act;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void run(Player player){
        switch (act) {
            case MESSAGE -> player.sendMessage(ChatColor.translateAlternateColorCodes('&',action));
            case MENU -> MenuManager.openMenu(player, action);
            case COMMAND -> Bukkit.getServer().dispatchCommand(player, action);
            case CLOSE -> player.closeInventory();
            case SOUND -> player.playSound(player.getLocation(), Sound.valueOf(action),1,1);
            case COOKIE -> CMSGHandler.addPoint(player,1);
        }
    }
    public enum Action {
        COMMAND, MESSAGE, CLOSE, MENU, CANCEL, COOKIE, SOUND
    }
}
