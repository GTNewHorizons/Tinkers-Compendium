package lance5057.tDefense.armor;

import lance5057.tDefense.TDIntegration;
import lance5057.tDefense.TinkersDefense;
import lance5057.tDefense.armor.parts.ClothMaterial;
import lance5057.tDefense.armor.renderers.ArmorRenderer;
import lance5057.tDefense.core.tools.modifiers.Modifiers;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import org.lwjgl.opengl.GL11;

import tconstruct.library.TConstructRegistry;
import tconstruct.library.tools.AbilityHelper;
import tconstruct.library.tools.CustomMaterial;
import tconstruct.library.tools.ToolCore;
import thaumcraft.api.IRunicArmor;
import vazkii.botania.api.item.IPixieSpawner;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Optional.InterfaceList({
        @Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.IRunicArmor", striprefs = true),
        @Optional.Interface(modid = "Botania", iface = "vazkii.botania.api.item.IPixieSpawner", striprefs = true) })
public abstract class ArmorCore extends ToolCore implements ISpecialArmor, IRunicArmor, IPixieSpawner {

    int slot;
    public float reductionPercent = 0f;
    protected int maxReduction = 100;

    // public String[] renderParts;

    // Thaumcraft
    boolean Charge = false;

    public ArmorCore(int baseProtection, int slot) {
        super(baseProtection);

        this.slot = slot;

        // MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
            int slot) {
        ArmorProperties armorp;
        if (!source.isUnblockable()) {
            armorp = new ArmorProperties(0, reductionPercent, maxReduction); // 0.04
            // per
            // half
            // shirt
        } else {
            armorp = new ArmorProperties(0, 0, 0);
        }

        return armorp;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return (int) ((int) reductionPercent / 0.04);
    }

    public int getSlot() {
        return slot;
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getModel(String[] color, NBTTagCompound tags) {
        return null;
    }

    @Override
    public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {
        return armorType == slot;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        AbilityHelper.damageTool(stack, damage, entity, false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (itemStack.getItem() instanceof ToolCore) {
            final String[] color = new String[10];
            final ToolCore tool = (ToolCore) itemStack.getItem();

            for (int j = 0; j < 10; j++) {
                color[j] = Integer.toHexString(itemStack.getItem().getColorFromItemStack(itemStack, j));

                switch (j) {
                    case 0:
                        if (tool.getHandleItem() == TinkersDefense.partCloth) {
                            final int ID = itemStack.getTagCompound().getCompoundTag("InfiTool")
                                    .getInteger("RenderHandle");

                            final CustomMaterial newColor = TConstructRegistry
                                    .getCustomMaterial(ID, ClothMaterial.class);
                            color[j] = Integer.toHexString(newColor.color);
                        }
                        break;

                    case 1:
                        if (tool.getHeadItem() == TinkersDefense.partCloth) {
                            final int ID = itemStack.getTagCompound().getCompoundTag("InfiTool")
                                    .getInteger("RenderHead");

                            final CustomMaterial newColor = TConstructRegistry
                                    .getCustomMaterial(ID, ClothMaterial.class);
                            color[j] = Integer.toHexString(newColor.color);
                        }
                        break;

                    case 2:
                        if (tool.getAccessoryItem() != null && tool.getAccessoryItem() == TinkersDefense.partCloth) {
                            final int ID = itemStack.getTagCompound().getCompoundTag("InfiTool")
                                    .getInteger("RenderAccessory");

                            final CustomMaterial newColor = TConstructRegistry
                                    .getCustomMaterial(ID, ClothMaterial.class);
                            color[j] = Integer.toHexString(newColor.color);
                        }
                        break;

                    case 3:
                        if (tool.getExtraItem() != null && tool.getExtraItem() == TinkersDefense.partCloth) {
                            final int ID = itemStack.getTagCompound().getCompoundTag("InfiTool")
                                    .getInteger("RenderExtra");

                            final CustomMaterial newColor = TConstructRegistry
                                    .getCustomMaterial(ID, ClothMaterial.class);
                            color[j] = Integer.toHexString(newColor.color);
                        }
                        break;
                }
            }

            final ArmorRenderer model = getRenderer();
            model.SetColors(color, getDefaultFolder(), itemStack);
            return model;
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public abstract ArmorRenderer getRenderer();

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

    @Override
    public String[] getTraits() {
        return new String[] { "armor" };
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

        Modifiers.AMod.UpdateAll(
                (ToolCore) itemStack.getItem(),
                itemStack,
                world,
                player,
                itemStack.getTagCompound().getCompoundTag("InfiTool"));
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity ent, int p_77663_4_, boolean p_77663_5_) {
        // Check if runic shielding level has changed
        if (TDIntegration.thaumcraft) {
            final NBTTagCompound tcTag = stack.getTagCompound();
            final NBTTagCompound ticoTag = stack.getTagCompound().getCompoundTag("InfiTool");

            final byte rs = tcTag.getByte("RS.HARDEN");
            if (!Charge && rs > 0) {
                if (ticoTag.getInteger("Modifiers") > 0) {
                    ticoTag.setInteger("Modifiers", ticoTag.getInteger("Modifiers") - 1);
                    Charge = true;
                } else {
                    tcTag.removeTag("RS.HARDEN");
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderArmor(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, String[] colors,
            ItemStack stack, int pass) {

        final ResourceLocation rc = new ResourceLocation(
                "tinkersdefense:textures/" + getDefaultFolder() + "/" + getTexture(pass, stack) + ".png");
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(rc);

        final float size = 1.6f;
        GL11.glScalef(1.0F / size, 1.0F / size, 1.0F / size);
        GL11.glTranslatef(0.0F, -0.01F, 0.0F);

        final int[] intColors = TinkersDefense.hexToRGB(colors[pass]);
        GL11.glColor3d((float) intColors[0] / 255, (float) intColors[1] / 255, (float) intColors[2] / 255);
    }

    @SideOnly(Side.CLIENT)
    public String getTexture(int pass, ItemStack stack) {
        final NBTTagCompound tags = stack.getTagCompound().getCompoundTag("InfiTool");
        String renderReturn = "";
        switch (pass) {
            case 0:
                renderReturn = handleStrings.get(tags.getInteger("RenderHandle"));
                break;
            case 1:
                renderReturn = headStrings.get(tags.getInteger("RenderHead"));
                break;
            case 2:
                renderReturn = accessoryStrings.get(tags.getInteger("RenderAccessory"));
                break;
            case 3:
                renderReturn = extraStrings.get(tags.getInteger("RenderExtra"));
                break;
            //
            // default:
            // if(tags != null && tags.hasKey("Effect" + (pass - getPartAmount())))
            // {
            // final String effect = effectStrings.get(tags.getInteger("Effect" + (pass - getPartAmount())));
            // if(effect != null)
            // return effect.substring(effect.lastIndexOf("/") + 1);
            // else
            // return "";
            //
            // }
        }
        if (renderReturn != null && renderReturn != "") {
            renderReturn = renderReturn.substring(renderReturn.indexOf("_"));
        } else {
            renderReturn = "";
        }

        return renderReturn;
    }

    @Override
    public float getPixieChance(ItemStack stack) {
        final float chance = stack.getTagCompound().getCompoundTag("InfiTool").getInteger("ElementiumCore") * 5 / 100f;
        return chance;
    }
}
