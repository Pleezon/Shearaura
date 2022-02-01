package de.techgamez.pleezon.utils;

import java.util.List;
import java.util.Objects;


import de.techgamez.pleezon.Main;
import de.techgamez.pleezon.handler.SettingsHandler;
import net.labymod.addon.online.info.AddonInfo;
import net.labymod.main.LabyMod;
import net.labymod.settings.LabyModAddonsGui;
import net.labymod.settings.elements.*;
import net.labymod.utils.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.naming.ldap.Control;

public class AddonSettingsReflector {

    public static boolean obfuscated = true;

    private static boolean resetted = false;
    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        GuiScreen gui = Minecraft.getMinecraft().currentScreen;
        if (gui instanceof LabyModAddonsGui) {
            try {
                LabyModAddonsGui labyModAddonsGui = (LabyModAddonsGui) gui;
                AddonElement openedAddonSettings = (AddonElement) ReflectionUtils.getDeclaredField(labyModAddonsGui, "openedAddonSettings");
                if (openedAddonSettings != null && openedAddonSettings.getAddonInfo() != null) {
                    AddonInfo info = openedAddonSettings.getAddonInfo();
                    if (info.getAuthor().equalsIgnoreCase("Pleezon")
                            && info.getName().equalsIgnoreCase("Shearaura")) {
                        ((List<SettingsElement>) Objects.requireNonNull(ReflectionUtils.getDeclaredField(labyModAddonsGui, "tempElementsStored"))).clear();

                        invokeReflection((List<SettingsElement>) Objects.requireNonNull(ReflectionUtils.getDeclaredField(labyModAddonsGui, "listedElementsStored")));
                    }
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        } else
            reset();
    }

    private void invokeReflection(List<SettingsElement> toInvoke) {
        resetted = false;

        toInvoke.clear();
        toInvoke.add(new BooleanElement("§4§lAus§r/§a§lAn§r", new ControlElement.IconData(Material.LEVER), (r) -> {
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§4§lFEHLER!", "§4§ldir fehlen die nötigen Rechte.!");
        }, false));
        toInvoke.add(new BooleanElement("§a§lKopfbewegung", new ControlElement.IconData(Material.LEVER), (r) -> {

            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§4§lFEHLER!", "§4§ldir fehlen die nötigen Rechte.!");

        }, false));

        toInvoke.add(new NumberElement("Tick-Delay", new ControlElement.IconData(Material.WATCH),0).setRange(0,1000).addCallback((a)->{

        }));
        toInvoke.add(new BooleanElement("§a§lAnti-Itemclear", new ControlElement.IconData(Material.LEVER), (r) -> {
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§4§lFEHLER!", "§4§ldir fehlen die nötigen Rechte.!");
        }, false));
        CategoryElement cat_autodrop = new CategoryElement("Auto-Drop",Material.ENDER_PEARL);
        cat_autodrop.add(new BooleanElement("KEINE RECHTE",new ControlElement.IconData(Material.BARRIER),(a)->{},false));
        toInvoke.add(cat_autodrop);

    }

    private void reset() {
        if (resetted)
            return;
        resetted = true;
    }
}
