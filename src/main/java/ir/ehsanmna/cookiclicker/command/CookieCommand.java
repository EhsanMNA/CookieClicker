package ir.ehsanmna.cookiclicker.command;

import ir.ehsanmna.cookiclicker.CookiClicker;
import ir.ehsanmna.cookiclicker.Storage;
import ir.ehsanmna.cookiclicker.handler.CCManager;
import ir.ehsanmna.cookiclicker.handler.CMSGHandler;
import ir.ehsanmna.cookiclicker.handler.CMessages;
import ir.ehsanmna.cookiclicker.utils.ConfigWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CookieCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (sender instanceof Player player){
            if (player.hasPermission("cookieClicker.use")){
                if (args.length == 0){
                    player.sendMessage(CookiClicker.colorize("&2&m------------&a CookieClicker &2&m------------"));
                    player.sendMessage(CookiClicker.colorize("&2 → &a/cookie start &fStart a new event"));
                    player.sendMessage(CookiClicker.colorize("&2 → &a/cookie gui &fOpen the event gui"));
                    player.sendMessage(CookiClicker.colorize("&2 → &a/cookie reload &fReload the plugin config (except config.yml)"));
                }

                if (args.length == 1){
                    if (args[0].equalsIgnoreCase("start")){
                        if (player.hasPermission("cookieClicker.admin")){
                            if (CMSGHandler.isEvent){
                                player.sendMessage(CookiClicker.toComponent("<green>Event is already started!!"));
                                return true;
                            }
                            CCManager.startEvent();
                            player.sendMessage(CookiClicker.toComponent("<green>Event started!"));
                            return true;
                        }else player.sendMessage(CookiClicker.toComponent(CookiClicker.messages.noPermission));
                    }
                    else if (args[0].equalsIgnoreCase("gui")){
                        if (!CMSGHandler.isEvent){
                            player.sendMessage(CookiClicker.toComponent(CookiClicker.messages.noEvent));
                            return true;
                        }
                        CCManager.openGui(player);
                    }
                    else if (args[0].equalsIgnoreCase("reload")){
                        if (player.hasPermission("cookieClicker.admin")){
                            Storage.save();
                            CMessages.initialize(Storage.yamlMessages,CookiClicker.messages);
                            ConfigWrapper.refreshData();
                            ConfigWrapper.wrap();
                            player.sendMessage(CookiClicker.toComponent("<green>Reloaded!"));
                        }
                    }
                }

            }else player.sendMessage(CookiClicker.toComponent(CookiClicker.messages.noPermission));
        }


        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> tabs = new ArrayList<>();


        return tabs;
    }
}
