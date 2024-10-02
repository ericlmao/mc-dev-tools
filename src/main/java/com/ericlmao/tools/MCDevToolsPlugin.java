package com.ericlmao.tools;

import com.ericlmao.tools.command.CommandMain;
import com.ericlmao.tools.core.Locale;
import games.negative.alumina.AluminaPlugin;
import org.jetbrains.annotations.NotNull;

public final class MCDevToolsPlugin extends AluminaPlugin {

    private static MCDevToolsPlugin instance;

    @Override
    public void load() {
        instance = this;
    }

    @Override
    public void enable() {
        reload();

        registerCommand(new CommandMain());
    }

    public void reload() {
        Locale.init(this);
    }

    @Override
    public void disable() {

    }

    @NotNull
    public static MCDevToolsPlugin instance() {
        return instance;
    }

}
