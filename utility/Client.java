package utility;

public class Client {
    private static String token = "";

    public boolean login(String account, String password) {
        String format = "{\"account\": \"%s\",\"password\": \"%s\"}";
        String body = String.format(format, account, password);

        Response res = Server.fetch("/api/login", "POST", body);
        if (res.getStatus() != 200) {
            System.err.println("[Error] Login failed");
            return false;
        }

        token = res.getString("token");
        return true;
    }
}
