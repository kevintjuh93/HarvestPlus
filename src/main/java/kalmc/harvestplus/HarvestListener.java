package kalmc.harvestplus;

import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public record HarvestListener(HarvestPlus plugin) implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        var config = plugin.getHarvestConfig(event.getBlock().getBlockData().getMaterial());

        if (config == null) {
            return;
        }
        if (event.getBlock().getBlockData() instanceof Ageable ageable) {
            if (ageable.getAge() != ageable.getMaximumAge()) {
                return;
            }
        }
        if (config.getXP() > 0 && makeProbChance(config.getProb())) {
            if (config.fromDrop()) {
                event.setExpToDrop(config.getXP());
            } else {
                event.getPlayer().giveExp(config.getXP(), config.allowMending());
            }
        }
    }

    private boolean makeProbChance(int prob) {
        return (Math.random() * 100) < prob;
    }
}
