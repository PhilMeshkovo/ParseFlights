import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class main {

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    if (args.length == 1) {

      JSONParser parser = new JSONParser();
      try {

        Object obj = parser.parse(new FileReader(args[0]));

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray flights = (JSONArray) jsonObject.get("flights");

        List<JSONObject> jsonObjectList = new ArrayList<>();

        for (int i = 0; i < flights.size(); i++) {
          JSONObject jsonObject1 = (JSONObject) flights.get(i);
          jsonObjectList.add(jsonObject1);
        }
        long max = jsonObjectList.stream().filter(f -> f.get("fromCity").equals("Moscow") &&
            f.get("toCity").equals("Khabarovsk")).mapToLong(p -> (long) p.get("price")).max()
            .orElseThrow();

        long min = jsonObjectList.stream().filter(f -> f.get("fromCity").equals("Moscow") &&
            f.get("toCity").equals("Khabarovsk")).mapToLong(p -> (long) p.get("price")).min()
            .orElseThrow();
        System.out.println("Максимальная цена " + max);
        System.out.println("Минимальная цена " + min);
        System.out.println("Средняя цена " + (max + min) / 2);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }
}
