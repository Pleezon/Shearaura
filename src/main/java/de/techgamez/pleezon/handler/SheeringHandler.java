package de.techgamez.pleezon.handler;

import de.techgamez.pleezon.Main;
import de.techgamez.pleezon.utils.LookingUtils;
import net.labymod.core.LabyModCore;
import net.labymod.core.WorldRendererAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SheeringHandler {

    public static void trigger() {
        if (Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().world==null|| !Main.l || !Main.enabled||!Minecraft.getMinecraft().player.getHeldItemMainhand().getItem().equals(Items.SHEARS)) return;
        EntityPlayerSP p = Minecraft.getMinecraft().player;
        World w = Minecraft.getMinecraft().world;
        Object[] sheeps = w.getLoadedEntityList().stream().filter(e -> e instanceof EntitySheep && !((EntitySheep) e).getSheared() && !((EntitySheep) e).isChild()
                && Math.sqrt(e.getPosition().distanceSq(p.getPosition())) < 3).toArray();
        if (sheeps.length!=0) {
            if(Main.moveHead) LookingUtils.lookAtBlock(((EntitySheep)sheeps[0]).getPosition());
            p.connection.sendPacket(new CPacketUseEntity((EntitySheep)sheeps[0], EnumHand.MAIN_HAND));
        }

    }

}
 /*WorldRendererAdapter worldRenderer = LabyModCore.getWorldRenderer();
        worldRenderer.begin();
        Tessellator t = Tessellator.getInstance();*/