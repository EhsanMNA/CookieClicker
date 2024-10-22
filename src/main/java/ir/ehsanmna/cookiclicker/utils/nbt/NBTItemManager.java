package ir.ehsanmna.cookiclicker.utils.nbt;

import org.bukkit.inventory.ItemStack;

public class NBTItemManager {

    public static String nbtSystem = "madeIn";

    public static NBTItem createNBTItem(ItemStack item){
        return getNewItem(item);
    }

    static NBTItem getNewItem(ItemStack item){
        if (nbtSystem.equalsIgnoreCase("madeIn"))
            return new NBTReflection(item);

        return new NBTReflection(item);
    }

}
