package lance5057.tDefense.core.tools;

import java.util.List;

import mods.battlegear2.api.ISheathed;
import mods.battlegear2.api.shield.IArrowCatcher;
import mods.battlegear2.api.shield.IArrowDisplay;
import mods.battlegear2.api.shield.IShield;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import tconstruct.library.tools.ToolCore;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Optional.InterfaceList({ @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.ISheathed"),
        @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.shield.IArrowCatcher"),
        @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.shield.IArrowDisplay"),
        @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.shield.IShield") })
public class Shield extends ToolCore implements IShield, ISheathed, IArrowCatcher, IArrowDisplay {

    public Shield(int baseDamage) {
        super(baseDamage);
    }

    protected float baseSpeed() {
        return 1.5f;
    }

    protected float effectiveSpeed() {
        return 15f;
    }

    public float breakSpeedModifier() {
        return 1.0f;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        return 0.0f;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
            float clickX, float clickY, float clickZ) {
        return false;
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    @Override
    public boolean canHarvestBlock(Block block, ItemStack is) {
        for (final Material element : web) {
            if (block.getMaterial() == element) {
                return true;
            }
        }
        return super.canHarvestBlock(block, is);
    }

    protected Material[] getEffectiveMaterials() {
        return web;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        super.onUpdate(stack, world, entity, par4, par5);
        if (entity instanceof EntityPlayerSP) {
            final EntityPlayerSP player = (EntityPlayerSP) entity;
            final ItemStack usingItem = player.getItemInUse();
            if (usingItem != null && usingItem.getItem() == this) {
                player.movementInput.moveForward *= 2.5F;
                player.movementInput.moveStrafe *= 2.5F;
            }
        }
    }

    @Override
    public String[] getTraits() {
        return new String[] { "shield", "blocking" };
    }

    public static Material[] web = new Material[] { Material.web, Material.cloth, Material.coral, Material.cake };
    public static Material[] none = new Material[0];

    protected String getHarvestType() {
        return null;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public int getArrowCount(ItemStack stack) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("arrows")) {
            return stack.getTagCompound().getShort("arrows");
        } else {
            return 0;
        }
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public void setArrowCount(ItemStack stack, int count) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Should never happen, you would need A LOT of arrows for this to
        // happen
        if (count > Short.MAX_VALUE) {
            count = Short.MAX_VALUE;
        }
        stack.getTagCompound().setShort("arrows", (short) count);
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean catchArrow(ItemStack shield, EntityPlayer player, IProjectile arrow) {
        if (arrow instanceof EntityArrow) {
            setArrowCount(shield, getArrowCount(shield) + 1);
            player.setArrowCountInEntity(player.getArrowCountInEntity() - 1);
            ((EntityArrow) arrow).setDead();
            return true;
        }
        return false;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean sheatheOnBack(ItemStack item) {
        return true;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public void blockAnimation(EntityPlayer player, float dmg) {
        player.worldObj.playSoundAtEntity(player, "battlegear2:shield", 1, 1);
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean canBlock(ItemStack shield, DamageSource source) {
        return !source.isUnblockable();
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public int getBashTimer(ItemStack arg0) {
        return 10;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public float getBlockAngle(ItemStack arg0) {
        return 60;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public float getDamageDecayRate(ItemStack shield, float amount) {
        return 0;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public float getDamageReduction(ItemStack arg0, DamageSource arg1) {
        return 1f;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public float getDecayRate(ItemStack stack) {
        final NBTTagCompound tags = stack.getTagCompound();
        final float recovery = tags.getCompoundTag("InfiTool").getInteger("MiningSpeed")
                + (tags.getCompoundTag("InfiTool").getInteger("feathers") * 300) / 1.5f;
        return 10f / recovery;
    }

    @Override
    @Optional.Method(modid = "battlegear2")
    public float getRecoveryRate(ItemStack stack) {
        final NBTTagCompound tags = stack.getTagCompound();
        final float recovery = tags.getCompoundTag("InfiTool").getInteger("MiningSpeed")
                + (tags.getCompoundTag("InfiTool").getInteger("feathers") * 300) / 1.5f;
        return 10f / recovery;
    }

    @Override
    public Item getAccessoryItem() {
        return null;
    }

    @Override
    public String getDefaultFolder() {
        return null;
    }

    @Override
    public String getEffectSuffix() {
        return null;
    }

    @Override
    public Item getHeadItem() {
        return null;
    }

    @Override
    public String getIconSuffix(int arg0) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Optional.Method(modid = "battlegear2")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer,
            @SuppressWarnings("rawtypes") List par3List, boolean par4) {
        final NBTTagCompound tags = par1ItemStack.getTagCompound();
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add("");
        par3List.add(
                EnumChatFormatting.DARK_GREEN
                        + ItemStack.field_111284_a.format(
                                1F / (10f / (tags.getCompoundTag("InfiTool").getInteger("MiningSpeed")
                                        + (tags.getCompoundTag("InfiTool").getInteger("feathers") * 300) / 1.5f)) / 20F)
                        + StatCollector.translateToLocal("attribute.shield.block.time"));
        final int arrowCount = getArrowCount(par1ItemStack);
        if (arrowCount > 0) {
            par3List.add(
                    String.format(
                            "%s%s %s",
                            EnumChatFormatting.GOLD,
                            arrowCount,
                            StatCollector.translateToLocal("attribute.shield.arrow.count")));
        }
    }
}
