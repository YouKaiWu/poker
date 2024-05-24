package utility;

import java.util.HashMap;
import java.util.Map;

public class Response {
    int status;
    String text;
    Map<String, String> fields;

    public Response(int s, String t) {
        status = s;
        text = t;
        fields = new HashMap<>();

        if (status != 0)
            parse(t);
    }

    private void parse(String t) {
        t = t.substring(1, t.length() - 1);
        String[] items = t.split(",");
        for (String item : items) {
            String[] data = item.split(":");
            String key = data[0].substring(1, data[0].length() - 1);
            String val = data[1].substring(1, data[1].length() - 1);
            fields.put(key, val);
        }
    }

    public String toString() {
        return text;
    }

    public String get(String key) {
        return fields.get(key);
    }

    public int getStatus() {
        return status;
    }
}
