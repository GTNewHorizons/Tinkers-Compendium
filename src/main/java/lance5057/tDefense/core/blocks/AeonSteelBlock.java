package lance5057.tDefense.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AeonSteelBlock extends Block {

    public AeonSteelBlock(Material material) {
        super(material);
        setHarvestLevel("pickaxe", 2);
    }
}
