package lance5057.tDefense;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class TD_Commands extends CommandBase implements ICommand {
    private final List<String> aliases;
    private final List<String> commands;

    public TD_Commands() {
        aliases = new ArrayList<>();

        aliases.add("TinkersDefense");
        aliases.add("TDefense");
        aliases.add("TD");

        commands = new ArrayList<>();

        commands.add("reloadRenderers");
        commands.add("toggleTransparency");
        commands.add("toggleDebugMode");
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public int compareTo(Object arg0) {
        return 0;
    }

    @Override
    public String getCommandName() {
        return "TinkersDefense";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "TinkersDefense <text>";
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List getCommandAliases() {
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        final World world = p_71515_1_.getEntityWorld();
        if (world.isRemote) {
            if (p_71515_2_[0].equals("reloadRenderers")) {
                p_71515_1_.addChatMessage(new ChatComponentText("§9[TDefense]§f - Reloading All Renderers..."));
                reloadModels();
            } else if (p_71515_2_[0].equals("toggleTransparency")) {
                TinkersDefense.config.transparency = !TinkersDefense.config.transparency;
                if (TinkersDefense.config.transparency) {
                    p_71515_1_.addChatMessage(new ChatComponentText("§9[TDefense]§f - Transparency on."));
                } else {
                    p_71515_1_.addChatMessage(new ChatComponentText("§9[TDefense]§f - Transparency off."));
                }

            } else if (p_71515_2_[0].equals("toggleDebugMode")) {
                TinkersDefense.config.debug = !TinkersDefense.config.debug;
                if (TinkersDefense.config.debug) {
                    p_71515_1_.addChatMessage(new ChatComponentText("§9[TDefense]§f - Debug Mode on."));
                } else {
                    p_71515_1_.addChatMessage(new ChatComponentText("§9[TDefense]§f - Debug Mode off."));
                }

            } else {
                p_71515_1_.addChatMessage(new ChatComponentText("§c[TDefense]§f - Invalid Command"));
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return commands;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    public void reloadModels() {
        TinkersDefense.proxy.registerRenderers();
    }
}
