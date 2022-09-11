package net.pravekypetr.wh.itemInit;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.pravekypetr.wh.attributes.ModWeaponAttribute;
import net.pravekypetr.wh.networking.ModMessages;
import net.pravekypetr.wh.networking.packet.HammerSlamC2S;

public class Warhammer extends TieredItem {
    private final float attackDamage;
    private final float attackSpeed;
    private final float aoeDamage;
    private final float aoeRadius;
    // public static float aoeDamage;

    private Multimap<Attribute, AttributeModifier> map;

    public Warhammer(Tier tier, int dmg, float speed, Item.Properties properties) {
        super(tier, properties);
        this.attackDamage = (float)dmg+tier.getAttackDamageBonus();
        this.attackSpeed = speed;
        this.aoeDamage = this.attackDamage*0.6f;
        this.aoeRadius = 2;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(ModWeaponAttribute.AOE_RADIUS.get(), new AttributeModifier(ModWeaponAttribute.AOE_RADIUS_MODIFIER, "Weapon modifier", this.aoeRadius, AttributeModifier.Operation.ADDITION));
        //Checking if the Forge 'Reach' Attribute is present
        /*if (ForgeMod.REACH_DISTANCE.isPresent()) {
            builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ATTACK_REACH_MODIFIER, "Weapon modifier", this.reach, AttributeModifier.Operation.ADDITION));
        }*/
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (world.isClientSide()) {
            ModMessages.sendToServer(new HammerSlamC2S());
            /*List<Entity> entityList = world.getEntities(player, AABB.ofSize(player.position(), 4, 2, 4));
            for (Entity entity : entityList) {
                System.out.println(entity);
                LivingEntity livingEntity = (LivingEntity) entity;
                // livingEntity.hurt(ModDamageSource.HAMMERED, this.aoeDamage);
                // LivingHurtEvent(livingEntity, ModDamageSource.HAMMERED, this.aoeDamage);
            }*/
            player.getCooldowns().addCooldown(this, 40);
        }
        return super.use(world, player, hand);
    }
}
