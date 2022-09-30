package net.pravekypetr.wh.networking.packet.S2C.weapons;

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
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Warhammer;
import net.minecraft.world.entity.EquipmentSlot;

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
                        float force;
                        if (weapon.heavy) {
                            force = 0.5f;
                        } else {
                            force = 0.35f;
                        }
                        livingEntity.knockback((double)(force), (double)(player.position().x-livingEntity.position().x), (double)(player.position().y-livingEntity.position().y));
                        livingEntity.hurt(ModDamageSource.HAMMERED, weapon.aoeDamage);
                        player.getMainHandItem().hurtAndBreak(1, player, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                        
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}
        });
        return true;
    }
}
