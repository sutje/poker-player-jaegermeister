package org.leanpoker.player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Default Java folding player";
    private static Map<String, Integer> rankValues = null;

    public static int betRequest(JsonElement request) {
    	JsonObject root = request.getAsJsonObject();
    	JsonArray constellation = getConstellation(root);
    	int valueOfHand = getValueOfConstellation(constellation);
        return getBetForValueOfHand(root, valueOfHand);
    }

    public static void showdown(JsonElement game) {
    }
    
    private static int getBetForValueOfHand(JsonObject root, int valueOfHand) {
    	if (getRoundNumber(root) == 0) {
    		return getMinRaise(root); // Erste Runde
    	}
    	if (valueOfHand > 1) { // Mindestens ein Zwilling
    		return getMinRaise(root);
    	}
    	return 0;
    }
    
    private static int getMinRaise(JsonObject root) {
    	 //     current_buy_in - players[in_action][bet] + minimum_raise
    	int buyIn = root.get("current_buy_in").getAsInt();
    	int bet = getMyPlayer(root).get("bet").getAsInt();
    	int minRaise = root.get("minimum_raise").getAsInt();
    	return buyIn - bet + minRaise;
    }
    
    private static int getValueOfConstellation(JsonArray constellation) {
    	return constellation.size();
    }
    
    private static JsonArray getConstellation(JsonObject root) {
    	JsonArray allCards = getAllCards(root);
    	Map<String, JsonArray> map = new HashMap<>();
    	Iterator<JsonElement> iterator = allCards.iterator();
    	while (iterator.hasNext()) {
    		JsonElement jsonElement = iterator.next();
    		JsonObject card = jsonElement.getAsJsonObject();
    		String key = card.get("rank").getAsString();
    		JsonArray array = map.get(key);
    		if (array == null) {
    			array = new JsonArray();
    			map.put(key, array);
    		}
    		array.add(jsonElement);
    	}
    	// Arrays interpretieren
    	JsonArray best = null;
    	for (JsonArray combination : map.values()) {
    		if (best == null) {
    			best = combination;
    		} else if (combination.size() > best.size()) {
    			best = combination;
    		} else if (combination.size() == best.size()) {
    			String rankBest = getRankOfCard(best.get(0).getAsJsonObject());
    			String rankComb = getRankOfCard(combination.get(0).getAsJsonObject());
    			int valueBest = getValueForRank(rankBest);
    			int valueComb = getValueForRank(rankBest);
    			if (valueComb > valueBest) {
    				best = combination; 
    			}
    		}
    	}
    	return best;
    }
    
    private static int getValueForRank(String rank) {
    	int value = 0;
    	Integer integer = getRankValues().get(rank);
    	if (integer != null) {
    		value = integer.intValue();
    	}
    	return value;
    }
    
    private static Map<String, Integer> getRankValues() {
    	if (rankValues == null) {
    		rankValues = new HashMap<>();
    		rankValues.put("2", 2);
    		rankValues.put("3", 2);
    		rankValues.put("4", 2);
    		rankValues.put("5", 2);
    		rankValues.put("6", 2);
    		rankValues.put("7", 2);
    		rankValues.put("8", 2);
    		rankValues.put("9", 2);
    		rankValues.put("10", 2);
    		rankValues.put("J", 2);
    		rankValues.put("Q", 2);
    		rankValues.put("K", 2);
    		rankValues.put("A", 2);
    	}
    	return rankValues;
    }
    
    private static String getSuitOfCard(JsonObject card) {
    	return card.get("suit").getAsString();
    }
    
    private static String getRankOfCard(JsonObject card) {
    	return card.get("rank").getAsString();
    }

    private static JsonArray getPlayers(JsonObject root) {
    	return root.get("players").getAsJsonArray();
    }
    
    private static int getMyIndex(JsonObject root) {
    	return root.get("in_action").getAsInt();
    }
    
    private static int getRoundNumber(JsonObject root) {
    	return root.get("round").getAsInt();
    }
    
    private static JsonObject getMyPlayer(JsonObject root) {
    	JsonArray players = getPlayers(root);
    	return players.get(getMyIndex(root)).getAsJsonObject();
    }
    
    private static JsonArray getMyCards(JsonObject root) {
    	JsonObject myPlayer = getMyPlayer(root);
    	return myPlayer.get("hole_cards").getAsJsonArray();
    }
    
    private static JsonArray getCommunityCards(JsonObject root) {
    	return root.get("community_cards").getAsJsonArray();    	
    }
    
    private static JsonArray getAllCards(JsonObject root) {
    	JsonArray allCards = new JsonArray();
    	allCards.addAll(getMyCards(root));
    	allCards.addAll(getCommunityCards(root));
    	return allCards;
    }
    
}
