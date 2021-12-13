package marsh.town.brb.Mixins.Accessors;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PotionBrewing.Mix.class)
public interface BrewingRecipeRegistryMixAccessor<T> {
    @Accessor("from")
    T getFrom();
    @Accessor("to")
    T getTo();
    @Accessor("ingredient")
    Ingredient getIngredient();
}