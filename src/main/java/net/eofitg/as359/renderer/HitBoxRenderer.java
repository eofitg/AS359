package net.eofitg.as359.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HitBoxRenderer {

    public static boolean ENABLED = true;
    public static boolean DEPTH = false;

    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderArmorStand(RenderLivingEvent.Post<EntityArmorStand> event) {
        if (!ENABLED) return;
        if (event.entity instanceof EntityArmorStand) {
            renderHitBox((EntityArmorStand) event.entity);
        }
    }

    private void renderHitBox(EntityArmorStand armorStand) {
        if (mc.getRenderManager() == null) return;

        double x = armorStand.posX;
        double y = armorStand.posY;
        double z = armorStand.posZ;

        AxisAlignedBB axis = armorStand.getEntityBoundingBox();
        if (axis == null) return;

        float exp = armorStand.getCollisionBorderSize();
        axis = axis.expand(exp, exp, exp);

        double renderX = x - mc.getRenderManager().viewerPosX;
        double renderY = y - mc.getRenderManager().viewerPosY;
        double renderZ = z - mc.getRenderManager().viewerPosZ;

        AxisAlignedBB renderAABB = axis.offset(-x, -y, -z).offset(renderX, renderY, renderZ);

        GlStateManager.pushMatrix();
        setupRenderingState();

        if (isMouseOver(armorStand))
            RenderGlobal.drawOutlinedBoundingBox(renderAABB, 255, 0, 0, 191);
        else
            RenderGlobal.drawOutlinedBoundingBox(renderAABB, 255, 255, 255, 191);

        restoreRenderingState();
        GlStateManager.popMatrix();
    }

    private static void setupRenderingState() {
        GlStateManager.disableTexture2D();
        if (DEPTH) GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        mc.entityRenderer.disableLightmap();
    }

    private static void restoreRenderingState() {
        mc.entityRenderer.enableLightmap();
        GlStateManager.enableLighting();
        if (DEPTH) GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    private boolean isMouseOver(EntityArmorStand armorStand) {
        if (mc.objectMouseOver == null)
            return false;
        if (mc.objectMouseOver.entityHit == null)
            return false;

        return mc.objectMouseOver.entityHit == armorStand;
    }

}