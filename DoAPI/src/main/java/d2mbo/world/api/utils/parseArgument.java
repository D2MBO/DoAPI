package d2mbo.world.api.utils;

import d2mbo.world.api.DoAPI;
import d2mbo.world.adapter.VersionAdapter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public class parseArgument {
    final private DoAPI main = DoAPI.get();
    final private VersionAdapter version = main.getVersion();

    public parseArgument(final Player player, final List<String> list, final ClickType click) {
        list.forEach(string-> new parseArgument(player, string, click));
    }

    public parseArgument(final Player player, final String string, final ClickType click) {
        final String[] arguments = string.split(",(?=\\s*\\[|\\s*[a-zA-Z])");

        for (String argument : arguments) {
            if (argument.contains("[RIGHT-CLICK]")) {
                if (!click.isRightClick()) continue;
                argument = argument.replace("[RIGHT-CLICK]", "").trim();
            }
            if (argument.contains("[LEFT-CLICK]")) {
                if (!click.isLeftClick()) continue;
                argument = argument.replace("[LEFT-CLICK]", "").trim();
            }
            if (argument.equalsIgnoreCase("CLOSE")) player.closeInventory();
            if (!argument.contains("(") || !argument.contains(")")) continue;


            final String type = argument.substring(0, argument.indexOf("(")).toLowerCase().trim();
            final String value = argument.substring(argument.indexOf("(") + 1, argument.lastIndexOf(")"));

            if (type.equals("item")) {
                final String[] itemArguments = value.split(":");

                ItemMaker itemStack = new ItemMaker(Material.getMaterial(itemArguments[0].toUpperCase()), Integer.parseInt(itemArguments[1]));
                if (!itemArguments[2].equalsIgnoreCase("null")) itemStack.display(main.getVersion().toColor(itemArguments[2]));

                for (int i = 3; i < itemArguments.length; i++) {
                    if (itemArguments[i].startsWith("lore:")) {
                        itemStack = itemStack.lore(itemArguments[i].replace("lore:", ""));
                    }
                    else if (itemArguments[i].startsWith("enchants:")) {
                        itemStack = itemStack.enchantments(itemArguments[i].replace("enchants:", ""));
                    }
                }

                player.getInventory().addItem(itemStack.build());
            }

            else if (type.equals("command")) {
                player.chat("/" + value.replace("<player>", player.getName()));
            }
            else if (type.equals("console")) {
                Bukkit.getScheduler().runTask(DoAPI.get(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), value.replace("<player>", player.getName())));
            }
            else if (type.equals("chat")) {
                player.chat(value.replace("<player>", player.getName()));
            }
            else if (type.equals("message")) {
                player.sendMessage(version.toColor(value.replace("<player>", player.getName())));
            }
            else if (type.equals("broadcast")) {
                Bukkit.broadcastMessage(version.toColor(value.replace("<player>", player.getName())));
            }
            else if (type.equals("sound")) {player.playSound(player.getLocation(), Sound.valueOf(value.toUpperCase()), 1F, 1F);

            }
        }
    }
}
