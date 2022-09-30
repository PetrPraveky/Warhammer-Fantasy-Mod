package net.pravekypetr.wh.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.networking.packet.C2S.stations.FluidSyncS2C;
import net.pravekypetr.wh.networking.packet.S2C.weapons.HammerSlamC2S;
import net.pravekypetr.wh.networking.packet.S2C.weapons.LongswordSlashC2S;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(WH.MOD_ID, "messages"))
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

        INSTANCE = net;

        // C2S
        net.messageBuilder(FluidSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
            .decoder(FluidSyncS2C::new)
            .encoder(FluidSyncS2C::toBytes)
            .consumerMainThread(FluidSyncS2C::handle)
            .add();


        // S2C
        net.messageBuilder(HammerSlamC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
            .decoder(HammerSlamC2S::new)
            .encoder(HammerSlamC2S::toBytes)
            .consumerMainThread(HammerSlamC2S::handle)
            .add();
            
        net.messageBuilder(LongswordSlashC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
            .decoder(LongswordSlashC2S::new)
            .encoder(LongswordSlashC2S::toBytes)
            .consumerMainThread(LongswordSlashC2S::handle)
            .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
