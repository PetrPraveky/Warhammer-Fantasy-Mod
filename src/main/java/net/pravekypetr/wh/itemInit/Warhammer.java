package net.pravekypetr.wh.itemInit;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.pravekypetr.wh.attributes.ModWeaponAttribute;
import net.pravekypetr.wh.networking.ModMessages;
import net.pravekypetr.wh.networking.packet.HammerSlamC2S;

public class Warhammer extends TieredItem {
    public final float attackDamage;
    public final float attackSpeed;
    public final float aoeDamage;
    public final float aoeRadius;
    public final float cooldown;
    public final float reduction;
    // public static float aoeDamage;

    private Multimap<Attribute, AttributeModifier> map;

    public Warhammer(Tier tier, int dmg, float speed, Item.Properties properties) {
        super(tier, properties);
        this.attackDamage = (float)dmg+tier.getAttackDamageBonus();
        this.attackSpeed = speed;
        this.aoeRadius = 2;
        this.cooldown = 40;
        this.reduction = 0.4f;
        this.aoeDamage = (this.attackDamage+1)*(1f-this.reduction);

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(ModWeaponAttribute.AOE_RADIUS.get(), new AttributeModifier(ModWeaponAttribute.AOE_RADIUS_MODIFIER, "Weapon modifier", this.aoeRadius, AttributeModifier.Operation.ADDITION));
        builder.put(ModWeaponAttribute.AOE_DAMAGE.get(), new AttributeModifier(ModWeaponAttribute.AOE_DAMAGE_MODIFIER, "Weapon modifier", this.aoeDamage, AttributeModifier.Operation.ADDITION));
        builder.put(ModWeaponAttribute.SPEACIAL_COOLDOWN.get(), new AttributeModifier(ModWeaponAttribute.SPECIAL_COOLDOWN_MODIFIER, "Weapon modifier", this.cooldown/20, AttributeModifier.Operation.ADDITION));
        //Checking if the Forge 'Reach' Attribute is present
        map = builder.build();
    }

    // Hover tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("wh.warhammer.info").withStyle(ChatFormatting.AQUA));
        } else {
            components.add(Component.translatable("wh.info").withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(stack, level, components, flag);
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
     }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? map : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide()) {
            ModMessages.sendToServer(new HammerSlamC2S());
            player.getCooldowns().addCooldown(this, (int)this.cooldown);
        }
        return super.use(world, player, hand);
    }
}
