package com.ericlmao.tools.command;

import com.ericlmao.tools.core.Locale;
import com.google.common.collect.Sets;
import games.negative.alumina.command.Command;
import games.negative.alumina.command.CommandProperties;
import games.negative.alumina.command.Context;
import games.negative.alumina.event.Events;
import games.negative.alumina.util.NumberUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class CmdMenuDebug extends Command {

    private final Set<UUID> cache = Sets.newHashSet();

    public CmdMenuDebug() {
        super(CommandProperties.builder().name("menudebug").build());

        Events.listen(PlayerQuitEvent.class, event -> cache.remove(event.getPlayer().getUniqueId()));

        Events.listen(InventoryClickEvent.class, EventPriority.HIGHEST, event -> {
            if (!(event.getWhoClicked() instanceof Player player) || !cache.contains(player.getUniqueId())) return;

            Locale.MENU_DEBUG.send(player, "%slot%", NumberUtil.decimalFormat(event.getSlot()));
        });
    }

    @Override
    public void execute(@NotNull Context context) {
        Player player = context.player().orElseThrow();
        UUID uuid = player.getUniqueId();

        boolean contains = cache.contains(uuid);
        if (contains) {
            cache.remove(uuid);

            Locale.MENU_DEBUG_DISABLED.send(player);
            return;
        }

        cache.add(uuid);

        Locale.MENU_DEBUG_ENABLED.send(player);
    }
}
