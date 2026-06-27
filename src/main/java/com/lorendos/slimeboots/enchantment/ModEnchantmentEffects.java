package com.lorendos.slimeboots.enchantment;

import com.lorendos.slimeboots.SlimeBootsMod;
import com.lorendos.slimeboots.enchantment.custom.SlimeFallEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {

    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, SlimeBootsMod.MODID);

    public static final Supplier<MapCodec <? extends EnchantmentEntityEffect>> SLIME_FALL=
            ENTITY_ENCHANTMENT_EFFECTS.register("slime_fall",()-> SlimeFallEnchantmentEffect.CODEC);

    public static void register(IEventBus eventBus){
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
