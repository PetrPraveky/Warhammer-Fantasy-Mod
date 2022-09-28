package net.pravekypetr.wh.itemInit.oldWorldWeapons;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.pravekypetr.wh._ModItemQualities;
import net.pravekypetr.wh.attributes.ModWeaponAttribute;

public class Battleaxe extends TieredItem {
    public final float attackDamage;
    public final float attackSpeed;
    public final float cooldown;
    public final boolean heavy;
    public boolean remover;

    private final String quality;

    private Multimap<Attribute, AttributeModifier> map;

    public Battleaxe(Tier tier, String quality, int dmg, float speed, boolean heavy, Item.Properties properties) {
        super(tier, properties);
        this.attackDamage = (float)dmg+tier.getAttackDamageBonus();
        this.attackSpeed = speed;
        this.heavy = heavy;
        this.cooldown = 36;
        this.quality = quality;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(ModWeaponAttribute.SPEACIAL_COOLDOWN.get(), new AttributeModifier(ModWeaponAttribute.SPECIAL_COOLDOWN_MODIFIER, "Weapon modifier", this.cooldown/20, AttributeModifier.Operation.ADDITION));

        map = builder.build();
    }

    // Hover tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal(this.quality).withStyle(_ModItemQualities.getColor(this.quality)));
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("wh.info.battleaxe").withStyle(ChatFormatting.WHITE));
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

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }


    public void attack(Player player) {
        double reach = player.getAttributeValue(ForgeMod.REACH_DISTANCE.get());
        double reachSqr = reach * reach;
        Level world = player.level;

        Vec3 viewVec = player.getViewVector(1.0F);
        Vec3 eyeVec = player.getEyePosition(1.0F);
        Vec3 targetVec = eyeVec.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);

        AABB viewBB = player.getBoundingBox().expandTowards(viewVec.scale(reach)).inflate(4.0D, 4.0D, 4.0D);
        EntityHitResult result = ProjectileUtil.getEntityHitResult(world, player, eyeVec, targetVec, viewBB, EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        if (result == null || !(result.getEntity() instanceof LivingEntity)) return;

        LivingEntity target = (LivingEntity) result.getEntity();

        double distanceToTargetSqr = player.distanceToSqr(target);

        boolean hitResult = (result != null ? target : null) != null;

        // Custom attack with lowered reach
        if (hitResult) {
            if (player instanceof Player) {
                if (reachSqr >= distanceToTargetSqr) {
                    target.hurt(DamageSource.playerAttack((Player) player), this.attackDamage+1);
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // this.onEntitySwing(player.getOffhandItem(), player);

        attack(player);

        player.getCooldowns().addCooldown(this, (int)this.cooldown);
        player.getMainHandItem().hurtAndBreak(1, player, e -> {
            e.broadcastBreakEvent(EquipmentSlot.MAINHAND);
         });

        return super.use(level, player, hand);
    }
}
