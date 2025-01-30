package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void writeTasksToJsonFile(String filePath, List<Task> tasks) {
        File file = createJsonFileIfNotExists(filePath);
        try {
            objectMapper.writeValue(file, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> readTasksFromJsonFile(String filePath) {
        File file = createJsonFileIfNotExists(filePath);
        if (file.length() == 0) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static File createJsonFileIfNotExists(String filePath) {
        File file = new File(filePath);
        if(!file.exists()) {
            try {
                file.createNewFile();
                return file;
            } catch (IOException e)  {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }
}
