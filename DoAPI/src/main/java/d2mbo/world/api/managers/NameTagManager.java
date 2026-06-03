package d2mbo.world.api.managers;

import d2mbo.world.api.DoAPI;
import d2mbo.world.api.utils.NameTagMaker;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class NameTagManager {
    private final DoAPI main = DoAPI.get();

    private void setNameTab(final Player player, final String prefix, final String suffix) {
        main.getVersion().setNameTab(player, prefix, suffix);
    }

    private void setNameTag(final Player player, final String prefix, final String suffix) {
        main.getVersion().setNameTag(player, prefix, suffix);
    }
}
