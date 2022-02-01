package de.techgamez.pleezon.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;

public class LookingUtils {
    public static void lookAtBlock(BlockPos pos) {
        if (pos == null)
            return;
        EntityPlayerSP p = Minecraft.getMinecraft().player;
        double x = pos.getX() - (p.posX - 0.5d), z = pos.getZ() - (p.posZ - 0.5d);
        double yaw = Math.toDegrees(Math.atan(z / x));
        yaw += x < 0 ? 90 : -90;
        double y = Math.sqrt((x * x) + (z * z));
        double pitch = Math.toDegrees(Math.atan((pos.getY() - (p.posY + 1)) / y));
        setPosition((float) yaw, (float) -pitch);
    }
    protected static void setPosition(float yaw, float pitch){
        double xpos = Minecraft.getMinecraft().player.prevPosX; /*xpos*/
        double ypos = Minecraft.getMinecraft().player.prevPosY; /*xpos*/
        double zpos = Minecraft.getMinecraft().player.prevPosZ; /*zpos*/
        Minecraft.getMinecraft().player.setPositionAndRotation(xpos,ypos,zpos,yaw,pitch);
    }
}
