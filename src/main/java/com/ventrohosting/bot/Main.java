package com.ventrohosting.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String token = System.getenv("DISCORD_TOKEN");
        if (token == null || token.isBlank()) {
            System.err.println("Kein Token gesetzt: Die Umgebungsvariable DISCORD_TOKEN fehlt.");
            System.exit(1);
        }

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new CommandListener())
                .build();
        jda.awaitReady();

        // Registriert die Slash-Befehle global; neue Befehle erscheinen nach kurzer Zeit.
        jda.updateCommands().addCommands(
                Commands.slash("ping", "Antwortet mit Pong und der Latenz"),
                Commands.slash("info", "Zeigt Informationen zu diesem Server").setContexts(InteractionContextType.GUILD)
        ).queue();

        System.out.println("Bot ist online als " + jda.getSelfUser().getName());
    }
}
