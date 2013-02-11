package prj.pingback.utils;

import java.util.HashMap;
import java.util.Map;

public class ParseUtils
{
    public static Map<String, String> parseResponse(String response)
    {
        Map<String, String> nameValuePairs = new HashMap<>();
        String[] splits = response.split("&");
        for (String s : splits)
        {
            String[] parts = s.split("=");
            if (parts.length == 2)
            {
                nameValuePairs.put(parts[0], parts[1]);
            }
        }
        return nameValuePairs;
    }
}
