package com.lorendos.slimeboots.event;

import com.lorendos.slimeboots.SlimeBootsMod;
import com.lorendos.slimeboots.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = SlimeBootsMod.MODID)
public class SlimeFallHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {

        Player player = event.getEntity();

        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        Holder<Enchantment> slimeFall = player.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(ModEnchantments.SLIME_FALL);

        if (EnchantmentHelper.getTagEnchantmentLevel(slimeFall, boots) <= 0) {
            return;
        }

        if (player.isShiftKeyDown()) {
            player.getPersistentData().putDouble("slime_falling", 0);
            return;
        }

        player.fallDistance = 0;

        if (!player.onGround()) {
            player.getPersistentData().putDouble(
                    "slime_falling",
                    -player.getDeltaMovement().y
            );
            return;
        }

        double fall = player.getPersistentData().getDouble("slime_falling");

        if (fall <= 0.25)
            return;

        player.setDeltaMovement(
                player.getDeltaMovement().x,
                fall * 0.75,
                player.getDeltaMovement().z
        );

        player.level().playSound(
                null,
                player.blockPosition(),
                SoundEvents.SLIME_JUMP,
                SoundSource.PLAYERS,
                0.2F,
                4F
        );

        if (player.isSprinting()) {

            Vec3 look = player.getLookAngle();

            player.setDeltaMovement(
                    player.getDeltaMovement().x + look.x * fall * 0.75,
                    player.getDeltaMovement().y,
                    player.getDeltaMovement().z + look.z * fall * 0.75
            );
        }

        player.getPersistentData().putDouble("slime_falling", 0);
    }
}
