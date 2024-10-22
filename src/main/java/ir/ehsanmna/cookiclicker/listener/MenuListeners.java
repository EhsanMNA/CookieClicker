package ir.ehsanmna.cookiclicker.listener;

import ir.ehsanmna.cookiclicker.model.MenuAction;
import ir.ehsanmna.cookiclicker.model.MenuModel;
import ir.ehsanmna.cookiclicker.utils.nbt.NBTItem;
import ir.ehsanmna.cookiclicker.utils.nbt.NBTItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListeners implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) return;
        NBTItem nbtItem = NBTItemManager.createNBTItem(event.getCurrentItem());
        if (nbtItem.hasTag("CookieMenu")) event.setCancelled(true);
        else return;
        MenuModel model = MenuModel.getModels().get(nbtItem.getString("MenuModel"));
        int slot = event.getSlot();
        if (model.hasAction(slot))
            for (MenuAction action : model.getActions(slot))
                try { action.run((Player) event.getWhoClicked());}catch (Exception ignored){}
    }

}
