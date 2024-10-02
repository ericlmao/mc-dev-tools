package com.ericlmao.tools.command;

import com.ericlmao.tools.core.Locale;
import com.ericlmao.tools.core.util.MathUtil;
import com.ericlmao.tools.core.util.SoundUtil;
import games.negative.alumina.command.Command;
import games.negative.alumina.command.CommandProperties;
import games.negative.alumina.command.Context;
import games.negative.alumina.command.TabContext;
import games.negative.alumina.util.NumberUtil;
import games.negative.alumina.util.TabCompleteUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class CmdPlaySound extends Command {

    public CmdPlaySound() {
        super(CommandProperties.builder().name("playsound").aliases(List.of("sound")).params(List.of("sound")).build());
    }

    @Override
    public void execute(@NotNull Context context) {
        Player player = context.player().orElseThrow();

        Sound sound = context.argument(0).map(s -> Sound.valueOf(s.toUpperCase())).orElse(null);
        if (sound == null) {
            Locale.PLAY_SOUND_INVALID_SOUND.send(player, "sound", context.argument(0).orElseThrow());
            return;
        }

        float volume = context.argument(1).map(NumberUtil::getFloat).orElse(1F);
        float pitch = MathUtil.minMax(context.argument(2).map(NumberUtil::getFloat).orElse(1F), 0F, 2F);

        player.playSound(player, sound, volume, pitch);

        Locale.PLAY_SOUND.send(player, "%sound%", SoundUtil.formatName(sound), "%volume%", NumberUtil.decimalFormat(volume), "%pitch%", NumberUtil.decimalFormat(pitch));
    }

    @Override
    public List<String> onTabComplete(@NotNull TabContext context) {
        int index = context.index();

        if (index == 1) {
            List<String> list = Arrays.stream(Sound.values()).map(sound -> sound.name().toUpperCase()).toList();

            return TabCompleteUtil.getSimilarStrings(list, context.current());
        }

        return List.of();
    }
}
