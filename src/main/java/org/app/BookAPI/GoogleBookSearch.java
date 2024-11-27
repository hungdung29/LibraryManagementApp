package org.app.BookAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.app.Object.Book;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleBookSearch {
    private static final String API_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String API_KEY = "AIzaSyByYj71afEARbjzUT9bIfeECg1zTjwjais";

    public static Book searchBook(String query) {
        try {
            JsonObject responseJson = getApiResponse(query);
            JsonArray items = responseJson.getAsJsonArray("items");

            if (items == null || items.size() == 0) {
                return null;
            }

            JsonObject volumeInfo = items.get(0).getAsJsonObject().getAsJsonObject("volumeInfo");
            return extractBookFromVolumeInfo(volumeInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JsonObject getApiResponse(String query) throws Exception {
        String searchUrl = API_BASE_URL + query + "&key=" + API_KEY;
        URL url = new URL(searchUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HTTP GET Request Failed with Error Code: " + responseCode);
        }

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        return JsonParser.parseReader(reader).getAsJsonObject();
    }

    private static Book extractBookFromVolumeInfo(JsonObject volumeInfo) {
        String title = volumeInfo.get("title").getAsString();
        String authors = extractAuthors(volumeInfo);
        String publisher = volumeInfo.has("publisher")
                ? volumeInfo.get("publisher").getAsString() : "Unknown";
        String isbn = extractIsbn(volumeInfo);
        String description = volumeInfo.has("description")
                ? volumeInfo.get("description").getAsString() : "No description available";
        String thumbnail = volumeInfo.has("imageLinks")
                ? volumeInfo.getAsJsonObject("imageLinks").get("thumbnail").getAsString() : null;
        String content = volumeInfo.has("content")
                ? volumeInfo.get("content").getAsString() : "No content available";
        String catalog = volumeInfo.has("categories")
                ? volumeInfo.getAsJsonArray("categories").get(0).getAsString() : "Unknown";
        Double price = volumeInfo.has("retailPrice")
                ? volumeInfo.getAsJsonObject("retailPrice").get("amount").getAsDouble() : 0.0;

        return new Book.Builder()
                .setTitle(title)
                .setAuthor(authors)
                .setPublisher(publisher)
                .setIsbn(isbn)
                .setDescription(description)
                .setContent(content)
                .setCatalog(catalog)
                .setPrice(price)
                .setImagePath(thumbnail)
                .build();
    }

    private static String extractAuthors(JsonObject volumeInfo) {
        if (!volumeInfo.has("authors")) {
            return "";
        }

        JsonArray authorsJson = volumeInfo.getAsJsonArray("authors");
        StringBuilder authors = new StringBuilder();
        for (int i = 0; i < authorsJson.size(); i++) {
            authors.append(authorsJson.get(i).getAsString());
            if (i < authorsJson.size() - 1) {
                authors.append(", ");
            }
        }
        return authors.toString();
    }

    private static String extractIsbn(JsonObject volumeInfo) {
        if (!volumeInfo.has("industryIdentifiers")) {
            return null;
        }

        JsonArray identifiers = volumeInfo.getAsJsonArray("industryIdentifiers");
        for (int i = 0; i < identifiers.size(); i++) {
            JsonObject identifier = identifiers.get(i).getAsJsonObject();
            if ("ISBN_13".equals(identifier.get("type").getAsString())) {
                return identifier.get("identifier").getAsString();
            }
        }
        return null;
    }
}