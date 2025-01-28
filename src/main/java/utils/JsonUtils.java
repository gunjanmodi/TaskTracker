package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Task;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void writeTasksToJsonFile(String filePath, List<Task> tasks) {
        File file = createJsonFileIfNotExists(filePath);
        try {
            objectMapper.writeValue(file, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> readTasksFromJsonFile(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Task>>() {});
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File createJsonFileIfNotExists(String filePath) {
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
