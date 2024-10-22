package ir.ehsanmna.cookiclicker;

import ir.ehsanmna.cookiclicker.command.CookieCommand;
import ir.ehsanmna.cookiclicker.handler.CCManager;
import ir.ehsanmna.cookiclicker.handler.CMSGHandler;
import ir.ehsanmna.cookiclicker.handler.CMessages;
import ir.ehsanmna.cookiclicker.listener.MenuListeners;
import ir.ehsanmna.cookiclicker.utils.ConfigWrapper;
import ir.ehsanmna.cookiclicker.utils.economy.EconomyManager;
import ir.ehsanmna.cookiclicker.utils.economy.EconomyType;
import ir.ehsanmna.cookiclicker.utils.placeholder.CustomPlaceholder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class CookiClicker extends JavaPlugin {
    static CookiClicker main;
    public static CMessages messages;

    @Override
    public void onEnable() {
        // Plugin startup logic
        main = this;
        saveDefaultConfig();

        Storage.setupMessages();
        Storage.setupData(getConfig());

        messages = new CMessages();
        CMessages.initialize(Storage.yamlMessages,messages);

        CMSGHandler.runTaskTimer();

        getCommand("cookie").setExecutor(new CookieCommand());
        getCommand("cookie").setTabCompleter(new CookieCommand());

        CCManager.handle(getConfig().getInt("every"));

        EconomyManager.setup(EconomyType.VAULT);

        getServer().getPluginManager().registerEvents(new MenuListeners(),this);

        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) new CustomPlaceholder().register();


        ConfigWrapper.setup();
        ConfigWrapper.wrap();

        getLogger().info("Plugin run shod!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CookiClicker getInstance() {
        return main;
    }

    public static String colorize(String msg){
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

    public static List<String> colorize(List<String> stringList){
        List<String> colorizedList = new ArrayList<>();
        for (String string : stringList) colorizedList.add(colorize(string));
        return colorizedList;
    }

    public static Component toComponent(String content) {
        return Component.empty().decoration(TextDecoration.ITALIC, false).append(MiniMessage.miniMessage().deserialize(content));
    }

    public static Component toComponent(Component content) {
        return toComponent(MiniMessage.miniMessage().serialize(content));
    }


}
