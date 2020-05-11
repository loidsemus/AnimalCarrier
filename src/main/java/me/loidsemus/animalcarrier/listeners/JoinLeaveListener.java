package me.loidsemus.animalcarrier.listeners;

import me.loidsemus.animalcarrier.AnimalCarrier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    private final AnimalCarrier plugin;

    public JoinLeaveListener(AnimalCarrier plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getPlayerManager().initPlayer(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        plugin.getPlayerManager().removePlayer(event.getPlayer());
    }

}
