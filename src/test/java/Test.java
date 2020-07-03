import jdk.swing.interop.SwingInterOpUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
//        JSONObject jsonObject = new JSONObject("{\"grade\": 100}");
//        JSONObject jsonObject1 = new JSONObject("{\"NAME\": \"Trung\"}");
//
//
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.put(100);
//        jsonArray.put("trung");
//        jsonArray.put(5);
//        jsonArray.put(64);
//        jsonArray.put("Tran");
//
//        jsonObject.put("array", jsonArray);
//        jsonObject.put("obj1", jsonObject1);
//
//        System.out.println(jsonObject.getJSONObject("obj1"));
//        System.out.println(jsonArray);
//        System.out.println(jsonObject.toString(4));

        LinkedList<String> ll = new LinkedList<>();

        ll.addLast("Ace1");
        ll.addFirst("King");
        ll.addFirst("King");
        ll.addFirst("Queen");
        ll.addLast("Ace2");
        ll.addFirst("10");
        ll.addFirst("8");


        for (String item : ll ) {
            System.out.println(item);
        }
    }
}
