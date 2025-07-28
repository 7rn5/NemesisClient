package nemesis.util.discord;

import com.google.gson.JsonObject;

public record Packet(Opcode opcode, JsonObject data) {
}
