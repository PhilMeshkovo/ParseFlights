import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class main {

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
          if (jsonObject1.get("fromCity").equals("Moscow") &&
              jsonObject1.get("toCity").equals("Khabarovsk")) {
            jsonObjectList.add(jsonObject1);
          }
        }
        long max = 0;
        long min = 0;
        long sum = 0;
        for (int i = 0; i < jsonObjectList.size(); i++) {
          if (i == 1) {
            max = (long) jsonObjectList.get(i).get("price");
            min = (long) jsonObjectList.get(i).get("price");
            sum += (long) jsonObjectList.get(i).get("price");
          } else {
            sum += (long) jsonObjectList.get(i).get("price");
            if ((long) jsonObjectList.get(i).get("price") > max) {
              max = (long) jsonObjectList.get(i).get("price");
            }
            if ((long) jsonObjectList.get(i).get("price") < min) {
              min = (long) jsonObjectList.get(i).get("price");
            }
          }


        }
        System.out.println("Максимальная цена " + max);
        System.out.println("Минимальная цена " + min);
        System.out.println("Средняя цена " + sum / jsonObjectList.size());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
