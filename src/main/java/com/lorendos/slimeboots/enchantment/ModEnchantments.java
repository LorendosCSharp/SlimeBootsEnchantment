package com.lorendos.slimeboots.enchantment;

import com.lorendos.slimeboots.SlimeBootsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {

    public static final ResourceKey<Enchantment> SLIME_FALL= ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(SlimeBootsMod.MODID, "slime_fall"));

    public static void bootstrap(BootstrapContext<Enchantment> context){
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, SLIME_FALL,Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                5,
                1,
                Enchantment.dynamicCost(5,7),
                Enchantment.dynamicCost(25,7),
                2,
                EquipmentSlotGroup.FEET))
        );
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder ){
        registry.register(key,builder.build(key.location()));
    }

}
