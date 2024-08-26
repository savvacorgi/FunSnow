package com.svo.snowp.utils;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TelegramNotifier {
    private final JavaPlugin plugin; // Добавляем поле для хранения ссылки на плагин
    private final String apiToken;
    private final String chatId;

    public TelegramNotifier(JavaPlugin plugin) {
        this.plugin = plugin; // Инициализируем поле
        this.apiToken = plugin.getConfig().getString("telegram.api-token");
        this.chatId = plugin.getConfig().getString("telegram.chat-id");
    }

    public void sendNotification(String message) {
        try {
            String urlString = "https://api.telegram.org/bot" + apiToken + "/sendMessage";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String postData = String.format(
                    "chat_id=%s&text=%s",
                    chatId,
                    java.net.URLEncoder.encode(message, StandardCharsets.UTF_8.toString())
            );

            try (OutputStream os = connection.getOutputStream()) {
                os.write(postData.getBytes(StandardCharsets.UTF_8));
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
