package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Map;
import java.util.concurrent.Callable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference")
class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {

        String fileContent1 = readFile(filepath1);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map1 = objectMapper.readValue(fileContent1, new TypeReference<Map<String,Object>>(){});

        String fileContent2 = readFile(filepath2);
        Map<String, Object> map2 = objectMapper.readValue(fileContent1, new TypeReference<Map<String,Object>>(){});

        System.out.println("'"+filepath1+"'");
        System.out.println(fileContent1);
        System.out.println(map1);
        System.out.println("=============");
        System.out.println("'"+filepath2+"'");
        System.out.println(fileContent2);
        System.out.println(map2);
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    private static String readFile(String fileName) throws Exception {
        var path = Paths.get(fileName).toAbsolutePath().normalize();
        return Files.readString(path);
    }
}