package no.czapla.sigmamod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SigmaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BLUE_STEEL.get());
        basicItem(ModItems.RAW_BLUE_STEEL.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.DURIAN.get());
        basicItem(ModItems.OIL_CAN.get());
    }
}
