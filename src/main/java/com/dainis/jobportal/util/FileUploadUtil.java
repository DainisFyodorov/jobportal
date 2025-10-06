package com.dainis.jobportal.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        if (fileName == null || fileName.isEmpty()) {
            throw new IOException("Filename is empty");
        }

        Path filePath = Paths.get(uploadDir).resolve(fileName);

        Path parentDir = filePath.getParent();
        if (Files.exists(parentDir) && !Files.isDirectory(parentDir)) {
            throw new IOException("Parent path exists and is not a directory: " + parentDir);
        }
        Files.createDirectories(parentDir);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved to: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }
    }
}
