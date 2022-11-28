package net.pravekypetr.wh.recipe;

import com.google.gson.JsonObject;
import net.pravekypetr.wh.WH;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BlastFurnaceRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final Ingredient one;
    private final Ingredient two;

    public BlastFurnaceRecipe(ResourceLocation id, ItemStack output, Ingredient one, Ingredient two) {
        this.id = id;
        this.output = output;
        this.one = one;
        this.two = two;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        return one.test(pContainer.getItem(1)) && two.test(pContainer.getItem(2));
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    
    public static class Type implements RecipeType<BlastFurnaceRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "blast_furnace_smelting";
    }

    public static class Serializer implements RecipeSerializer<BlastFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(WH.MOD_ID, "blast_furnace_smelting");

        @Override
        public BlastFurnaceRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            Ingredient one = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "base"));
            Ingredient two = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "add"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            return new BlastFurnaceRecipe(pRecipeId, output, one, two);
        }

        @Override
        public @Nullable BlastFurnaceRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf buf) {
            Ingredient one = Ingredient.fromNetwork(buf);
            Ingredient two = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();
            return new BlastFurnaceRecipe(pRecipeId, output, one, two);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, BlastFurnaceRecipe recipe) {
            recipe.one.toNetwork(buf);
            recipe.two.toNetwork(buf);
            buf.writeItem(recipe.output);
        }
    }

}


