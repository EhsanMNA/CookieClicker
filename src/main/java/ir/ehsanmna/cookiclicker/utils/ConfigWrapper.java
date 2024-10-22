package ir.ehsanmna.cookiclicker.utils;

import ir.ehsanmna.cookiclicker.CookiClicker;
import ir.ehsanmna.cookiclicker.model.MenuAction;
import ir.ehsanmna.cookiclicker.model.MenuModel;
import ir.ehsanmna.cookiclicker.utils.nbt.NBTItem;
import ir.ehsanmna.cookiclicker.utils.nbt.NBTItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigWrapper {

    static YamlConfiguration yamlConfiguration;
    static File file;

    public static void setup(){
        CookiClicker main = CookiClicker.getInstance();
        file = new File(main.getDataFolder(),"menus.yml");
        try {
            if (file.createNewFile())
                main.saveResource("menus.yml",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }
    public static void wrap(){
            for (String menuName : yamlConfiguration.getKeys(false)){
                ConfigurationSection menuSection = yamlConfiguration.getConfigurationSection(menuName);
                assert menuSection != null;
                Inventory gui = Bukkit.createInventory(null,menuSection.getInt("size"), CookiClicker.colorize(menuSection.getString("menu_title")));
                MenuModel menuModel = new MenuModel(gui,menuName,menuName);
                for (String itemId : menuSection.getConfigurationSection("items").getKeys(false)){
                    ConfigurationSection itemSection = menuSection.getConfigurationSection("items."+itemId);
                    ItemStack itemStack = new ItemStack(Material.valueOf(itemSection.getString("material").toUpperCase()));
                    if (itemSection.contains("data")) itemStack.setDurability((short) itemSection.getInt("data"));
                    boolean isListOfSlots = false;
                    List<Integer> slots = new ArrayList<>();
                    int slot = 0;
                    if (itemSection.contains("slot"))
                        slot = itemSection.getInt("slot");
                    else {
                        slots = itemSection.getIntegerList("slots");
                        isListOfSlots = true;
                    }
                    String name = CookiClicker.colorize(itemSection.getString("display_name"));
                    List<String> lore = CookiClicker.colorize(itemSection.getStringList("lore"));
                    if (itemSection.contains("actions")){
                        List<String> actions = itemSection.getStringList("actions");
                        for (String act : actions){
                            MenuAction menuAction = new MenuAction();
                            String actionEnumId = act.split(" ")[0];
                            menuAction.setAction(act.replaceAll(actionEnumId+" ",""));
                            menuAction.setAct(MenuAction.Action.valueOf(actionEnumId));
                            if (isListOfSlots) for (Integer i : slots) menuModel.addAction(i,menuAction);
                            else menuModel.addAction(slot,menuAction);
                        }
                    }
                    ItemMeta meta = itemStack.getItemMeta();
                    meta.setDisplayName(name);
                    meta.setLore(lore);
                    itemStack.setItemMeta(meta);
                    NBTItem nbtItem = NBTItemManager.createNBTItem(itemStack);
                    nbtItem.setTag("CookieMenu",true);
                    nbtItem.setTag("MenuModel",menuName);
                    nbtItem.save();
                    itemStack = nbtItem.getItem();
                    if (isListOfSlots) for (Integer i : slots) gui.setItem(i,itemStack);
                    else gui.setItem(slot,itemStack);
                }
                menuModel.setInv(gui);
                MenuModel.addModel(menuName,menuModel);
            }
    }

    public static void refreshData() {
        try {
            yamlConfiguration.save(file);
        }catch (IOException ignored){}

    }



}
