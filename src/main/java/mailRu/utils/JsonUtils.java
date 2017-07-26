package mailRu.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mailRu.business_objects.letter.Letter;

import java.lang.reflect.Type;

public class JsonUtils {

    private Type itemsArrType = new TypeToken<Letter[]>() {
    }.getType();

    public Letter[] getLetterParameters(String jsonFile) {
        return new Gson().fromJson(jsonFile, itemsArrType);
    }
}