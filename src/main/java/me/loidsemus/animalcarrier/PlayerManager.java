package me.loidsemus.animalcarrier;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {

    private final Map<Player, List<Entity>> carriedAnimals = new HashMap<>();

    public void initPlayer(Player player) {
        carriedAnimals.put(player, new ArrayList<>());
    }

    public void removePlayer(Player player) {
        carriedAnimals.remove(player);
    }

    public void addEntityCarriedByPlayer(Player player, Entity entity) {
        carriedAnimals.get(player).add(entity);
    }

    public void clearEntitiesCarriedByPlayer(Player player) {
        carriedAnimals.get(player).clear();
    }

    public List<Entity> getEntitiesCarriedByPlayer(Player player) {
        return carriedAnimals.get(player);
    }

}
