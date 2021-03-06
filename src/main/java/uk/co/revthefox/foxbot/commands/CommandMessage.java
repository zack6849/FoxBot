package uk.co.revthefox.foxbot.commands;

import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;
import uk.co.revthefox.foxbot.FoxBot;

public class CommandMessage extends Command
{
    private FoxBot foxbot;

    public CommandMessage(FoxBot foxbot)
    {
        super("pm", "command.message", "message");
        this.foxbot = foxbot;
    }

    @Override
    public void execute(final MessageEvent event, final String[] args)
    {
        User sender = event.getUser();

        if (args.length > 1)
        {
            User target = foxbot.getUser(args[0]);
            StringBuilder message = new StringBuilder(args[1]);

            for (int arg = 2; arg < args.length; arg++)
            {
                message.append(" ").append(args[arg]);
            }

            foxbot.sendMessage(target, foxbot.getUtils().colourise(message.toString()));
            return;
        }
        foxbot.sendNotice(sender, String.format("Wrong number of args! Use %spm <user> <message>", foxbot.getConfig().getCommandPrefix()));
    }
}