package ir.ehsanmna.cookiclicker.handler;

import org.bukkit.configuration.ConfigurationSection;

import java.lang.reflect.Field;

public class CMessages {

    public String eventStart;
    public String click;
    public String noPermission;
    public String noEvent;
    public String noParticipate;



    public static void initialize(ConfigurationSection yaml, CMessages msg){
        for (String str: yaml.getKeys(false)) {
            if (yaml.isList(str)) continue;
            for (Field field : msg.getClass().getFields()) {
                field.setAccessible(true);
                try {
                    if (field.getName().equalsIgnoreCase(str)) field.set(msg, yaml.get(str));
                } catch (NullPointerException | IllegalAccessException ignored) {}
            }
        }
        CookieEndGame.lastGameMessage = yaml.getStringList("endGame");
    }
}
