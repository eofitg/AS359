package net.eofitg.as359.command;

import net.eofitg.as359.renderer.HitBoxRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;

public class ASCommand extends CommandBase {
    private static final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getCommandName() {
        return "as";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/as toggle|depth";
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
        if (sub.equals("toggle")) {
            HitBoxRenderer.ENABLED = !HitBoxRenderer.ENABLED;
        }
        else if (sub.equals("depth")) {
            HitBoxRenderer.DEPTH = !HitBoxRenderer.DEPTH;
        }
    }

    public static Entity getTargetedEntity(float range) {
        if (mc.thePlayer == null || mc.theWorld == null) {
            return null;
        }

        MovingObjectPosition objectMouseOver = mc.thePlayer.rayTrace(range, 1.0F);
        if (objectMouseOver != null &&
                objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            return objectMouseOver.entityHit;
        }

        return null;
    }
}
