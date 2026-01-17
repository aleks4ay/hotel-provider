package com.aleks4ay.hotelprovider.util;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class JpgToSvgGenerator {

    private static final String IMG_PATH = "static\\img\\%s";

    public static void main(String[] args) throws Exception {
        String rawName = "501.jpg";
        String fileName = IMG_PATH.formatted(rawName);
        try (InputStream is = JpgToSvgGenerator.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new IllegalArgumentException("Файл не найден: " + fileName);
            }
            byte[] bytes = is.readAllBytes();
            String base64 = Base64.getEncoder().encodeToString(bytes);

            String svg = """
                    <svg xmlns="http://www.w3.org/2000/svg" width="600" height="400"><image href="data:image/jpeg;base64,%s" height="100%%" width="100%%"/></svg>"""
                    .formatted(base64);

            Path outputPath = Paths.get("src/main/resources/generated/%s".formatted(rawName.replace("jpg", "svg")));
            Files.writeString(outputPath, svg);
        }


    }
}
