package net.pravekypetr.wh.entity.client;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.entity.passive.RatEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RatRenderer extends GeoEntityRenderer<RatEntity> {

    public RatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RatModel());
        this.shadowRadius = 0.125f;
    }

    @Override
    public ResourceLocation getTextureLocation(RatEntity instance) {
        return new ResourceLocation(WH.MOD_ID, "textures/entity/rat_texture.png");
    }    

    @Override
    public RenderType getRenderType(RatEntity animatable, float partialTicks, PoseStack stack,
            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
            ResourceLocation textureLocation) {
        
        stack.scale(2f, 2f, 2f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn,
                textureLocation);
    }
}
