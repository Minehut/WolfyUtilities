package me.wolfyscript.utilities.api.nms.v1_14_R1.block;

import org.bukkit.block.BrewingStand;
import org.bukkit.craftbukkit.v1_14_R1.block.CraftBrewingStand;

public class NMSBrewingStand extends CraftBrewingStand implements me.wolfyscript.utilities.api.nms.block.NMSBrewingStand {

    public NMSBrewingStand(BrewingStand brewingStand) {
        super(brewingStand.getBlock());
    }

    @Override
    public int getFuelLevel() {
        return getTileEntity().fuelLevel;
    }

    @Override
    public void setFuelLevel(int level) {
        getTileEntity().fuelLevel = level;
    }

    @Override
    public int getBrewingTime() {
        return getTileEntity().brewTime;
    }

    @Override
    public void setBrewingTime(int brewTime) {
        getTileEntity().brewTime = brewTime;
    }

    @Override
    public boolean update(boolean force) {
        return super.update(force);
    }
}
