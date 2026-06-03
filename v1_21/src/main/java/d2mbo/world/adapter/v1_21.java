package d2mbo.world.adapter;

import net.md_5.bungee.api.ChatColor;

public final class v1_21 implements VersionAdapter {

    @Override
    public String toColor(final String text) {
        final String[] texts = text.split(String.format("((?<=%1$s)|(?=%1$s))", "&"));
        final StringBuilder createText = new StringBuilder();
        for (int i = 0; i < texts.length; ++i) {
            if (!text.contains("#")) return ChatColor.translateAlternateColorCodes('&', text);
            if (texts[i].equals("&")) ++i;

            if (texts[i].startsWith("#") && !texts[i].startsWith("#<")) {
                final ChatColor color = ChatColor.of(texts[i].substring(0, 7));
                createText.append(color + texts[i].substring(7));
                ++i;
            }
            else {
                createText.append("§" + texts[i]);
            }
        }
        return createText.toString();
    }
}
