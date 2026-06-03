package d2mbo.world.api.utils;

import d2mbo.world.api.DoAPI;
import d2mbo.world.adapter.VersionAdapter;
import lombok.Getter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;


public class TextMaker {
    @Getter private TextComponent text;
    final private VersionAdapter version;

    public TextMaker(final String text) {
        this.version = DoAPI.get().getVersion();
        this.text = new TextComponent(version.toColor(text));
    }

    public TextMaker setHover(final String text) {
        this.text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(version.toColor(text)).create()));
        return this;
    }
    public TextMaker runCommand(final String command) {
        this.text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return this;
    }
    public TextMaker runLink(final String url) {
        this.text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        return this;
    }

}
