package org.app.BookAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.app.Object.Book;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class GoogleBookSearch {
    private static final String API_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String API_KEY = "AIzaSyByYj71afEARbjzUT9bIfeECg1zTjwjais"; // API key

    public static Book searchBook(String query) {
        try {
            // Construct the URL with the API key
            String searchUrl = API_BASE_URL + query + "&key=" + API_KEY;
            URL url = new URL(searchUrl);

            // Send HTTP request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check HTTP response
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error Code: " + responseCode);
            }

            // Read data from API
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject responseJson = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray items = responseJson.getAsJsonArray("items");

            if (items == null || items.size() == 0) {
                return null;
            }

            // Extract information from the first book result
            JsonObject volumeInfo = items.get(0).getAsJsonObject().getAsJsonObject("volumeInfo");
            String title = volumeInfo.get("title").getAsString();
            JsonArray authorsJson = volumeInfo.has("authors")
                    ? volumeInfo.getAsJsonArray("authors") : null;

            List<String> authors = new ArrayList<>();
            if (authorsJson != null) {
                for (int i = 0; i < authorsJson.size(); i++) {
                    authors.add(authorsJson.get(i).getAsString());
                }
            }

            String publisher = volumeInfo.has("publisher")
                    ? volumeInfo.get("publisher").getAsString() : "Unknown";
            String isbn = null;

            // Extract ISBN
            if (volumeInfo.has("industryIdentifiers")) {
                JsonArray identifiers = volumeInfo.getAsJsonArray("industryIdentifiers");
                for (int i = 0; i < identifiers.size(); i++) {
                    JsonObject identifier = identifiers.get(i).getAsJsonObject();
                    if ("ISBN_13".equals(identifier.get("type").getAsString())) {
                        isbn = identifier.get("identifier").getAsString();
                        break;
                    }
                }
            }

            String description = volumeInfo.has("description")
                    ? volumeInfo.get("description").getAsString() : "No description available";

            String thumbnail = volumeInfo.has("imageLinks")
                    ? volumeInfo.getAsJsonObject("imageLinks")
                    .get("thumbnail").getAsString() : null;

            String content = volumeInfo.has("content")
                    ? volumeInfo.get("content").getAsString() : "No content available";

            String catalog = volumeInfo.has("categories")
                    ? volumeInfo.getAsJsonArray("categories").get(0).getAsString() : "Unknown";

            Double price = volumeInfo.has("retailPrice")
                    ? volumeInfo.getAsJsonObject("retailPrice").get("amount").getAsDouble() : 0.0;

            // Return the Book object
            return new Book.Builder()
                    .setTitle(title)
                    .setAuthor(authors.getFirst())
                    .setPublisher(publisher)
                    .setIsbn(isbn)
                    .setDescription(description)
                    .setContent(content)
                    .setCatalog(catalog)
                    .setPrice(price)
                    .setImagePath(thumbnail)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
