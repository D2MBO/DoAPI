package d2mbo.world.api.utils;

import d2mbo.world.adapter.VersionAdapter;
import d2mbo.world.api.DoAPI;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MaterialMaker {
    final private ItemStack item;
    private ItemMeta meta;
    private LeatherArmorMeta armorMeta;
    final private VersionAdapter version = DoAPI.get().getVersion();


    public MaterialMaker(final Material material, final int amount, final int data) {
        item = new ItemStack(material, amount, (byte)data);
        meta = item.getItemMeta();
    }
    public MaterialMaker display(final String display) {
        meta.setDisplayName(version.toColor(display));
        return this;
    }

    public MaterialMaker lore(final String... line) {
        if (meta == null) meta = item.getItemMeta();

        final List<String> lore = Arrays.asList(line);
        for (String string : line) lore.add(version.toColor(string));

        meta.setLore(lore);
        return this;
    }
    public MaterialMaker enchants(final HashMap<Enchantment, Integer> enchants) {
        enchants.forEach((enchantment, level) -> {
            if(level >= enchantment.getMaxLevel()) item.addUnsafeEnchantment(enchantment, level);
            else item.addEnchantment(enchantment, level);
        });
        return this;
    }
    public MaterialMaker armor(final Color color) {
        armorMeta = (LeatherArmorMeta) meta;
        armorMeta.setColor(color);
        item.setItemMeta(armorMeta);
        return this;
    }

    public ItemStack create() {
        item.setItemMeta(armorMeta == null ? meta : armorMeta);
        return item;
    }
}
