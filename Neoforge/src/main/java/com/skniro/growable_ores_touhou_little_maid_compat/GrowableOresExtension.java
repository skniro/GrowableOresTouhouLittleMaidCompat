package com.skniro.growable_ores_touhou_little_maid_compat;

import com.github.tartaricacid.touhoulittlemaid.api.ILittleMaid;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(GrowableOresExtension.MOD_ID)
public class GrowableOresExtension implements ILittleMaid {
    public static final String MOD_ID = "growable_ores_touhou_little_maid_compat";
    private static final Logger LOGGER = LogUtils.getLogger();


    public GrowableOresExtension(IEventBus modEventBus) {

        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}