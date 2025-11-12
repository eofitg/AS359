package net.eofitg.as359.command;

import net.eofitg.as359.helper.ArmorStandHelper;
import net.eofitg.as359.renderer.HitBoxRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ChatComponentText;

public class ASCommand extends CommandBase {
    private static final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getCommandName() {
        return "as";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/as toggle|depth|set|reset";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText("§eUsage: " + getCommandUsage(sender)));
            return;
        }

        String sub = args[0].toLowerCase();
        switch (sub) {
            case "toggle":
                HitBoxRenderer.ENABLED = !HitBoxRenderer.ENABLED;
                break;
            case "depth":
                HitBoxRenderer.DEPTH = !HitBoxRenderer.DEPTH;
                break;
            case "set":
                EntityArmorStand as = getMouseOverAS();
                if (as != null) {
                    String id = ArmorStandHelper.getId(as);
                    if (id != null) {
                        HitBoxRenderer.TARGET = id;
                    }
                }
                break;
            case "reset":
                HitBoxRenderer.TARGET = "";
                break;
        }
    }

    public static EntityArmorStand getMouseOverAS() {
        if (mc.thePlayer == null || mc.theWorld == null || mc.objectMouseOver == null) {
            return null;
        }
        if (mc.objectMouseOver.entityHit instanceof EntityArmorStand) {
            return (EntityArmorStand) mc.objectMouseOver.entityHit;
        }
        return null;
    }

}
