package ir.ehsanmna.cookiclicker.handler;

import ir.ehsanmna.cookiclicker.CookiClicker;
import ir.ehsanmna.cookiclicker.utils.economy.EconomyManager;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static ir.ehsanmna.cookiclicker.handler.CMSGHandler.*;

public class CookieEndGame {

    static List<String> lastGameMessage = new ArrayList<>();

    public static void sendEndMessage(Player player){
        for (String msg : lastGameMessage){
            Component message = Component.text(PlaceholderAPI.setPlaceholders(player,CookiClicker.colorize(msg.concat(""))));
            player.sendMessage(message);
        }
    }

    public static void givePlayersRewards(){
        givePrice(CMSGHandler.getRandomWinne(),CMSGHandler.randomPrice);

        Integer totalPrice = 0;
        for (UUID uuid : points.keySet())
            totalPrice += points.get(uuid);
        totalPrice = (int) (totalPrice*1.5f);


        givePrice(CMSGHandler.getPlayer(getTop5(points).get(0).getKey()), (int) (totalPrice*0.6f));
        if (getTop5(points).size() >= 2) givePrice(CMSGHandler.getPlayer(getTop5(points).get(1).getKey()), (int) (totalPrice*0.2f));
        if (getTop5(points).size() >= 3) givePrice(CMSGHandler.getPlayer(getTop5(points).get(2).getKey()), (int) (totalPrice*0.1f));
        if (getTop5(points).size() >= 4) givePrice(CMSGHandler.getPlayer(getTop5(points).get(3).getKey()), (int) (totalPrice*0.07f));
        if (getTop5(points).size() >= 5) givePrice(CMSGHandler.getPlayer(getTop5(points).get(4).getKey()), (int) (totalPrice*0.03f));
        //for (UUID uuid : CMSGHandler.points.keySet())
        //    givePrice(CMSGHandler.getPlayer(uuid), CMSGHandler.points.get(uuid));
    }

    public static void givePrice(Player player,int money){
        EconomyManager.economy.addMoney(player,money);
    }

    public static void handlePoints(){
        pointsLastRound = new HashMap<>(points);
        points.clear();
    }


}
