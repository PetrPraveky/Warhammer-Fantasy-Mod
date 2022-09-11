package net.pravekypetr.wh.itemInit;

import java.util.UUID;

import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.pravekypetr.wh.networking.ModMessages;
import net.pravekypetr.wh.networking.packet.SpearAttackC2S;

public class Spear extends TieredItem {
    private final float attackDamage;
    private final float reach;
    private final float attackSpeed;

    protected static final UUID ATTACK_REACH_MODIFIER = UUID.fromString("2f8f916c-bf09-11ec-9d64-0242ac120002");

    private Multimap<Attribute, AttributeModifier> map;

    public Spear(Tier tier, int dmg, float speed, Item.Properties properties) {
        super(tier, properties);
        this.attackDamage = (float)dmg+tier.getAttackDamageBonus();
        this.reach = 3;
        this.attackSpeed = speed;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        //Checking if the Forge 'Reach' Attribute is present
        if (ForgeMod.REACH_DISTANCE.isPresent()) {
            builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ATTACK_REACH_MODIFIER, "Weapon modifier", this.reach, AttributeModifier.Operation.ADDITION));
        }
        map = builder.build();
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? map : super.getAttributeModifiers(slot, stack);
    }

    //This also works on blocks btw
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        // ModMessages.sendToServer(new SpearAttackC2S());
        double reach = entity.getAttributeValue(ForgeMod.REACH_DISTANCE.get());
        double reachSqr = reach * reach;
        Level world = entity.level;

        Vec3 viewVec = entity.getViewVector(1.0F);
        Vec3 eyeVec = entity.getEyePosition(1.0F);
        Vec3 targetVec = eyeVec.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);

        //Expanding the attacker's bounding box by the view vector's scale, and inflating it by 4.0D (x, y, z)
        AABB viewBB = entity.getBoundingBox().expandTowards(viewVec.scale(reach)).inflate(4.0D, 4.0D, 4.0D);
        EntityHitResult result = ProjectileUtil.getEntityHitResult(world, entity, eyeVec, targetVec, viewBB, EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        if (result == null || !(result.getEntity() instanceof LivingEntity)) return false;

        LivingEntity target = (LivingEntity) result.getEntity();

        double distanceToTargetSqr = entity.distanceToSqr(target);

        boolean hitResult = (result != null ? target : null) != null;

        if (hitResult) {
            if (entity instanceof Player) {
                if (reachSqr >= distanceToTargetSqr) {
                    target.hurt(DamageSource.playerAttack((Player) entity), this.attackDamage);
                    //Do stuff
                }
            }
        }
        return super.onEntitySwing(stack, entity);
    }
}
