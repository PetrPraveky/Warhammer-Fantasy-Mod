package net.pravekypetr.wh.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.pravekypetr.wh.WH;


import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;

public class BlastFurnaceRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    // private final Ingredient one;
    private final NonNullList<Ingredient> recipeItem1;
    // private final Ingredient two;

    public BlastFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItem1) {
        this.id = id;
        this.output = output;
        // this.one = one;
        this.recipeItem1 = recipeItem1;
        // this.two = two;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        System.out.println(pContainer.getItem(1));
        if (pLevel.isClientSide()) {
            return false;
        }

        return recipeItem1.get(0).test(pContainer.getItem(1))/* && two.test(pContainer.getItem(2))*/;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItem1;
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
            // Ingredient one = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "base"));
            // // Ingredient two = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "add"));
            // ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            // return new BlastFurnaceRecipe(pRecipeId, output, one);

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredient1 = GsonHelper.getAsJsonArray(pSerializedRecipe, "base");
            NonNullList<Ingredient> input1 = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < input1.size(); i++) {
                input1.set(0, Ingredient.fromJson(ingredient1.get(i)));
            }
            return new BlastFurnaceRecipe(pRecipeId, output, input1);
        }

        @Override
        public @Nullable BlastFurnaceRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf buf) {
            // Ingredient one = Ingredient.fromNetwork(pBuffer);
            // // Ingredient two = Ingredient.fromNetwork(pBuffer);
            // ItemStack output = pBuffer.readItem();
            // return new BlastFurnaceRecipe(pRecipeId, output, one);
            NonNullList<Ingredient> input1 = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < input1.size(); i++) {
                input1.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new BlastFurnaceRecipe(pRecipeId, output, input1);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, BlastFurnaceRecipe recipe) {
            // recipe.one.toNetwork(pBuffer);
            // // recipe.two.toNetwork(pBuffer);
            // pBuffer.writeItem(recipe.output);

            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }

}


