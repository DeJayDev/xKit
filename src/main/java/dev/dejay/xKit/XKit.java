package dev.dejay.xKit;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class XKit extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.getDrops().clear(); // force the player not to drop anything
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        giveItems(player);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        giveItems(player);
    }

    public void giveItems(Player player) {
        PlayerInventory inventory = player.getInventory();

        // Define the items:
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
        // Example for defining custom metadata, like a name, setting an enchant and removing the enchant list thing
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.displayName(Component.text("woah!", NamedTextColor.RED));
        helmetMeta.addEnchant(Enchantment.SHARPNESS, 1, false);
        helmetMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        helmet.setItemMeta(helmetMeta);

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);

        ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack shield = new ItemStack(Material.SHIELD, 1);

        ItemStack food = new ItemStack(Material.COOKED_BEEF, 16);
        ItemStack wool = new ItemStack(Material.PINK_WOOL, 32);

        // using equipment slots to equip items,
        inventory.setItem(EquipmentSlot.HEAD, helmet);
        inventory.setItem(EquipmentSlot.CHEST, chestplate);
        inventory.setItem(EquipmentSlot.LEGS, leggings);
        inventory.setItem(EquipmentSlot.FEET, boots);

        inventory.setItem(EquipmentSlot.HAND, sword);
        inventory.setItem(EquipmentSlot.OFF_HAND, shield);

        inventory.addItem(food, wool); // notice you can just put however many you want in these
    }

}
