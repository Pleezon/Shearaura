package de.techgamez.pleezon.utils;

import net.labymod.settings.Settings;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;

public class CategoryElement extends ControlElement {

    @Override
    public int getObjectWidth() {
        return 0;
    }

    public CategoryElement(String displayName, Material iconData) {
        super(displayName, new IconData(iconData));
        this.setSettingEnabled(true);
        this.setHoverable(true);
    }
    public Settings add(SettingsElement element) {
        return getSubSettings().add(element);
    }
}