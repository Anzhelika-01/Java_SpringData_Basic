package bg.softuni.jsonprocessing.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public enum Utils {
    ;
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting().create();

    public static void writeJsonToFile(List<?> objects, Path filePath) throws IOException {
        File file = new File(filePath.toUri());
        if (file.exists() && file.length() == 0) {
            final FileWriter fileWriter = new FileWriter(filePath.toFile());
            GSON.toJson(objects, fileWriter);

            fileWriter.flush();
            fileWriter.close();
        }
    }
}
