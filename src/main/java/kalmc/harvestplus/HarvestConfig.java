package kalmc.harvestplus;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

public class HarvestConfig {

    private final Material material;
    private final int xp;
    private final int prob;
    private final boolean mending;
    private final boolean drop;

    public HarvestConfig(Material material, Configuration config) {
        this.material = material;
        String matName = material.name().toLowerCase();
        this.xp = config.getInt(String.format("%s.xp", matName), 0);
        this.prob = config.getInt(String.format("%s.prob", matName), 100);
        this.mending = config.getBoolean(String.format("%s.mending", matName), false);
        this.drop = config.getBoolean(String.format("%s.drop", matName), false);
    }

    public Material getMaterial() {
        return material;
    }

    public int getXP() {
        return xp;
    }

    public int getProb() {
        return prob;
    }

    public boolean allowMending() {
        return mending;
    }

    public boolean fromDrop() {
        return drop;
    }
}
