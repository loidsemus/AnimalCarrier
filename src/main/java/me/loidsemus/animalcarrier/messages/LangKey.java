package me.loidsemus.animalcarrier.messages;

import me.loidsemus.pluginlib.Translatable;

public enum LangKey implements Translatable {
    PREFIX("&a[AnimalCarrier]&r"),
    ANIMAL_PICKED_UP("You picked up &a{name}&r", "name"),
    NO_ANIMALS_CARRIED("You're not carrying any animals"),
    MAX_AMOUNT_REACHED("You can only carry &a{max}&r animals at once!", "max");

    private final String defaultValue;
    private final String[] args;

    LangKey(String defaultValue, String... args) {
        this.defaultValue = defaultValue;
        this.args = args;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public String getKey() {
        return toString().toLowerCase();
    }
}
