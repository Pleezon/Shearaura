package de.techgamez.pleezon;

import de.techgamez.pleezon.handler.*;
import de.techgamez.pleezon.utils.AddonSettingsReflector;
import de.techgamez.pleezon.utils.CategoryElement;
import net.labymod.api.LabyModAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.*;
import net.labymod.utils.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.naming.ldap.Control;
import java.util.List;

public class Main extends LabyModAddon {
    public static String sheep_home = "";
    public static String drop_home = "";
    private static int tickCount = 0;
    public static int tickDelay = 0;
    public static boolean l = false;
    public static boolean enabled = false;
    public static boolean moveHead = false;
    public static boolean doAutoDrop = false;
    public static boolean pause = false;
    public static boolean antiItemClearEnabled = false;

    @Override
    public void onEnable() {
        if (l) Minecraft.getMinecraft().shutdown();
        SettingsHandler.init();
        VerificationHandler.enable();
        getApi().registerForgeListener(this);
        if (!l) getApi().registerForgeListener(new AddonSettingsReflector());
        this.getApi().getEventManager().register(new MessageReceiveEvent() {
            public boolean onReceive(String unformattedmessage, final String msg) {
                if (l && antiItemClearEnabled && enabled) ItemClearHandler.trigger(unformattedmessage, msg);
                return false;
            }
        });
        System.out.println("Sheeraura initialized.");

    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> generalList) {
        generalList.add(new BooleanElement("§4§lAus§r/§a§lAn§r", new ControlElement.IconData(Material.LEVER), (r) -> {
            enabled = r;
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§a§lERFOLG!!", "§r");
            SettingsHandler.save();
        }, enabled));
        generalList.add(new BooleanElement("§a§lKopfbewegung", new ControlElement.IconData(Material.LEVER), (r) -> {
            moveHead = r;
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§a§lERFOLG!!", "§r");
            SettingsHandler.save();
        }, moveHead));

        generalList.add(new NumberElement("Tick-Delay", new ControlElement.IconData(Material.WATCH), tickDelay).setRange(0, 1000).addCallback((a) -> {
            tickDelay = a;
            SettingsHandler.save();
        }));
        generalList.add(new BooleanElement("§a§lAnti-Itemclear", new ControlElement.IconData(Material.LEVER), (r) -> {
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§a§lERFOLG!!", "§r");
            antiItemClearEnabled = r;
            SettingsHandler.save();
        }, antiItemClearEnabled));
        CategoryElement cat_autodrop = new CategoryElement("Auto-Drop", Material.ENDER_PEARL);
        cat_autodrop.add(new BooleanElement("§a§lAutoDrop", new ControlElement.IconData(Material.LEVER), (r) -> {
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§a§lERFOLG!!", "§r");
            doAutoDrop = r;
            SettingsHandler.save();
        }, doAutoDrop));
        cat_autodrop.add(new StringElement("Home -> Schafe", new ControlElement.IconData(Material.WOOL), sheep_home, (r) -> {
            sheep_home = r;
            SettingsHandler.save();
        }));
        cat_autodrop.add(new StringElement("Home -> DropPosition", new ControlElement.IconData(Material.WOOL), sheep_home, (r) -> {
            drop_home = r;
            SettingsHandler.save();
        }));
        generalList.add(cat_autodrop);

    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (!l) enabled = false;
        if (!l || !enabled) return;
        tickCount++;
        if (doAutoDrop && !pause) AutoDropHandler.trigger();
        if (tickCount >= tickDelay && !pause) {
            tickCount = 0;
            SheeringHandler.trigger();

        }
    }
}
