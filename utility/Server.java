package utility;

import java.io.*;
import java.net.*;

public class Server {
    private static String url = "http://k8s-gpu.tail0ab30.ts.net:3000";

    public static Response fetch(String path, String method, String body) {
        return Server.fetch(path, method, "", body);
    }

    public static Response fetch(String path, String method, String token, String body) {
        StringBuilder result = new StringBuilder();
        int status = 0;
        try {
            URL url = new URL(Server.url + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);

            if (token.length() != 0)
                conn.setRequestProperty("Authorization", "Bearer " + token);

            if (body.length() != 0) {
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = body.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            status = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();

            conn.disconnect();
        } catch (Exception e) {
            return new Response(status, e.getMessage());
        }
        return new Response(status, result.toString());
    }

    public static void main(String[] args) {
        String format = "{\"account\": \"%s\",\"password\": \"%s\"}";
        String body = String.format(format, "user1", "user1");
        Response res = Server.fetch("/api/login", "POST", body);

        if (res.getStatus() == 200) {
            String token = res.getString("token");
            System.out.println("[Test] Login succeed");
            System.out.println("[Test] Token: " + token);
        } else {
            System.out.println("[Test] Login Failed");
        }
    }
}
