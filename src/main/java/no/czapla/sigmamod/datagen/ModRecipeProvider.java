package no.czapla.sigmamod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.block.ModBlocks;
import no.czapla.sigmamod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> BLUE_STEEL_SMELTABLES = List.of(ModItems.RAW_BLUE_STEEL,
                ModBlocks.BLUE_STEEL_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLUE_STEEL_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BLUE_STEEL.get())
                .unlockedBy("has_blue_steel", has(ModItems.BLUE_STEEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CHISEL.get())
                .pattern("  B")
                .pattern(" S ")
                .pattern("S  ")
                .define('B', ModItems.BLUE_STEEL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_blue_steel", has(ModItems.BLUE_STEEL)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLUE_STEEL.get(), 9)
                .requires(ModBlocks.BLUE_STEEL_BLOCK.get())
                .unlockedBy("has_blue_steel_block", has(ModBlocks.BLUE_STEEL_BLOCK)).save(recipeOutput);

        // if there are two recipes with the same output, you need to specify a unique name for one of them
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLUE_STEEL.get(), 18)
                .requires(ModBlocks.MAGIC_BLOCK)
                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK))
                // here we specify the unique name
                .save(recipeOutput, "czaplasigmamod:blue_steel_from_magic_block");

        oreSmelting(recipeOutput, BLUE_STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.BLUE_STEEL.get(), 0.25f, 200, "blue_steel");
        oreBlasting(recipeOutput, BLUE_STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.BLUE_STEEL.get(), 0.25f, 100, "blue_steel");
    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, SigmaMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
