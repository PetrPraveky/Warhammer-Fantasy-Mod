package net.pravekypetr.wh.itemInit;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import net.minecraftforge.common.ForgeMod;
import net.pravekypetr.wh.attributes.ModWeaponAttribute;
import net.pravekypetr.wh.damageSource.ModDamageSource;

public class Dagger extends TieredItem {
    public final float attackDamage;
    public final float reach;
    public final float attackSpeed;
    public final float cooldown;
    public final float damageAmplifier;

    public boolean remover;

    private boolean stab = false;

    protected static final UUID ATTACK_REACH_MODIFIER = UUID.fromString("2f8f916c-bf09-11ec-9d64-0242ac120002");

    private Multimap<Attribute, AttributeModifier> map;

    public Dagger(Tier tier, int dmg, float speed, Item.Properties properties) {
        super(tier, properties);
        this.attackDamage = (float)dmg+tier.getAttackDamageBonus();
        this.reach = -0.5f;
        this.attackSpeed = speed;
        this.cooldown = 60;
        this.damageAmplifier = 2;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        //Checking if the Forge 'Reach' Attribute is present
        if (ForgeMod.REACH_DISTANCE.isPresent()) {
            builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ATTACK_REACH_MODIFIER, "Weapon modifier", this.reach, AttributeModifier.Operation.ADDITION));
        }
        builder.put(ModWeaponAttribute.ADDITIONAL_DAMAGE.get(), new AttributeModifier(ModWeaponAttribute.ADDITIONAL_DAMAGE_MODIFIER, "Weapon modifier", this.damageAmplifier, AttributeModifier.Operation.ADDITION));
        builder.put(ModWeaponAttribute.SPEACIAL_COOLDOWN.get(), new AttributeModifier(ModWeaponAttribute.SPECIAL_COOLDOWN_MODIFIER, "Weapon modifier", this.cooldown/20, AttributeModifier.Operation.ADDITION));
        map = builder.build();
    }

    // Hover tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("wh.info.dagger").withStyle(ChatFormatting.AQUA));
        } else {
            components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(stack, level, components, flag);
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack p_43288_, BlockState p_43289_) {
        if (p_43289_.is(Blocks.COBWEB)) {
           return 15.0F;
        } else {
           Material material = p_43289_.getMaterial();
           return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && !p_43289_.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
        }
    }

    @Override
    public boolean mineBlock(ItemStack p_43282_, Level p_43283_, BlockState p_43284_, BlockPos p_43285_, LivingEntity p_43286_) {
        if (p_43284_.getDestroySpeed(p_43283_, p_43285_) != 0.0F) {
            p_43282_.hurtAndBreak(2, p_43286_, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        p_43278_.hurtAndBreak(1, p_43280_, (p_43296_) -> {
           p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? map : super.getAttributeModifiers(slot, stack);
    }

    //Different attack method
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (remover && !stab) {
            remover = false;
            return true;
        }
        
        double reach = entity.getAttributeValue(ForgeMod.REACH_DISTANCE.get());
        double reachSqr = reach * reach;
        Level world = entity.level;

        Vec3 viewVec = entity.getViewVector(1.0F);
        Vec3 eyeVec = entity.getEyePosition(1.0F);
        Vec3 targetVec = eyeVec.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);

        AABB viewBB = entity.getBoundingBox().expandTowards(viewVec.scale(reach)).inflate(4.0D, 4.0D, 4.0D);
        EntityHitResult result = ProjectileUtil.getEntityHitResult(world, entity, eyeVec, targetVec, viewBB, EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        if (result == null || !(result.getEntity() instanceof LivingEntity)) return false;

        LivingEntity target = (LivingEntity) result.getEntity();

        double distanceToTargetSqr = entity.distanceToSqr(target);

        boolean hitResult = (result != null ? target : null) != null;

        // Custom attack with lowered reach
        if (hitResult && !stab) {
            if (entity instanceof Player) {
                if (reachSqr >= distanceToTargetSqr) {
                    target.hurt(DamageSource.playerAttack((Player) entity), this.attackDamage+1);
                }
            }
        }
        if (hitResult && stab) {
            if (entity instanceof Player) {
                if (reachSqr >= distanceToTargetSqr) {
                    float i = (float)entity.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
                    i += EnchantmentHelper.getKnockbackBonus(entity);
                    System.out.println(i);
                    target.knockback(i, (double)Mth.sin(entity.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(entity.getYRot() * ((float)Math.PI / 180F))));
                    target.hurt(ModDamageSource.STABBED, this.attackDamage+1);
                    stab = false;
                }
            }
        }
        return super.onEntitySwing(stack, entity);
    }

    // Right click method
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && hand == InteractionHand.OFF_HAND && 
            player.getMainHandItem().getItem() instanceof SwordItem ||
            !level.isClientSide() && hand == InteractionHand.OFF_HAND && 
            player.getMainHandItem().getItem() instanceof Rapier
            ) {
                stab = true;
                this.onEntitySwing(player.getOffhandItem(), player);
                player.getCooldowns().addCooldown(this, (int)this.cooldown);
                player.getOffhandItem().hurtAndBreak(1, player, e -> {e.broadcastBreakEvent(EquipmentSlot.OFFHAND);});
        } else {}
        return super.use(level, player, hand);
    }
}
