package cheesenull.balloonies.datagen;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.item.BallooniesItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BallooniesRecipeProvider extends FabricRecipeProvider {

    public BallooniesRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, BallooniesItems.HARPOON)
                .input('#', Items.STICK)
                .input('O', BallooniesItems.OXIDIZED_BLADE)
                .input('S', Items.STRING)
                .pattern("  O")
                .pattern(" S#")
                .pattern("SS ")
                .criterion("has_oxidized_blade", conditionsFromItem(BallooniesItems.OXIDIZED_BLADE))
                .offerTo(exporter);

    }
}
