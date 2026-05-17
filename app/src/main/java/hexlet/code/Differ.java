package hexlet.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.TreeSet;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        // чтение файлов
        // парсинг
        // нахождендие разницы
        // форматирование разницы
        // возврат разницы
        String fileContent1 = readFile(filePath1);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> contentMap1 = objectMapper.readValue(fileContent1, new TypeReference<>() {
        });

        String fileContent2 = readFile(filePath2);
        Map<String, Object> contentMap2 = objectMapper.readValue(fileContent2, new TypeReference<>() {
        });

        List<DiffElement> difference = calculateDifference(contentMap1, contentMap2);

        // [DEBUG] >
        System.out.println("===== "+ filePath1 +" =====");
        System.out.println(fileContent1);
        System.out.println(contentMap1);
        System.out.println();
        System.out.println("===== "+ filePath2 +" =====");
        System.out.println(fileContent2);
        System.out.println(contentMap2);
        System.out.println();
        System.out.println("===== difference ======");
        System.out.println(difference);
        System.out.println();
        // [DEBUG] <

        return Formatter.format(difference);
    }

    private static List<DiffElement> calculateDifference(Map<String, Object> map1, Map<String, Object> map2) {
        List<DiffElement> diffList = new ArrayList<>();
        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        keys.forEach(key ->
                diffList.add(createDiffElement(map1, map2, key)));

        return diffList;
    }

    private static DiffElement createDiffElement(Map<String, Object> map1, Map<String, Object> map2, String key) {
        if (!map1.containsKey(key)) {
            return new DiffElement(key, null, map2.get(key), DiffElement.DIFFERENCE_TYPE_ADDED);
        }

        if (!map2.containsKey(key)) {
            return new DiffElement(key, map1.get(key), null, DiffElement.DIFFERENCE_TYPE_DELETED);
        }

        var value1 = map1.get(key);
        var value2 = map2.get(key);

        if (!value1.equals(value2)) {
            return new DiffElement(key, value1, value2, DiffElement.DIFFERENCE_TYPE_CHANGED);
        }

        return new DiffElement(key, value1, value2, DiffElement.DIFFERENCE_TYPE_UNCHANGED);
    }

    private static String readFile(String fileName) throws Exception {
        var path = Paths.get(fileName).toAbsolutePath().normalize();
        return Files.readString(path);
    }
}
