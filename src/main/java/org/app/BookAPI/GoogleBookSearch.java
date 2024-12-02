package org.app.BookAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.Book;

public class GoogleBookSearch {
    private static final String API_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String API_KEY = "AIzaSyByYj71afEARbjzUT9bIfeECg1zTjwjais";

    /**
	* Get book information from Google Books API.
	*
	* @param query search query.
	* @return list of books.
	*/
    public static ObservableList<Book> getBookFromAPI(String query) {
	   ObservableList<Book> books = FXCollections.observableArrayList();
	   try {
		  JsonObject responseJson = getApiResponse(query);
		  JsonArray items = responseJson.getAsJsonArray("items");

		  if (items==null || items.isEmpty()) {
			 System.out.println("No book found");
			 return null;
		  }

		  for (int i = 0; i < items.size(); i++) {
			 JsonObject volumeInfo = items.get(i).getAsJsonObject().getAsJsonObject(
				    "volumeInfo");
			 Book book = extractBookFromVolumeInfo(volumeInfo);
			 books.add(book);
		  }
		  return books;
	   } catch (Exception e) {
		  e.printStackTrace();
		  return null;
	   }
    }

    /**
	* Get API response from Google Books API.
	*
	* @param query search query.
	* @return JsonObject.
	* @throws Exception if connection failed.
	*/
    public static JsonObject getApiResponse(String query) throws Exception {
	   String searchUrl = API_BASE_URL + query + "&key=" + API_KEY;
	   URL url = new URL(searchUrl);

	   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	   connection.setRequestMethod("GET");
	   connection.connect();

	   int responseCode = connection.getResponseCode();
	   if (responseCode!=200) {
		  throw new RuntimeException("HTTP GET Request Failed with Error Code: " + responseCode);
	   }

	   InputStreamReader reader = new InputStreamReader(connection.getInputStream());
	   return JsonParser.parseReader(reader).getAsJsonObject();
    }

    /**
	* Extract book information from volumeInfo.
	*
	* @param volumeInfo JsonObject
	* @return book
	*/
    public static Book extractBookFromVolumeInfo(JsonObject volumeInfo) {
	   String title = volumeInfo.get("title").getAsString();
	   String authors = extractAuthors(volumeInfo);
	   String publisher = volumeInfo.has("publisher")
			 ? volumeInfo.get("publisher").getAsString():"Unknown";
	   String isbn = extractIsbn(volumeInfo);
	   String description = volumeInfo.has("description")
			 ? volumeInfo.get("description").getAsString():"No description available";
	   String thumbnail = volumeInfo.has("imageLinks")
			 ? volumeInfo.getAsJsonObject("imageLinks").get("thumbnail").getAsString():null;
	   String content = volumeInfo.has("language")
			 ? volumeInfo.get("language").getAsString():"No content available";
	   String catalog = volumeInfo.has("categories")
			 ? volumeInfo.getAsJsonArray("categories").get(0).getAsString():"Unknown";
	   Double price = volumeInfo.has("retailPrice")
			 ? volumeInfo.getAsJsonObject("retailPrice").get("amount").getAsDouble():0.0;

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

    /**
	* Extract authors from volumeInfo.
	*
	* @param volumeInfo JsonObject
	* @return
	*/
    private static String extractAuthors(JsonObject volumeInfo) {
	   if (!volumeInfo.has("authors")) {
		  return "Unknown";
	   }

	   JsonArray authorsJson = volumeInfo.getAsJsonArray("authors");
	   StringBuilder authors = new StringBuilder();
	   authors.append(authorsJson.get(0).getAsString());
	   for (int i = 1; i < authorsJson.size(); i++) {
		  authors.append(", ");
		  authors.append(authorsJson.get(i).getAsString());
	   }
	   return authors.toString();
    }

    /**
	* Extract ISBN from volumeInfo.
	*
	* @param volumeInfo JsonObject
	* @return book's ISBN
	*/
    private static String extractIsbn(JsonObject volumeInfo) {
	   if (!volumeInfo.has("industryIdentifiers")) {
		  return null;
	   }

	   JsonArray identifiers = volumeInfo.getAsJsonArray("industryIdentifiers");
	   for (int i = 0; i < identifiers.size(); i++) {
		  JsonObject identifier = identifiers.get(i).getAsJsonObject();
		  if ("ISBN_13".equals(identifier.get("type").getAsString())) {
			 String isbn = identifier.get("identifier").getAsString();
			 for (int j = 0; j < isbn.length(); j++) {
				if (isbn.charAt(j) >= '0' && isbn.charAt(j) <= '9') {
				    isbn = isbn.substring(j);
				    return isbn;
				}
			 }
			 return isbn;
		  }
	   }
	   String isbn = identifiers.get(0).getAsJsonObject().get("identifier").getAsString();
	   for (int j = 0; j < isbn.length(); j++) {
		  if (isbn.charAt(j) >= '0' && isbn.charAt(j) <= '9') {
			 isbn = isbn.substring(j);
			 return isbn;
		  }
	   }
	   return null;
    }
}