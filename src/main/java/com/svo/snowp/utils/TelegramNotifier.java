package com.svo.snowp.utils;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TelegramNotifier {

    private final JavaPlugin plugin;
    private final String apiToken;
    private final String chatId;

    public TelegramNotifier(JavaPlugin plugin, String apiToken, String chatId) {
        this.plugin = plugin;
        this.apiToken = apiToken;
        this.chatId = chatId;
    }

    public void notifyEventStarted(String title, String description) {
        sendMessageToTelegram(title + ": " + description);
    }

    private void sendMessageToTelegram(String message) {
        try {
            String urlString = "https://api.telegram.org/bot" + apiToken + "/sendMessage";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String payload = "chat_id=" + chatId + "&text=" + message;
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(payload);
            writer.flush();
            writer.close();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                plugin.getLogger().warning("Failed to send message to Telegram. Response code: " + responseCode);
            }
        } catch (Exception e) {
            plugin.getLogger().warning("Failed to send message to Telegram: " + e.getMessage());
        }
    }
}
