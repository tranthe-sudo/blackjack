import gameComponent.Card;
import gameComponent.Hand;
import gameComponent.Rank;
import gameComponent.Suit;
import org.json.JSONArray;

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
//        System.out.println("trung".hashCode());
//        System.out.println("trung".hashCode());


//        public JSONArray cardsToJSONArray() {
//            String[] arrOfStringCards = new String[hand.getListCards().size()];
//            int index = 0;
//
//            for ( Card card : hand.getListCards() ) {
//                arrOfStringCards[index] = card.toString();
//                index++;
//            }
//
//            return new JSONArray(arrOfStringCards);
//        }

//        Hand hand = new Hand();
//        System.out.println(hand.getListCards().size());
//        System.out.println(hand.getListCards());
//        hand.addToHand(new Card(Suit.Diamond, Rank.Ace));
//        hand.addToHand(new Card(Suit.Diamond, Rank.Jack));
//
//        System.out.println(hand.getListCards().size());
//        System.out.println(hand.getListCards());
//
//        String[] arrOfStringCards = new String[hand.getListCards().size()];
//        int index = 0;
//
//        for ( Card card : hand.getListCards() ) {
//            arrOfStringCards[index] = card.toString();
//            index++;
//        }
//
//
//        System.out.println(new JSONArray(arrOfStringCards));


        Card card = new Card(Suit.Spade, Rank.Ace);
        System.out.println(card.isAce());
    }
}
