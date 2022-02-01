package de.techgamez.pleezon.handler;

import de.techgamez.pleezon.Main;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;

public class ItemClearHandler  {
    public static void trigger(String unformattedmessage,String msg){
        if(unformattedmessage.contains("§8[§r§6GrieferGames§r§8] §r§4Warnung! §r§cDie auf dem Boden liegenden Items werden in") && unformattedmessage.contains("§r§cSekunden entfernt!§r")){
            if(msg.split(" ")[10].equals("20")){
                LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§4§lITEMCLEAR ERKANNT", "§a§lwarte 21sek.");
                Main.pause = true;
                new Thread(()->{
                    try {
                        Thread.sleep(21000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§a§lWARTE-STATUS", "§a§lwarten beendet.");
                    Main.pause=false;
                }).start();
            }
        }

    }

}
