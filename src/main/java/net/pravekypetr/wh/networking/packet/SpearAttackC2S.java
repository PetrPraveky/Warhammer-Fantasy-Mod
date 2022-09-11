package net.pravekypetr.wh.networking.packet;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.network.NetworkEvent;
import net.pravekypetr.wh.damageSource.ModDamageSource;

public class SpearAttackC2S {
    public SpearAttackC2S() {
    }

    public SpearAttackC2S(FriendlyByteBuf buf) {
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


            float damage = 1;
            for(AttributeModifier modifier : player.getMainHandItem().getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_DAMAGE)) {
                damage += modifier.getAmount();
            }
            float reach = 0;
            for(AttributeModifier modifier : player.getMainHandItem().getAttributeModifiers(EquipmentSlot.MAINHAND).get(ForgeMod.REACH_DISTANCE.get())) {
                reach += modifier.getAmount();
            }
            
            Vec3 viewVec = player.getViewVector(1.0F);
            Vec3 eyeVec = player.getEyePosition(1.0F);
            Vec3 targetVec = eyeVec.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);

            AABB viewBB = player.getBoundingBox().expandTowards(viewVec.scale(reach)).inflate(4.0D, 4.0D, 4.0D);
            EntityHitResult result = ProjectileUtil.getEntityHitResult(level, player, eyeVec, targetVec, viewBB, EntitySelector.NO_CREATIVE_OR_SPECTATOR);

            System.out.println(result);

            if (result == null || !(result.getEntity() instanceof LivingEntity)) return;

            // List<Entity> entityList = level.getEntities(player, AABB.ofSize(player.position(), 4, 2, 4));
            // // System.out.println(entityList);
            // for (Entity entity : entityList) {
            //     try {
            //         // Filter out non living entities
            //         LivingEntity livingEntity = (LivingEntity)entity;
            //         // Damage get
            //         damage *= 0.6f;
            //         // Damage entity
            //         livingEntity.hurt(ModDamageSource.HAMMERED, damage);
            //     } catch (Exception e) {}
            // }
        });
        return true;
    }
}
