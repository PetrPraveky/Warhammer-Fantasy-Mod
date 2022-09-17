package net.pravekypetr.wh.networking.packet;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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

            List<Entity> entityList = level.getEntities(player, AABB.ofSize(player.position(), 4, 2, 4));
            // System.out.println(entityList);
            for (Entity entity : entityList) {
                try {
                    // Filter out non living entities
                    LivingEntity livingEntity = (LivingEntity)entity;
                    // Damage get
                    float damage = 1;
                    for(AttributeModifier modifier : player.getMainHandItem().getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_DAMAGE)) {
                        damage += modifier.getAmount();
                    }
                    Warhammer weapon = (Warhammer)player.getMainHandItem().getItem();
                    damage *= (1f-weapon.reduction);
                    System.out.println(damage);
                    // Damage entity
                    livingEntity.hurt(ModDamageSource.HAMMERED, damage);
                } catch (Exception e) {}
            }
        });
        return true;
    }
}
