package RestClient;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class RestClient {
    public static void main(String[] args) throws IOException {

        URL url = new URL("https://cat-fact.herokuapp.com/facts/random");


        InputStreamReader reader = new InputStreamReader(url.openStream());

        CatFact catFact = new Gson().fromJson(reader,CatFact.class);

        System.out.println(catFact.getText());

    }
}
