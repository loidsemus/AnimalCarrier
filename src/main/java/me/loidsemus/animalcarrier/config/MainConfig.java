package me.loidsemus.animalcarrier.config;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.annotation.ElementType;
import de.exlll.configlib.configs.yaml.YamlConfiguration;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class MainConfig extends YamlConfiguration {

    public MainConfig(Path path, YamlProperties properties) {
        super(path, properties);
    }

    @Comment("Language code to load, set to \"default\" to use default values")
    private String languageCode = "default";

    @Comment("Which item players should shift+right click with to pick animals up.")
    private String pickupItem = "LEAD";

    @Comment("How many mobs players are able to hold at once")
    private int maxAmount = 5;

    @Comment({"Entities that are possible to pick up.", "For a list of entities, visit https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/EntityType.html"})
    @ElementType(EntityType.class)
    private List<EntityType> carriableEntities = Arrays.asList(
            EntityType.SHEEP,
            EntityType.COW,
            EntityType.PIG,
            EntityType.CHICKEN
    );

    public String getLanguageCode() {
        return languageCode;
    }

    public Material getPickupItem() {
        return Material.matchMaterial(pickupItem);
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public List<EntityType> getCarriableEntities() {
        return carriableEntities;
    }
}
