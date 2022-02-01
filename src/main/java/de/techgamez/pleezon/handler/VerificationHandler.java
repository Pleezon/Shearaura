package de.techgamez.pleezon.handler;

import de.techgamez.pleezon.Main;
import net.labymod.api.LabyModAPI;
import net.labymod.main.LabyMod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class VerificationHandler {
    public static void enable(){
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://smartbot-studios.de/lizenzen/licence.php?wolle=" + LabyMod.getInstance().getPlayerUUID().toString().replace("-", "") ).openConnection().getInputStream()))) {
            String rl0 = bufferedReader.readLine();
            if (rl0 != null && !rl0.equals("") && rl0.contains("3")) {
                Main.l = true;
            }
        }
        catch (Exception localException){
            localException.printStackTrace();
        }
    }
}
