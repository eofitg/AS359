package net.eofitg.as359;

import net.eofitg.as359.command.ASCommand;
import net.eofitg.as359.renderer.HitBoxRenderer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = "as359",
        name = "AS359",
        version = "1.0",
        acceptedMinecraftVersions = "[1.8.9]",
        clientSideOnly = true
)
public class AS359 {
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        ClientCommandHandler.instance.registerCommand(new ASCommand());
        MinecraftForge.EVENT_BUS.register(new HitBoxRenderer());
    }
}
