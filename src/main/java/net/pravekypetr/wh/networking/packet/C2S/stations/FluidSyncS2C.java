package net.pravekypetr.wh.networking.packet.C2S.stations;


import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;
import net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.entity.SkavenBlastFurnaceBlockEntity;
import net.pravekypetr.wh.screen.skavenBlastFurnace.SkavenBlastFurnaceMenu;

import java.util.function.Supplier;

public class FluidSyncS2C {
    private final FluidStack fluidStack;
    private final BlockPos pos;

    public FluidSyncS2C(FluidStack fluidStack, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.pos = pos;
    }

    public FluidSyncS2C(FriendlyByteBuf buf) {
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof SkavenBlastFurnaceBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof SkavenBlastFurnaceMenu menu &&
                    menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
        });
        return true;
    }
}