package net.pravekypetr.wh.itemInit;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import net.pravekypetr.wh.attributes.ModWeaponAttribute;
import net.pravekypetr.wh.networking.ModMessages;
import net.pravekypetr.wh.networking.packet.LongswordSlashC2S;

public class Longsword extends TieredItem {
    public final float attackDamage;
    public final float attackSpeed;
    public final float cooldown;
    public final float aoeDamage;
    public final float aoeRadius;

    public boolean remover;

    protected static final UUID ATTACK_REACH_MODIFIER = UUID.fromString("2f8f916c-bf09-11ec-9d64-0242ac120002");

    private Multimap<Attribute, AttributeModifier> map;

    public Longsword(Tier tier, int dmg, float speed, Item.Properties properties) {
        super(tier, properties);
        this.attackDamage = (float)dmg+tier.getAttackDamageBonus();
        this.attackSpeed = speed;
        this.cooldown = 20;
        this.aoeDamage = 0.6f*(this.attackDamage+1);
        this.aoeRadius = 4;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(ModWeaponAttribute.AOE_RADIUS.get(), new AttributeModifier(ModWeaponAttribute.AOE_RADIUS_MODIFIER, "Weapon modifier", this.aoeRadius, AttributeModifier.Operation.ADDITION));
        builder.put(ModWeaponAttribute.AOE_DAMAGE.get(), new AttributeModifier(ModWeaponAttribute.AOE_DAMAGE_MODIFIER, "Weapon modifier", this.aoeDamage, AttributeModifier.Operation.ADDITION));
        builder.put(ModWeaponAttribute.SPEACIAL_COOLDOWN.get(), new AttributeModifier(ModWeaponAttribute.SPECIAL_COOLDOWN_MODIFIER, "Weapon modifier", this.cooldown/20, AttributeModifier.Operation.ADDITION));
        map = builder.build();
    }

    // Hover tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("wh.info.longsword").withStyle(ChatFormatting.AQUA));
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide()) {
            ModMessages.sendToServer(new LongswordSlashC2S());
            world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
            player.sweepAttack();
            player.getCooldowns().addCooldown(this, (int)this.cooldown);
        }
        return super.use(world, player, hand);
    }
}
