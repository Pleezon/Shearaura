package de.techgamez.pleezon.handler;

import de.techgamez.pleezon.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;

public class AutoDropHandler {
    public static void trigger() {
        if(Minecraft.getMinecraft().player==null)return;
        boolean found = false;
        for (int i = 0;i < 36; i++) {
            ItemStack s = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
            if (s.isEmpty()) {
                found = true;
            }
        }
        if (!found) {
            Main.pause = true;
            new Thread(() -> {
                Minecraft.getMinecraft().player.sendChatMessage("/home " + Main.drop_home);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 36; i++) {
                    ItemStack s = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
                    if (!s.isEmpty()&&s.getUnlocalizedName().contains("cloth")) {
                        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId,i < 9 ? i + 36 : i,1, ClickType.THROW,Minecraft.getMinecraft().player);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Minecraft.getMinecraft().player.sendChatMessage("/home " + Main.sheep_home);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Main.pause=false;
            }).start();


        }
    }
}
