package ir.ehsanmna.cookiclicker.handler;

import ir.ehsanmna.cookiclicker.CookiClicker;
import ir.ehsanmna.cookiclicker.model.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static ir.ehsanmna.cookiclicker.Storage.timer;

public class CCManager {

    public static void startEvent(){
        CMSGHandler.isEvent = true;
        for (Player p : Bukkit.getOnlinePlayers())
            CMSGHandler.sendStartMessage(p);
        handleGame(timer);
    }

    public static void openGui(Player player){
        MenuManager.openMenu(player,"Cookie");
    }

    public static void endEvent(){
        CookiClicker.getInstance().getLogger().info("Cookie clicker event has been end!");
        if (CMSGHandler.points.isEmpty()){
            Bukkit.broadcast(CookiClicker.toComponent(CookiClicker.messages.noParticipate));
            CMSGHandler.isEvent = false;
            return;
        }
        CMSGHandler.selectRandomWinner();

        CMSGHandler.isEvent = false;
        CookieEndGame.givePlayersRewards();

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.closeInventory();
            CookieEndGame.sendEndMessage(p);
        }

        CookieEndGame.handlePoints();
    }

    public static void handle(int time){
        new BukkitRunnable() {
            @Override
            public void run() {
                CookiClicker.getInstance().getLogger().info("Cookie clicker event has been started! [method=runnable]");
                startEvent();
            }
        }.runTaskTimer(CookiClicker.getInstance(),0, 20L *60*time);
    }

    public static void handleGame(int time){
        new BukkitRunnable() {
            @Override
            public void run() {endEvent();}
        }.runTaskLater(CookiClicker.getInstance(),20L*60*time);
    }

}
