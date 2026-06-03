package d2mbo.world.api.utils;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemMaker {
    private ItemStack stack;
    private ItemMeta meta;

    public ItemMaker(final Material material) {
        this(material == null ? Material.BARRIER : material, 1);
    }

    public ItemMaker(final Material material, final int amount) {
        this(material == null ? Material.BARRIER : material, amount, (short)0);
    }

    public ItemMaker(final ItemStack stack) {
        if(stack == null) {
            this.stack = new ItemStack(Material.BARRIER);
        } else {
            //Preconditions.checkNotNull((Object) stack, "ItemStack cannot be null");
            this.stack = stack;
        }
    }

    public ItemMaker(final Material material, final int amount, final short data) {
        if(material == null) {
            this.stack = new ItemStack(Material.BARRIER, amount <= 0 ? 1 : amount, data);
        } else {
            //Preconditions.checkNotNull((Object) stack, "ItemStack cannot be null");
            this.stack = new ItemStack(material, amount <= 0 ? 1 : amount, data);
        }
    }

    public ItemMaker display(final String name) {
        if (meta == null) {
            meta = stack.getItemMeta();
        }

        if(stack.getType() == Material.BARRIER) {
            meta.setDisplayName("§cError: Verify item name");
        }
        meta.setDisplayName(name);
        return this;
    }

    public ItemMaker loreLine(final String line, final int i) {
        if (meta == null) {
            meta = stack.getItemMeta();
        }
        final boolean hasLore = meta.hasLore();
        final List<String> lore = hasLore ? meta.getLore() : new ArrayList<>();
        lore.add(hasLore ? lore.size() : 0, line);
        //lore(line);
        return this;
    }

    public ItemMaker lore(final List<String> lore) {
        if (meta == null) meta = stack.getItemMeta();
        if(stack.getType() == Material.BARRIER){
            lore.add("");
            lore.add("§cItem materialName not found.");
            lore.add("§cChange it in the respective file(yml).");
        }
        lore.replaceAll(s -> s.replace("&", "§"));
        meta.setLore(lore);
        return this;
    }
    public ItemMaker lore(final String string) {
        if (meta == null) meta = stack.getItemMeta();
        final String[] lines = string.substring("lore:".length()).trim().split(",");
        final List<String> lore = new ArrayList<>();

        for(String line : lines) {
            lore.add(line);
        }
        meta.setLore(lore);
        return this;
    }

    public ItemMaker enchant(final Enchantment enchantment, final int level) {
        return enchant(enchantment, level, true);
    }

    public ItemMaker enchant(final Enchantment enchantment, final int level, final boolean unsafe) {
        if (unsafe && level >= enchantment.getMaxLevel()) {
            stack.addUnsafeEnchantment(enchantment, level);
        }
        else {
            stack.addEnchantment(enchantment, level);
        }
        return this;
    }

    public ItemMaker enchantments(final String enchants) {
        System.out.println("[DEBUG] string: '" + enchants + "'");

        if(meta == null) meta = stack.getItemMeta();
        final String[] split = enchants.substring("enchants:".length()).trim().split(", ");

        for(final String enchant : split) {
            final String[] value = enchant.split("=");
            final Enchantment enchantment = Enchantment.getByName(value[0].trim());
            final int level = Integer.parseInt(value[1].trim());

            if(enchantment == null) continue;
            meta.addEnchant(enchantment, level, true);
        }
        return this;
    }

    public ItemMaker data(final short data) {
        stack.setDurability(data);
        return this;
    }

    public ItemStack build() {
        if (meta != null) {
            stack.setItemMeta(meta);
        }
        return stack;
    }

    public String toString() {
        final boolean displayed = meta.hasDisplayName();
        final boolean lored = meta.hasLore();
        final boolean enchanted = !stack.getEnchantments().isEmpty();

        final String material = stack.getType().toString()+";";
        final String data = stack.getDurability()+";";
        final String amount = stack.getAmount()+(displayed ? ";" : "");
        final String display = (displayed ? meta.getDisplayName() : "") +(lored ? ";" : "");
        final String lore = lored ? "lore: "+String.join(",", meta.getLore()) : "";
        final String enchants = enchanted ? "enchants: "+String.join(",", stack.getEnchantments().toString()) : "";

        return material+data+amount+display+lore+enchants;
    }
}
