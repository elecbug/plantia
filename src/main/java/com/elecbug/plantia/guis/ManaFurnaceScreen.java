package com.elecbug.plantia.guis;

import com.elecbug.plantia.ModMain;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ManaFurnaceScreen extends AbstractContainerScreen<ManaFurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ModMain.MOD_ID, "textures/gui/mana_furnace.png");

    public ManaFurnaceScreen(ManaFurnaceMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @SuppressWarnings("null")
    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    
        int manaStored = menu.getContainerData().get(0);
        int maxMana = menu.getContainerData().get(1);

        if (manaStored > 0) {
            int manaBarHeight = (int) (((double) manaStored / maxMana) * 50);
            blit(poseStack, leftPos + 100, topPos + 30 + (50 - manaBarHeight), 176, 0, 16, manaBarHeight);
        }

        int fuelBarHeight = (int) ((menu.getBurnTime() / 200.0) * 14);
        if (fuelBarHeight > 0) {
            blit(poseStack, leftPos + 57, topPos + 54 + (14 - fuelBarHeight), 176, 14, 14, fuelBarHeight);
        }
    }
}
