package net.marshmallow.BetterRecipeBook.Mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import net.marshmallow.BetterRecipeBook.BetterRecipeBook;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.recipebook.RecipeAlternativesWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeGridAligner;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(RecipeAlternativesWidget.AlternativeButtonWidget.class)
public abstract class AlternativeButtonWidgetMixin extends ClickableWidget implements RecipeGridAligner<Ingredient> {
    private static final Identifier BACKGROUND_TEXTURE = new Identifier("betterrecipebook:textures/gui/alt_button_blank.png");

    @Final @Shadow
    private boolean craftable;
    // @Final @Shadow
    // protected List<RecipeAlternativesWidget.AlternativeButtonWidget.InputSlot> slots;
    @Final @Shadow
    private Recipe<?> recipe;

    @Shadow public abstract void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta);

    public AlternativeButtonWidgetMixin(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }


    @Inject(at = @At("HEAD"), method = "renderButton", cancellable = true)
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (!this.isHovered() && BetterRecipeBook.config.showAlternativesOnHover) {
            RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
            matrices.push();

            int i = 0;
            int j = 0;

            if (!craftable) {
                i += 26;
            }

            this.drawTexture(matrices, this.x, this.y, i, j, this.width, this.height);

            MatrixStack matrixStack = RenderSystem.getModelViewStack();
            matrixStack.push();

            matrixStack.translate(0, 0, 125.0D);
            matrixStack.translate(0, 0, 0);
            // matrixStack.push();

            MinecraftClient.getInstance().getItemRenderer().renderInGuiWithOverrides(recipe.getOutput(), this.x + 4, this.y + 4);
            matrixStack.pop();

            RenderSystem.applyModelViewMatrix();

            ci.cancel();
        }
    }
}
