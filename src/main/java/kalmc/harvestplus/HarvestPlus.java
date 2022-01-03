package kalmc.harvestplus;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EnumMap;

public final class HarvestPlus extends JavaPlugin {

    public static final Material[] HARVEST_MATERIALS = {
            Material.WHEAT, Material.CARROTS, Material.POTATOES, Material.NETHER_WART,
            Material.BEETROOTS,
            Material.PUMPKIN, Material.MELON
    };
    private EnumMap<Material, HarvestConfig> configs;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfigs();
        getServer().getPluginManager().registerEvents(new HarvestListener(this), this);
        getCommand("harvestplus").setExecutor(new HarvestCommand(this));
        getLogger().info("Enabled");
    }

    public HarvestConfig getHarvestConfig(Material mat) {
        return configs.get(mat);
    }

    public void loadConfigs() {
        EnumMap<Material, HarvestConfig> map = new EnumMap<>(Material.class);

        for (var material : HARVEST_MATERIALS) {
            map.put(material, new HarvestConfig(material, getConfig()));
        }
        this.configs = map;
    }

    @Override
    public void onDisable() {
    }
}
