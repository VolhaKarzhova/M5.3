package mailRu.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mailRu.business_objects.letter.Letter;

import java.lang.reflect.Type;

public class JsonUtils {

    private Letter[] list;

    private Type itemsArrType = new TypeToken<Letter[]>() {
    }.getType();

    public Letter[] createTestData(String jsonFile) {
        list = new Gson().fromJson(jsonFile, itemsArrType);
        return list;
    }

//    public static void main(String[] args) {
//        JsonUtils jsonUtils = new JsonUtils();
//        jsonUtils.createTestData("[{\"addressee\":\"volhakarzhova@mail.ru\", \"subject\":\"Subject464647\", \"body\":\"wetyjiolkmn876re3\"},{\"addressee\":\"olga1584624@mail.ru\", \"subject\":\"5476576787\", \"body\":\"467589p0dfhki;lpo\"}]");
//
//    }
}