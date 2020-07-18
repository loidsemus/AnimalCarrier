package me.loidsemus.animalcarrier.listeners;

import me.loidsemus.animalcarrier.AnimalCarrier;
import me.loidsemus.animalcarrier.messages.LangKey;
import me.loidsemus.pluginlib.Messages;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class InteractListener implements Listener {

    private final AnimalCarrier plugin;

    public InteractListener(AnimalCarrier plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking() || !event.getHand().equals(EquipmentSlot.HAND) || !player.hasPermission("animalcarrier.use")) {
            return;
        }

        Material pickupMaterial = plugin.getMainConfig().getPickupItem();

        if (!player.getInventory().getItemInMainHand().getType().equals(pickupMaterial)) {
            return;
        }

        if (plugin.getPlayerManager().getEntitiesCarriedByPlayer(player).size() >= plugin.getMainConfig().getMaxAmount()) {
            player.sendMessage(Messages.get(LangKey.MAX_AMOUNT_REACHED, true, plugin.getMainConfig().getMaxAmount() + ""));
            return;
        }

        Entity entity = event.getRightClicked();

        if (!plugin.getMainConfig().getCarriableEntities().contains(entity.getType())) {
            return;
        }

        event.setCancelled(true);
        entity.remove();
        plugin.getPlayerManager().addEntityCarriedByPlayer(player, entity);
        player.sendMessage(Messages.get(LangKey.ANIMAL_PICKED_UP, true, entity.getName()));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!player.isSneaking() || event.getHand() == null || !event.getHand().equals(EquipmentSlot.HAND) || !player.hasPermission("animalcarrier.use")
                || event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Material pickupMaterial = plugin.getMainConfig().getPickupItem();
        if (!player.getInventory().getItemInMainHand().getType().equals(pickupMaterial)) {
            return;
        }

        if (plugin.getPlayerManager().getEntitiesCarriedByPlayer(player).isEmpty()) {
            player.sendMessage(Messages.get(LangKey.NO_ANIMALS_CARRIED, true));
            return;
        }

        for (Entity entity : plugin.getPlayerManager().getEntitiesCarriedByPlayer(player)) {
            Entity spawnedEntity = player.getWorld().spawnEntity(
                    Objects.requireNonNull(event.getClickedBlock()).getRelative(event.getBlockFace()).getLocation(),
                    entity.getType());
            spawnedEntity.setCustomName(entity.getCustomName());
            spawnedEntity.setCustomNameVisible(entity.isCustomNameVisible());
            spawnedEntity.setTicksLived(entity.getTicksLived());
        }
        plugin.getPlayerManager().clearEntitiesCarriedByPlayer(player);
    }

}
