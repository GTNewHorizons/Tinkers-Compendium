package lance5057.tDefense.baubles.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lance5057.tDefense.baubles.tileentities.TileEntity_JewelersBench;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class JewelersBench extends Block implements ITileEntityProvider {

    public JewelersBench() {
        super(Material.iron);
    }

    // You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
        return -1;
    }

    // It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    // It's not a normal block, so you need this too.
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    // This is the icon to use for showing the block in your hand.
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {
        blockIcon = icon.registerIcon("tinkersdefense:WIP");
    }

    @Override
    public TileEntity createNewTileEntity(World w, int md) {
        final TileEntity_JewelersBench te = new TileEntity_JewelersBench();
        return te;
    }
}
