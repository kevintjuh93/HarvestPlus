package kalmc.harvestplus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public record HarvestCommand(HarvestPlus plugin) implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("harvestplus")) {
            if (args.length != 1)
                return false;
            if (args[0].equalsIgnoreCase("reload") && hasPermission(sender, "harvestplus.reload")) {
                plugin.loadConfigs();
                sender.sendMessage("HarvestPlus reloaded");
                return true;
            }
        }
        sender.sendMessage("Unknown command. Type \"/help\" for help.");
        return true;
    }

    private boolean hasPermission(CommandSender sender, String permission) {
        if (sender instanceof Player player) {
            return (player.hasPermission(permission) || player.isOp());
        }
        return true;
    }
}

