import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class llamarequest {
    public static void main(String[] args) {

        String prompt = "your prompt here.";

        new Thread(() -> {
            System.out.print(chatGPT(prompt + "Answer only in one sentence" ));
        }).start();
    }

    public static String chatGPT(String message) {
        String url = "http://0.0.0.0:8888/v1/chat/completions";
                String apiKey = "sk-no-key-required"; 
                String model = "gpt-3.5-turbo";

                try {
                    // Create the HTTP POST request
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Authorization", "Bearer " + apiKey);
                    con.setRequestProperty("Content-Type", "application/json");

                    // Build the request body
                    String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
                    con.setDoOutput(true);
                    OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                    writer.write(body);
                    writer.flush();
                    writer.close();

                    // Get the response
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // returns the extracted contents of the response.
                    return extractContentFromResponse(response.toString());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
    }
    
            public static String extractContentFromResponse(String response) {
                int startMarker = response.indexOf("content") + 11; 
                int endMarker = response.indexOf("\"", startMarker); 
                return response.substring(startMarker, endMarker); 
            }
}