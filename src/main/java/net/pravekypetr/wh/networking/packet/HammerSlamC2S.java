package net.pravekypetr.wh.networking.packet;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkEvent;
import net.pravekypetr.wh.damageSource.ModDamageSource;
import net.pravekypetr.wh.itemInit.Warhammer;

public class HammerSlamC2S {
    public HammerSlamC2S() {
    }

    public HammerSlamC2S(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void fromBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            try {
                Warhammer weapon = (Warhammer)player.getMainHandItem().getItem();
                List<Entity> entityList = level.getEntities(player, AABB.ofSize(player.position(),weapon.aoeRadius*2, 2, weapon.aoeRadius*2));
                for (Entity entity : entityList) {
                    try {
                        LivingEntity livingEntity = (LivingEntity)entity;
                        // Damage entity
                        livingEntity.hurt(ModDamageSource.HAMMERED, weapon.aoeDamage);
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}
        });
        return true;
    }
}
