package me.loidsemus.animalcarrier;

import de.exlll.configlib.configs.yaml.YamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import me.loidsemus.animalcarrier.config.MainConfig;
import me.loidsemus.animalcarrier.listeners.InteractListener;
import me.loidsemus.animalcarrier.listeners.JoinLeaveListener;
import me.loidsemus.animalcarrier.messages.LangKey;
import me.loidsemus.pluginlib.Messages;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AnimalCarrier extends JavaPlugin {

    private MainConfig mainConfig;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        loadConfigAndMessages();

        playerManager = new PlayerManager();
        // Gotta support /reload :)
        if (getServer().getOnlinePlayers().size() > 0) {
            getServer().getOnlinePlayers().forEach(playerManager::initPlayer);
        }

        getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
    }

    private void loadConfigAndMessages() {
        YamlConfiguration.YamlProperties properties = YamlConfiguration.YamlProperties.builder()
                .setFormatter(FieldNameFormatters.LOWER_UNDERSCORE)
                .build();

        mainConfig = new MainConfig(new File(getDataFolder(), "config.yml").toPath(), properties);
        mainConfig.loadAndSave();

        Messages.load(getDataFolder(), mainConfig.getLanguageCode(), LangKey.values(), LangKey.PREFIX);
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
