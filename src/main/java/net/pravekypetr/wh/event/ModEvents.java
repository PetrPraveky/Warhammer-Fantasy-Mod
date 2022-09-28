package net.pravekypetr.wh.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.AirBlock;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.SkavenBlastFurnaceBlock;
import net.pravekypetr.wh.damageSource.ModDamageSource;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Dagger;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Halbert;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Rapier;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Spear;

@Mod.EventBusSubscriber(modid = WH.MOD_ID)
public class ModEvents {
    // Redone attack entity event for items with larger or smaller range
    @SubscribeEvent
    public static void attackEntity(AttackEntityEvent event) {
        ItemStack heldItem = event.getEntity().getMainHandItem();
        if (heldItem.getItem() instanceof Spear || 
            heldItem.getItem() instanceof Dagger || 
            heldItem.getItem() instanceof Halbert) {
            event.setCanceled(true);
        }
    }
    // Redone damage taken by entity for offhand dagger and sword item combo
    @SubscribeEvent
    public static void hurtEntity(LivingHurtEvent event) {
        try {
            Player player = (Player)event.getSource().getEntity();
            if (player.getOffhandItem().getItem() instanceof Dagger) {
                if (player.getMainHandItem().getItem() instanceof SwordItem || player.getMainHandItem().getItem() instanceof Rapier) {
                    LivingEntity entity = (LivingEntity)event.getEntity();
                    Dagger weapon = (Dagger)player.getOffhandItem().getItem();
                    float mainDamage = 1;
                    for(AttributeModifier modifier : player.getMainHandItem().getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_DAMAGE)) {
                        mainDamage += modifier.getAmount();
                    }
                    float damage = weapon.damageAmplifier+mainDamage;
                    entity.hurt(ModDamageSource.STABBED, damage);
                }
            }
        } catch (Exception e) {}

        try {
            LivingEntity entity = (LivingEntity)event.getSource().getEntity();
            if (entity.getMainHandItem().getItem() instanceof Rapier) {
                LivingEntity target = (LivingEntity)event.getEntity();
                Rapier weapon = (Rapier)target.getMainHandItem().getItem();
                float damage = 1+weapon.attackDamage;
                entity.hurt(ModDamageSource.RAPIER, damage);
            }
        } catch (Exception e) {}
    }

    @SubscribeEvent
    public static void rightClickOnEntity(PlayerInteractEvent event) {
        try {
            Player player = (Player)event.getEntity();
            // Debuging some shit
            if (player.getMainHandItem().getItem() instanceof Halbert) {
                Halbert weapon = (Halbert)player.getMainHandItem().getItem();
                weapon.remover = true;
            }
            if (player.getMainHandItem().getItem() instanceof Spear) {
                Spear weapon = (Spear)player.getMainHandItem().getItem();
                weapon.remover = true;
            }
            if (player.getMainHandItem().getItem() instanceof Dagger) {
                Dagger weapon = (Dagger)player.getMainHandItem().getItem();
                weapon.remover = true;
            }
        } catch (Exception e) {}
    }

    @SubscribeEvent
    public static void placeBlockEvent(EntityPlaceEvent event) {
        if (event.getPlacedBlock().getBlock() instanceof SkavenBlastFurnaceBlock) {
            if ((event.getLevel().getBlockState(event.getPos().above()).getBlock() instanceof AirBlock) == false) {
                event.setCanceled(true);
                try {
                    if (((Player)event.getEntity()).isCreative() == false) {
                        System.out.println(((Player)event.getEntity()).getMainHandItem().getCount());
                        ((Player)event.getEntity()).setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(event.getPlacedBlock().getBlock(), ((Player)event.getEntity()).getMainHandItem().getCount()+0));
                    }
                } catch (Exception e) {}
            }
        }
    }
}
