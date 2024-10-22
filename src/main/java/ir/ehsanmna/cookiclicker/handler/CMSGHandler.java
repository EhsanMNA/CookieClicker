package ir.ehsanmna.cookiclicker.handler;

import ir.ehsanmna.cookiclicker.CookiClicker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;


public class CMSGHandler {

    public static boolean isEvent = false;

    public static Map<UUID,Integer> points = new HashMap<>();
    public static Map<UUID,Integer> pointsLastRound = new HashMap<>();
    public static UUID randomWinner;
    public static Integer randomPrice;

    public static void sendStartMessage(Player player){
        // Create the clickable part
        Component message = CookiClicker.toComponent(CookiClicker.messages.eventStart)
                        .append(CookiClicker.toComponent(CookiClicker.messages.click)
                        .clickEvent(ClickEvent.runCommand("/cookie gui"))
                        .hoverEvent(CookiClicker.toComponent("Click to join the event")));
        // Send the message to the player
        player.sendMessage(message);
    }

    public static void runTaskTimer(){
        new BukkitRunnable() {
            @Override
            public void run() {
                CCManager.startEvent();
                for (Player player : Bukkit.getOnlinePlayers())
                    sendStartMessage(player);
            }
        }.runTaskTimer(CookiClicker.getInstance(),20,20 * 600);
    }

    public static UUID selectRandomWinner(){
        // Convert the map's keySet to a List
        List<UUID> keys = new ArrayList<>(points.keySet());

        int min = 3000;
        int max = 10000;

        // Generate a random number between 3000 (inclusive) and 10000 (inclusive)
        Random r = new Random();
        randomPrice = r.nextInt(max - min + 1) + min;

        // If the map is empty, return null or handle as needed
        if (keys.isEmpty()) {
            CookiClicker.getInstance().getLogger().info("No player participate to select a random player!");
            return null; // or throw an exception, or return an Optional<UUID>
        }else if (keys.size() == 1) {
            randomWinner = keys.get(0);
            return randomWinner;
        }

        // Generate a random index
        Random random = new Random();
        int randomIndex = random.nextInt(keys.size());

        // Return the UUID at the random index
        randomWinner = keys.get(randomIndex);
        return randomWinner;
    }

    public static String getRandomWinnerName(){
        Player player = Bukkit.getPlayer(randomWinner);
        if (player == null || !player.isOnline()) return Bukkit.getOfflinePlayer(randomWinner).getName();
        return player.getName();
    }

    public static Player getRandomWinne(){
        return Bukkit.getPlayer(randomWinner);
    }

    public static String getRandomWinnerPrice(){
        return ""+randomPrice;
    }

    public static List<Map.Entry<UUID, Integer>> getTop5(Map<UUID, Integer> map) {
        // Convert the map entries to a list
        List<Map.Entry<UUID, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort the list by the values (Integer) in descending order
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Return the top 5 entries, or fewer if the map has less than 5 entries
        return entryList.subList(0, Math.min(5, entryList.size()));
    }

    public static Map.Entry<UUID, Integer> getNthHighest(Map<UUID, Integer> map, int n) {
        // Convert the map entries to a list
        List<Map.Entry<UUID, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort the list by the values (Integer) in descending order
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // If n is out of bounds, return null or handle as needed
        if (n <= 0 || n > entryList.size()) {
            return null; // or throw an exception
        }

        // Return the nth entry (1-based index)
        return entryList.get(n - 1);
    }
    public static Map.Entry<UUID, Integer> getNthHighest(int n) {
        // Convert the map entries to a list
        List<Map.Entry<UUID, Integer>> entryList = new ArrayList<>(points.entrySet());

        // Sort the list by the values (Integer) in descending order
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // If n is out of bounds, return null or handle as needed
        if (n <= 0 || n > entryList.size()) {
            return null; // or throw an exception
        }

        // Return the nth entry (1-based index)
        return entryList.get(n - 1);
    }

    public static void addPoint(Player player,int point){
        if (points.containsKey(player.getUniqueId()))
            points.put(player.getUniqueId(),points.get(player.getUniqueId())+point);
        else points.put(player.getUniqueId(),point);
    }

    public static int getPoints(UUID player){
        return points.getOrDefault(player,0);
    }

    public static String getNameOfPlayer(UUID id){
        Player player = Bukkit.getPlayer(id);
        if (player == null || !player.isOnline()) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(id);
            if (p == null) return "-";
            return p.getName();
        }
        return player.getName();
    }

    public static Player getPlayer(UUID id){
        Player player = Bukkit.getPlayer(id);
        if (player == null || !player.isOnline()) return null;
        return player;
    }


}
