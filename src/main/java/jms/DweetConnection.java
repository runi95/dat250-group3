package jms;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.logging.Logger;

//import beans.AuctionDao;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.inject.Inject;

public class DweetConnection {

//    @Inject
//    private AuctionDao auctionDao;
//
//    private String dweetEndPoint = "dweet.io";
//    private JsonParser parser = new JsonParser();
//    private String userEndpoint = "g2018_03";
//
//    public DweetConnection() {
//    }
//
//    public boolean publish(JsonElement content) throws IOException {
//        if (userEndpoint == null || content == null)
//            throw new NullPointerException();
//
//        userEndpoint = URLEncoder.encode(userEndpoint, "UTF-8");
//        URL url = new URL("http" + "://" + dweetEndPoint + "/dweet/for/" + userEndpoint);
//        Logger logger = Logger.getLogger(getClass().getName());
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
//        connection.setRequestMethod("POST");
//        connection.setDoInput(true);
//        connection.setDoOutput(true);
//
//        PrintWriter out = new PrintWriter(connection.getOutputStream());
//        out.println(content.toString());
//        out.flush();
//        out.close();
//
//        JsonObject response = readResponse(connection.getInputStream());
//        logger.info("DTWEET Platform response: " + response.toString());
//        connection.disconnect();
//
//        return (response.has("this") && response.get("this").getAsString().equals("succeeded"));
//    }
//
//    private JsonObject readResponse(InputStream in) {
//        Scanner scan = new Scanner(in);
//        StringBuilder sn = new StringBuilder();
//        while (scan.hasNext())
//            sn.append(scan.nextLine()).append('\n');
//        scan.close();
//        return parser.parse(sn.toString()).getAsJsonObject();
//    }
}
