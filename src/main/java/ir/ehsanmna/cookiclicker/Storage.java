package ir.ehsanmna.cookiclicker;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Storage {

    public static int randomPrice = 1000;
    public static int timer = 1;

    public static File messagesFile;
    public static YamlConfiguration yamlMessages;

    public static void setupMessages(){
        messagesFile = new File(CookiClicker.getInstance().getDataFolder(),"messages.yml");
        if (!messagesFile.exists()) CookiClicker.getInstance().saveResource("messages.yml",false);
        yamlMessages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public static void save(){
        try {yamlMessages.save(messagesFile);
        }catch (IOException e){e.printStackTrace();}
    }

    public static void setupData(ConfigurationSection section){
        randomPrice = section.getInt("randomPrice");
        timer = section.getInt("time");
    }

}
