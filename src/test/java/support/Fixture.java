package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Fixture {
    public static String json(String name) {
        try {
            var file = new File(Fixture.class
                    .getClassLoader()
                    .getResource("fixture/" + name + ".json")
                    .toURI());
            return Files.readAllLines(file.toPath(), Charset.forName("UTF-8"))
                    .stream()
                    .collect(Collectors.joining());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String xml(String name) {
        try {
            var file = new File(Fixture.class
                    .getClassLoader()
                    .getResource("fixture/" + name + ".xml")
                    .toURI());

            return Files.readAllLines(file.toPath(), Charset.forName("UTF-8"))
                    .stream()
                    .collect(Collectors.joining());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream file(String name) {
        try {
            var fixturePath = Fixture.class
                    .getClassLoader()
                    .getResource("fixture/" + name + ".xml")
                    .toURI();
            var file = new File(fixturePath);
            return new FileInputStream(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}