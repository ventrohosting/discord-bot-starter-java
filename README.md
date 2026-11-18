# Discord-Bot-Vorlage (Java, JDA)

Solide Ausgangsbasis für einen eigenen Discord-Bot in Java 21: Slash-Befehle mit JDA 6, Maven-Build zu einer ausführbaren JAR, Dockerfile und CI. MIT-Lizenz, deutsch dokumentiert.

*English: A small JDA 6 starter (Java 21, Maven) with slash commands, a runnable fat JAR, Dockerfile and CI. German docs, MIT licensed.*

## Voraussetzungen

- Java 21 und Maven 3.9 oder neuer (oder nur Docker)
- Eine Anwendung mit Bot im [Discord Developer Portal](https://discord.com/developers/applications)

## Schnellstart

1. Anwendung anlegen, unter **Bot** den Token zurücksetzen und kopieren.
2. Unter **OAuth2 › URL Generator** die Scopes `bot` und `applications.commands` wählen und den Bot auf deinen Server einladen.
3. Bauen und starten:

```bash
git clone https://github.com/ventrohosting/discord-bot-starter-java.git
cd discord-bot-starter-java
mvn package
export DISCORD_TOKEN=dein-token     # PowerShell: $env:DISCORD_TOKEN="dein-token"
java -jar target/bot.jar
```

In der Konsole erscheint „Bot ist online als …“. Auf deinem Server funktionieren jetzt `/ping` und `/info`.

Der Token gehört nie in den Code und nie in ein öffentliches Repository.

## Projektaufbau

```
pom.xml                                   JDA 6, Shade-Plugin baut target/bot.jar
src/main/java/com/ventrohosting/bot/
  Main.java                               Start, registriert die Slash-Befehle
  CommandListener.java                    Beantwortet /ping und /info
```

## Eigenen Befehl hinzufügen

1. In `Main.java` den Befehl registrieren: `Commands.slash("hallo", "Sagt Hallo")`.
2. In `CommandListener.java` einen weiteren `case "hallo" -> event.reply("Hallo " + event.getUser().getName() + "!").queue();` ergänzen.
3. Neu bauen und starten.

## Mit Docker starten

```bash
docker build -t mein-bot .
docker run -d --name mein-bot --restart unless-stopped -e DISCORD_TOKEN=dein-token mein-bot
```

## Rund um die Uhr online

Auf dem eigenen PC ist der Bot nur online, solange der Rechner läuft. Für Dauerbetrieb brauchst du einen Rechner, der nie ausgeht: einen Raspberry Pi, einen eigenen Server oder einen Bot-Hoster. Du lädst dort die gebaute `bot.jar` hoch und setzt `DISCORD_TOKEN`.

Offenlegung: Dieses Repository pflegt [VentroHosting](https://ventrohosting.com), ein Anbieter für Discord-Bot-Hosting. Die Vorlage ist unabhängig von jedem Anbieter nutzbar. Passende Anleitungen auf Deutsch:

- [Discord-Bot mit Java hosten (JDA)](https://ventrohosting.com/anleitungen/discord-bot-java-hosten)
- [Discord-Bot erstellen und einladen](https://ventrohosting.com/anleitungen/discord-bot-erstellen)
- [Discord-Bot kostenlos hosten: Möglichkeiten und Grenzen](https://ventrohosting.com/anleitungen/discord-bot-kostenlos-hosten)
- [Discord-Bot 24/7 online halten](https://ventrohosting.com/anleitungen/discord-bot-247-online)
- [Discord-Bot-Hosting vergleichen: Checkliste](https://ventrohosting.com/anleitungen/discord-bot-hosting-vergleichen)

## Häufige Fehler

| Meldung | Ursache |
|---|---|
| `Kein Token gesetzt` | `DISCORD_TOKEN` ist nicht gesetzt. |
| `no main manifest attribute` | Nicht die falsche JAR starten: die gebaute Datei ist `target/bot.jar`. |
| `DisallowedIntentsException` | Der Code nutzt einen Intent, der im Developer Portal unter **Bot** nicht aktiv ist. Diese Vorlage braucht nur Standard-Intents. |
| Slash-Befehle erscheinen nicht | Kurz warten, Discord neu laden und prüfen, ob der Bot mit `applications.commands` eingeladen wurde. |

Weitere Fehler und Lösungen: [Discord-Bot offline? Häufige Fehler](https://ventrohosting.com/anleitungen/discord-bot-offline-fehler).

Hinweis: VentroHosting ist ein unabhängiger Anbieter und nicht mit Discord Inc. verbunden.
