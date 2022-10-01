package net.pravekypetr.wh.screen.skavenBlastFurnace;

import java.util.Optional;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.screen._renderer.FluidTankRenderer;
import net.pravekypetr.wh.util.MouseUtil;

public class SkavenBlastFurnaceScreen extends AbstractContainerScreen<SkavenBlastFurnaceMenu> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(WH.MOD_ID, "textures/gui/blast_furnace.png");
    private FluidTankRenderer renderer;

    private static Inventory inv;

    public SkavenBlastFurnaceScreen(SkavenBlastFurnaceMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        inv = inventory;
    }

    @Override
    protected void init() {
        super.init();
        assignFluidRenderer();
    }


    private void assignFluidRenderer() {
        renderer = new FluidTankRenderer(4000, true, 16, 70);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        super.renderLabels(pPoseStack, pMouseX, pMouseY);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderFluidAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);

    }

    private void renderFluidAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 152, 8)) {
            renderTooltip(pPoseStack, renderer.getTooltip(menu.getFluidStack(), TooltipFlag.Default.NORMAL),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    
    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(pPoseStack, x, y);
        renderFuel(pPoseStack, x, y);

        renderer.render(pPoseStack, x+152, y+8, menu.getFluidStack());
    }

    private void renderProgressArrow(PoseStack pPoseStack, int x, int y) {
        if (menu.isCrafing()) {
            blit(pPoseStack, x+51, y+34, 176, 14, menu.getScaledProgress(), 17);
        }
    }

    private void renderFuel(PoseStack pPoseStack, int x, int y) {
        if (menu.isBurning()) {
            System.out.println(inv.getContainerSize());
            blit(pPoseStack, x+19, y+37+menu.getScaledFuel(inv.getItem(0)), 176, menu.getScaledFuel(inv.getItem(0)), 14, 14-menu.getScaledFuel(inv.getItem(0)));
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    
    }
    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x+offsetX, y+offsetY, renderer.getWidth(), renderer.getHeight());
    }
}
