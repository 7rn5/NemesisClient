package nemesis.util.file;

import com.google.gson.JsonElement;

public interface JsonUtil {
    JsonElement toJson();

    void fromJson(JsonElement element);

    default String getFileName() {
        return "";
    }
}
