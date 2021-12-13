package marsh.town.brb.Mixins.Accessors;

import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ImageButton.class)
public interface TexturedButtonWidgetAccessor {
    @Accessor("resourceLocation")
    ResourceLocation getResourceLocation();
}