package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
    	JsonObject jObject = request.getAsJsonObject();
//    	jObject.get(memberName)
        return 200;

    }

    public static void showdown(JsonElement game) {
    }
}
