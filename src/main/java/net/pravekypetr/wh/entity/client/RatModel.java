package net.pravekypetr.wh.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.entity.passive.RatEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RatModel extends AnimatedGeoModel<RatEntity> {

    @Override
    public ResourceLocation getAnimationResource(RatEntity animatable) {
        return new ResourceLocation(WH.MOD_ID, "animations/com.rat.animation.json");
    }
    
    @Override
    public ResourceLocation getModelResource(RatEntity object) {
        return new ResourceLocation(WH.MOD_ID, "geo/rat.geo.json");
    }
    
    @Override
    public ResourceLocation getTextureResource(RatEntity object) {
        return new ResourceLocation(WH.MOD_ID, "textures/entity/rat_texture.png");
    }
    
}
