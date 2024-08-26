package com.svo.snowp.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TelegramNotifier {
    private final String apiToken;
    private final String chatId;

    public TelegramNotifier(JavaPlugin plugin) {
        FileConfiguration config = plugin.getConfig();
        this.apiToken = config.getString("telegram.api_token");
        this.chatId = config.getString("telegram.chat_id");
    }

    public void sendMessage(String message) {
        try {
            String urlString = "https://api.telegram.org/bot" + apiToken + "/sendMessage?chat_id=" + chatId + "&text=" + java.net.URLEncoder.encode(message, "UTF-8");
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (OutputStream os = connection.getOutputStream(); PrintWriter writer = new PrintWriter(os)) {
                writer.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                plugin.getLogger().warning("Failed to send message to Telegram. Response code: " + responseCode);
            }
        } catch (Exception e) {
            plugin.getLogger().warning("Failed to send message to Telegram: " + e.getMessage());
        }
    }
}
