// Date: 1/19/2015 11:08:25 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package gmail.Lance5057.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTinkerArmor extends ModelBiped {
    // fields
    ModelRenderer BackPlate;
    ModelRenderer BreastPlate;
    ModelRenderer Plackart;
    ModelRenderer PauldronL;
    ModelRenderer ArmL;
    ModelRenderer PauldronR;
    ModelRenderer ArmR;

    public ModelTinkerArmor(float f) {
        super(f, 0, 64, 64);
        textureWidth = 64;
        textureHeight = 64;

        BackPlate = new ModelRenderer(this, 0, 56);
        BackPlate.addBox(-4F, 0F, 1F, 8, 5, 3);
        BackPlate.setRotationPoint(0F, 0F, 0F);
        BackPlate.setTextureSize(64, 32);
        BackPlate.mirror = true;
        setRotation(BackPlate, -0.0872665F, 0F, 0F);
        this.bipedBody.addChild(BackPlate);

        BreastPlate = new ModelRenderer(this, 0, 32);
        BreastPlate.addBox(-4F, -1F, -5F, 8, 6, 4);
        BreastPlate.setRotationPoint(0F, 0F, 0F);
        BreastPlate.setTextureSize(64, 32);
        BreastPlate.mirror = true;
        setRotation(BreastPlate, 0.4363323F, 0F, 0F);
        this.bipedBody.addChild(BreastPlate);

        Plackart = new ModelRenderer(this, 0, 42);
        Plackart.addBox(-4F, 5F, -3F, 8, 7, 6);
        Plackart.setRotationPoint(0F, 0F, 0F);
        Plackart.setTextureSize(64, 32);
        Plackart.mirror = true;
        setRotation(Plackart, 0F, 0F, 0F);
        this.bipedBody.addChild(Plackart);

        PauldronL = new ModelRenderer(this, 28, 32);
        PauldronL.addBox(1F, -2F, -3.5F, 5, 5, 7);
        PauldronL.setRotationPoint(0F, 0F, 0F);
        PauldronL.setTextureSize(64, 32);
        PauldronL.mirror = true;
        setRotation(PauldronL, 0F, 0F, -0.7853982F);
        this.bipedLeftArm.addChild(PauldronL);

        ArmL = new ModelRenderer(this, 28, 44);
        ArmL.addBox(-1F, -2F, -3F, 5, 10, 6);
        ArmL.setRotationPoint(0F, 0F, 0F);
        ArmL.setTextureSize(64, 32);
        ArmL.mirror = true;
        setRotation(ArmL, 0F, 0F, 0F);
        this.bipedLeftArm.addChild(ArmL);

        PauldronR = new ModelRenderer(this, 28, 32);
        PauldronR.mirror = true;
        PauldronR.addBox(-6F, -2F, -3.5F, 5, 5, 7);
        PauldronR.setRotationPoint(0F, 0F, 0F);
        PauldronR.setTextureSize(64, 32);
        PauldronR.mirror = true;
        setRotation(PauldronR, 0F, 0F, 0.7853982F);
        PauldronR.mirror = false;
        this.bipedRightArm.addChild(PauldronR);

        ArmR = new ModelRenderer(this, 28, 44);
        ArmR.mirror = true;
        ArmR.addBox(-4F, -2F, -3F, 5, 10, 6);
        ArmR.setRotationPoint(0F, 0F, 0F);
        ArmR.setTextureSize(64, 32);
        ArmR.mirror = true;
        setRotation(ArmR, 0F, 0F, 0F);
        ArmR.mirror = false;
        this.bipedRightArm.addChild(ArmR);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
