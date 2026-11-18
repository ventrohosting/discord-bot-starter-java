package com.ventrohosting.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "ping" -> event.reply("Pong! " + event.getJDA().getGatewayPing() + " ms").queue();
            case "info" -> {
                Guild guild = event.getGuild();
                if (guild == null) {
                    event.reply("Dieser Befehl funktioniert nur auf einem Server.").setEphemeral(true).queue();
                    return;
                }
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle(guild.getName())
                        .addField("Mitglieder", String.valueOf(guild.getMemberCount()), true);
                event.replyEmbeds(embed.build()).queue();
            }
            default -> { }
        }
    }
}
