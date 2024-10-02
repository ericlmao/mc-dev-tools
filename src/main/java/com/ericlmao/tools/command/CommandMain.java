package com.ericlmao.tools.command;

import com.ericlmao.tools.core.Perm;
import games.negative.alumina.command.Command;
import games.negative.alumina.command.CommandProperties;
import games.negative.alumina.command.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandMain extends Command {

    public CommandMain() {
        super(CommandProperties.builder().name("devtools").aliases(List.of("dev")).permissions(List.of(Perm.ADMIN)).smartTabComplete(true).playerOnly(true).build());

        addSubCommand(new CmdMenuDebug());
        addSubCommand(new CmdPlaySound());
    }

    @Override
    public void execute(@NotNull Context context) {

    }
}
