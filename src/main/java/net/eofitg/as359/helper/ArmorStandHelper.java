package net.eofitg.as359.helper;

import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ArmorStandHelper {

    public static String getId(EntityArmorStand as) {
        ItemStack headItem = as.getCurrentArmor(3);
        if (headItem == null || headItem.getItem() != Items.skull || headItem.getItem().getMetadata(headItem) != 3) return null;

        NBTTagCompound tagCompound = headItem.getTagCompound();
        if (tagCompound == null) return null;

        String ownerId = tagCompound.getCompoundTag("SkullOwner").getString("Id");
        if (ownerId == null || ownerId.isEmpty()) return null;

        return ownerId;
    }

}
