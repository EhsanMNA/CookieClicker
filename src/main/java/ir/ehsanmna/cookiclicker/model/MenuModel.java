package ir.ehsanmna.cookiclicker.model;

import ir.ehsanmna.cookiclicker.CookiClicker;
import ir.ehsanmna.cookiclicker.utils.nbt.NBTItem;
import ir.ehsanmna.cookiclicker.utils.nbt.NBTItemManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuModel {

    private static final HashMap<String,MenuModel> models = new HashMap<>();

    Inventory inv;
    String id;
    String name;
    String displayName;
    HashMap<Integer, ArrayList<MenuAction>> actions = new HashMap<>();

    public static HashMap<String, MenuModel> getModels() {
        return models;
    }

    public static void addModel(String name,MenuModel model) {
        models.put(name,model);
    }


    public MenuModel(Inventory inv, String id, String name) {
        this.inv = inv;
        this.id = id;
        this.name = name;
        this.displayName = name;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void openMenu(Player player){
        Inventory fGUI = cloneInventory(inv, CookiClicker.colorize(displayName));

        for (int i=0;i < fGUI.getSize();i++){
            try {
                ItemStack itemStack = fGUI.getItem(i);
                if (itemStack == null || itemStack.getType().equals(Material.AIR)) continue;
                ItemStack finalItem = itemStack.clone();
                ItemMeta meta = itemStack.getItemMeta();
                ItemMeta finalMeta = finalItem.getItemMeta();
                String displayName = PlaceholderAPI.setPlaceholders(player,meta.getDisplayName());
                // displayName = displayName.replace("{name}",player.getName()).replace("{balance}", EconomyManager.economy.getMoney(player)+"");
                finalMeta.setDisplayName(CookiClicker.colorize((displayName)));
                if (meta.hasLore()){
                    List<String> newLore = new ArrayList<>();
                    for (String lore : meta.getLore())
                        newLore.add(CookiClicker.colorize(PlaceholderAPI.setPlaceholders(player,lore)));
                    finalMeta.setLore(newLore);
                }
                finalItem.setItemMeta(finalMeta);
                NBTItem nbt = NBTItemManager.createNBTItem(finalItem);
                nbt.setTag("CookieMenu",true);
                nbt.setTag("MenuModel",id);
                nbt.save();
                finalItem = nbt.getItem();
                fGUI.setItem(i,finalItem);
            }catch (Exception ignored){}
        }

        player.openInventory(fGUI);
    }

    public static Inventory cloneInventory(Inventory original,String title) {
        // Create a new inventory with the same size and title
        Inventory clonedInventory = Bukkit.createInventory(null, original.getSize(), title);

        // Clone the contents of the original inventory
        ItemStack[] originalContents = original.getContents();
        ItemStack[] clonedContents = new ItemStack[originalContents.length];

        for (int i = 0; i < originalContents.length; i++) {
            if (originalContents[i] != null) {
                clonedContents[i] = originalContents[i].clone();
            }
        }

        // Set the cloned contents to the new inventory
        clonedInventory.setContents(clonedContents);

        return clonedInventory;
    }

    public void addAction(int slot, MenuAction action){
        if (!actions.containsKey(slot)){
            ArrayList<MenuAction> listOfActions = new ArrayList<>();
            listOfActions.add(action);
            actions.put(slot,listOfActions);
        }else actions.get(slot).add(action);
    }

    public boolean hasAction(int slot){
        return actions.containsKey(slot);
    }

    public ArrayList<MenuAction> getActions(int slot){
        return actions.get(slot);
    }


}
