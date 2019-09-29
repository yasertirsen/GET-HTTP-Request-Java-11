import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;




public class SendHTTPRequest {

	public static void main(String[] args) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/albums")).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
		.thenApply(HttpResponse::body)
		.thenApply(SendHTTPRequest::parse)
		.join();
	}
	
	public static String parse(String responseBody) {
		System.out.println(responseBody);
		JSONArray albums = new JSONArray(responseBody);
		
		for(int i = 0; i<albums.length(); i++) {
			JSONObject album = albums.getJSONObject(i);
			int id = album.getInt("id");
			int userId = album.getInt("userId");
			String title = album.getString("title");
			System.out.println(id + " " + title + " " + userId);
		}
		return null;
	}

}
