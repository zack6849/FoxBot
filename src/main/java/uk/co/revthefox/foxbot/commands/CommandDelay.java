package uk.co.revthefox.foxbot.commands;

import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;
import uk.co.revthefox.foxbot.FoxBot;

public class CommandDelay extends Command
{
    private FoxBot foxbot;

    public CommandDelay(FoxBot foxbot)
    {
        super("delay", "command.delay");
        this.foxbot = foxbot;
    }

    @Override
    public void execute(final MessageEvent event, final String[] args)
    {
        User sender = event.getUser();

        if (args.length == 1)
        {
            try
            {
                foxbot.setMessageDelay(Long.valueOf(args[0]));
                foxbot.sendNotice(sender, String.format("Message delay set to %sms", foxbot.getMessageDelay()));
            }
            catch (NumberFormatException ex)
            {
                foxbot.sendNotice(sender, "That is not a number!");
                foxbot.setMessageDelay(foxbot.getConfig().getMessageDelay());
            }
            return;
        }
        foxbot.sendNotice(sender, String.format("Wrong number of args! Use %sdelay <number of milliseconds>", foxbot.getConfig().getCommandPrefix()));
    }
}