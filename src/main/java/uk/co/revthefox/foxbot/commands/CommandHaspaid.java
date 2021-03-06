package uk.co.revthefox.foxbot.commands;

import com.ning.http.client.AsyncHttpClient;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;
import uk.co.revthefox.foxbot.FoxBot;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandHaspaid extends Command
{
    private FoxBot foxbot;

    public CommandHaspaid(FoxBot foxbot)
    {
        super("haspaid", "command.ping", "mcpaid");
        this.foxbot = foxbot;
    }

    @Override
    public void execute(final MessageEvent event, final String[] args)
    {
        User sender = event.getUser();
        Channel channel = event.getChannel();

        if (args.length == 1)
        {
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

            try
            {
                channel.sendMessage(asyncHttpClient.prepareGet("https://minecraft.net/haspaid.jsp?user=" + args[0]).execute().get().getResponseBody().contains("true") ? foxbot.getUtils().colourise(String.format("(%s) The account \"%s\" is a &apremium&r Minecraft account!", foxbot.getUtils().munge(sender.getNick()), args[0])) : foxbot.getUtils().colourise(String.format("(%s) The account \"%s\" is &cNOT&r a premium Minecraft account!", foxbot.getUtils().munge(sender.getNick()), args[0])));
            }
            catch (Exception ex)
            {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                foxbot.sendMessage(channel, "Something went wrong...");
            }
            return;
        }
        foxbot.sendNotice(sender, String.format("Wrong number of args! Use %shaspaid <user>", foxbot.getConfig().getCommandPrefix()));
    }
}