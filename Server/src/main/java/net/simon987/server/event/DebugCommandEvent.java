package net.simon987.server.event;

import net.simon987.server.webserver.OnlineUser;
import org.json.simple.JSONObject;

public class DebugCommandEvent extends GameEvent {

    private JSONObject command;

    public DebugCommandEvent(JSONObject json, OnlineUser user) {
        this.command = json;
        this.setSource(user);
    }

    public String getName() {
        return (String) command.get("command");
    }

    public String getString(String key) {
        return (String) command.get(key);
    }

    public int getInt(String key) {
        return (int) (long) command.get(key);
    }

    public long getLong(String key) {
        return (long) command.get(key);
    }

    /**
     * Send back a response to the command issuer
     */
    public void reply(String message) {

        JSONObject response = new JSONObject();
        response.put("t", "debug");
        response.put("message", message);

        ((OnlineUser) getSource()).getWebSocket().send(response.toJSONString());
    }

}
