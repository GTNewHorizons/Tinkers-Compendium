package lance5057.tDefense.core.tools;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import tconstruct.library.tools.AbilityHelper;
import tconstruct.library.tools.Weapon;
import tconstruct.tools.TinkerTools;
import cpw.mods.fml.common.Optional;

@Optional.InterfaceList({
        @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.weapons.IBattlegearWeapon"), })
public class TinkerZweihander extends Weapon {

    public TinkerZweihander(int baseDamage) {
        super(3);
        setUnlocalizedName("zweihander");
    }

    @Override
    public Item getHeadItem() {
        return TinkerTools.largeSwordBlade;
    }

    @Override
    public Item getHandleItem() {
        return TinkerTools.toughRod;
    }

    @Override
    public Item getAccessoryItem() {
        return TinkerTools.wideGuard;
    }

    @Override
    public Item getExtraItem() {
        return TinkerTools.swordBlade;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (AbilityHelper.onLeftClickEntity(stack, player, entity, this)) {
            if (entity.riddenByEntity != null || entity.ridingEntity != null) {
                player.attackTargetEntityWithCurrentItem(entity);
            }
        }
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!player.isPotionActive(Potion.moveSlowdown)) {
            @SuppressWarnings("unchecked")
            final List<Entity> entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(
                    player,
                    AxisAlignedBB.getBoundingBox(
                            player.posX - 2,
                            player.posY - 2,
                            player.posZ - 2,
                            player.posX + 2,
                            player.posY + 2,
                            player.posZ + 2));

            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof EntityLivingBase) {
                    player.attackTargetEntityWithCurrentItem(entities.get(i));
                    player.worldObj.spawnParticle(
                            "largeexplode",
                            entities.get(i).posX,
                            entities.get(i).posY + (entities.get(i).height / 2),
                            entities.get(i).posZ,
                            0,
                            0,
                            0);
                    player.worldObj.playSoundAtEntity(entities.get(i), "mob.zombie.metal", 15f, 1f);
                }
            }
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 3));
            player.addPotionEffect(new PotionEffect(Potion.weakness.id, 200, 1));
        }

        return stack;
    }

    @Override
    public float getRepairCost() {
        return 4.0f;
    }

    @Override
    public float getDurabilityModifier() {
        return 2.5f;
    }

    @Override
    public int getPartAmount() {
        return 4;
    }

    @Override
    public String getIconSuffix(int partType) {
        switch (partType) {
            case 0:
                return "_zweihander_blade";
            case 1:
                return "_zweihander_blade_broken";
            case 2:
                return "_zweihander_handle";
            case 3:
                return "_zweihander_guard";
            case 4:
                return "_zweihander_core";
            default:
                return "";
        }
    }

    @Override
    public String getEffectSuffix() {
        return "_zweihander_effect";
    }

    @Override
    public String getDefaultFolder() {
        return "zweihander";
    }

    // @Override
    // @SideOnly(Side.CLIENT)
    // public void onUpdate(ItemStack stack, World world, Entity entity, int par4,
    // boolean par5) {
    // super.onUpdate(stack, world, entity, par4, par5);
    // if (entity instanceof EntityPlayerSP) {
    // EntityPlayerSP player = (EntityPlayerSP) entity;
    // ItemStack usingItem = player.inventory.getCurrentItem();
    // if (usingItem != null && usingItem.getItem() == this) {
    // player.addPotionEffect(new PotionEffect(Potion.resistance.getId(),2,1));
    //
    // }
    // }
    // }

    @Override
    @Optional.Method(modid = "battlegear2")
    public boolean allowOffhand(ItemStack mainhand, ItemStack offhand) {
        if (offhand == null) return true;
        else return false;
    }
}
