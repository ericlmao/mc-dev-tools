package com.ericlmao.tools.core.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class SoundUtil {

    @NotNull
    public String formatName(@NotNull Sound sound) {
        String[] spaces = sound.name().split("_");

        StringBuilder builder = new StringBuilder();

        int iterations = 0;
        int length = spaces.length;
        for (String space : spaces) {
            iterations++;

            builder.append(space.substring(0, 1).toUpperCase()).append(space.substring(1).toLowerCase());
            if (iterations == length) break;

            builder.append(" ");
        }

        return builder.toString();
    }

}
