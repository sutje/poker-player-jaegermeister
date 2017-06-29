package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    public void testBetRequest() throws Exception {

        JsonElement jsonElement = new JsonParser().parse("{\"key1\": \"value1\", \"key2\": \"value2\"}");

        assertEquals(0, Player.betRequest(jsonElement));
    }
    
    @Test
    public void testBetRequestWithFirstRound() throws Exception {

        JsonElement jsonElement = new JsonParser().parse(
        		"{"+
        	  "\"players\":["+
        	    "{"+
        	     "\"name\":\"Player 1\","+
        	     "\"stack\":1000,"+
        	      "\"status\":\"active\","+
        	      "\"bet\":10,"+
        	      "\"hole_cards\":[],"+
        	      "\"version\":\"Version name 1\","+
        	      "\"id\":0"+
        	    "},"+
        	    "{"+
        	      "\"name\":\"Player 2\","+
        	      "\"stack\":1000,"+
        	      "\"status\":\"active\","+
        	      "\"bet\":20,"+
        	      "\"hole_cards\":["+
           	   "{" +
               "\"rank\": \"3\"," +
               "\"suit\": \"spades\"" +
           "}" +
        	      "],"+
        	      "\"version\":\"Version name 2\","+
        	      "\"id\":1"+
        	    "}"+
        	  "],"+
        	  "\"tournament_id\":\"550d1d68cd7bd10003000003\","+
        	  "\"game_id\":\"550da1cb2d909006e90004b1\","+
        	  "\"round\":0,"+
        	  "\"bet_index\":0,"+
        	  "\"small_blind\":10,"+
        	  "\"orbits\":0,"+
        	  "\"dealer\":0,"+
        	  "\"minimum_raise\": 240,"+
        	  "\"in_action\": 1," +
        	  "\"community_cards\":["+
        	   "{" +
                  "\"rank\": \"4\"," +
                  "\"suit\": \"spades\"" +
              "}," +
              "{" +
                  "\"rank\": \"A\"," +
                  "\"suit\": \"hearts\"" +
              "}," +
              "{" +
                  "\"rank\": \"6\"," +
                  "\"suit\": \"clubs\"" +
              "}" +
        	  "],"+
        	  "\"current_buy_in\":340,"+
        	  "\"pot\":0"+
        	"}");

        assertEquals(560, Player.betRequest(jsonElement));

    }

    @Test
    public void testBetRequestWithPair() throws Exception {

        JsonElement jsonElement = new JsonParser().parse(
        		"{"+
        	  "\"players\":["+
        	    "{"+
        	     "\"name\":\"Player 1\","+
        	     "\"stack\":1000,"+
        	      "\"status\":\"active\","+
        	      "\"bet\":10,"+
        	      "\"hole_cards\":[],"+
        	      "\"version\":\"Version name 1\","+
        	      "\"id\":0"+
        	    "},"+
        	    "{"+
        	      "\"name\":\"Player 2\","+
        	      "\"stack\":1000,"+
        	      "\"status\":\"active\","+
        	      "\"bet\":20,"+
        	      "\"hole_cards\":["+
           	   "{" +
               "\"rank\": \"4\"," +
               "\"suit\": \"hearts\"" +
           "}" +
        	      "],"+
        	      "\"version\":\"Version name 2\","+
        	      "\"id\":1"+
        	    "}"+
        	  "],"+
        	  "\"tournament_id\":\"550d1d68cd7bd10003000003\","+
        	  "\"game_id\":\"550da1cb2d909006e90004b1\","+
        	  "\"round\":1,"+
        	  "\"bet_index\":0,"+
        	  "\"small_blind\":10,"+
        	  "\"orbits\":0,"+
        	  "\"dealer\":0,"+
        	  "\"minimum_raise\": 240,"+
        	  "\"in_action\": 1," +
        	  "\"community_cards\":["+
        	   "{" +
                  "\"rank\": \"4\"," +
                  "\"suit\": \"spades\"" +
              "}," +
              "{" +
                  "\"rank\": \"A\"," +
                  "\"suit\": \"hearts\"" +
              "}," +
              "{" +
                  "\"rank\": \"6\"," +
                  "\"suit\": \"clubs\"" +
              "}" +
        	  "],"+
        	  "\"current_buy_in\":340,"+
        	  "\"pot\":0"+
        	"}");

        assertEquals(560, Player.betRequest(jsonElement));

    }

    @Test
    public void testBetRequestWithoutPair() throws Exception {

        JsonElement jsonElement = new JsonParser().parse(
        		"{"+
        	  "\"players\":["+
        	    "{"+
        	     "\"name\":\"Player 1\","+
        	     "\"stack\":1000,"+
        	      "\"status\":\"active\","+
        	      "\"bet\":10,"+
        	      "\"hole_cards\":[],"+
        	      "\"version\":\"Version name 1\","+
        	      "\"id\":0"+
        	    "},"+
        	    "{"+
        	      "\"name\":\"Player 2\","+
        	      "\"stack\":1000,"+
        	      "\"status\":\"active\","+
        	      "\"bet\":20,"+
        	      "\"hole_cards\":["+
           	   "{" +
               "\"rank\": \"3\"," +
               "\"suit\": \"hearts\"" +
           "}" +
        	      "],"+
        	      "\"version\":\"Version name 2\","+
        	      "\"id\":1"+
        	    "}"+
        	  "],"+
        	  "\"tournament_id\":\"550d1d68cd7bd10003000003\","+
        	  "\"game_id\":\"550da1cb2d909006e90004b1\","+
        	  "\"round\":1,"+
        	  "\"bet_index\":0,"+
        	  "\"small_blind\":10,"+
        	  "\"orbits\":0,"+
        	  "\"dealer\":0,"+
        	  "\"minimum_raise\": 240,"+
        	  "\"in_action\": 1," +
        	  "\"community_cards\":["+
        	   "{" +
                  "\"rank\": \"4\"," +
                  "\"suit\": \"spades\"" +
              "}," +
              "{" +
                  "\"rank\": \"A\"," +
                  "\"suit\": \"hearts\"" +
              "}," +
              "{" +
                  "\"rank\": \"6\"," +
                  "\"suit\": \"clubs\"" +
              "}" +
        	  "],"+
        	  "\"current_buy_in\":340,"+
        	  "\"pot\":0"+
        	"}");

        assertEquals(0, Player.betRequest(jsonElement));

    }

}
