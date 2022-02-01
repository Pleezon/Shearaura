package de.techgamez.pleezon.handler;

import de.techgamez.pleezon.Main;
import org.jline.utils.WriterOutputStream;

import java.io.*;
import java.util.Properties;

public class SettingsHandler {
    public static void init(){
        new File("./sheeraura/").mkdir();
        load();
    }
    public static void save(){
        try{

            Properties prop = new Properties();
            prop.setProperty("enabled",String.valueOf(Main.enabled));
            prop.setProperty("tickdelay",String.valueOf(Main.tickDelay));
            prop.setProperty("doautodrop",String.valueOf(Main.doAutoDrop));
            prop.setProperty("movehead",String.valueOf(Main.moveHead));
            prop.setProperty("sheep_home",String.valueOf(Main.sheep_home));
            prop.setProperty("drop_home",String.valueOf(Main.drop_home));
            prop.setProperty("antiitemclear",String.valueOf(Main.antiItemClearEnabled));
            prop.store(new FileOutputStream("./sheeraura/config.aura"),"");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void load(){
        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream("./sheeraura/config.aura"));
            Main.enabled = Boolean.parseBoolean(prop.getProperty("enabled"));
            Main.tickDelay = Integer.parseInt(prop.getProperty("tickdelay"));
            Main.doAutoDrop = Boolean.parseBoolean(prop.getProperty("doautodrop"));
            Main.moveHead = Boolean.parseBoolean(prop.getProperty("movehead"));
            Main.drop_home = String.valueOf(prop.getProperty("drop_home"));
            Main.sheep_home = String.valueOf(prop.getProperty("sheep_home"));
            Main.antiItemClearEnabled = Boolean.parseBoolean(prop.getProperty("antiitemclear"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
