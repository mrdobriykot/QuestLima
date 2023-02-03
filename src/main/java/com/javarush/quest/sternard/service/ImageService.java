package com.javarush.quest.sternard.service;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import com.javarush.quest.sternard.util.Parameter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static com.javarush.quest.sternard.config.Default.WEB_INF;


@Slf4j
public enum ImageService {
    INSTANCE;
    private final Path imagesFolder = WEB_INF.resolve(Settings.get().getImagesFolder());

    @SneakyThrows
    ImageService() {
        createDirectories();
    }

    private void createDirectories() {
        Map<String, String> imagesSubFolders = Settings.get().getImagesSubFolders();
        imagesSubFolders.forEach((k, v) -> {
            try {
                Files.createDirectories(imagesFolder.resolve(v));
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new HandlerExceptions(e.getMessage());
            }
        });
    }

    public boolean uploadImage(HttpServletRequest req, String oldImageFilename, String subfolder) throws IOException, ServletException {
        Part data = req.getPart(Parameter.IMAGE);

        if (Objects.nonNull(data)) {
            InputStream inputStream = data.getInputStream();
            if (inputStream.available() > 0) {
                String newImageFilename = data.getSubmittedFileName();
                deleteOldImage(oldImageFilename, subfolder);
                uploadImageInternal(newImageFilename, inputStream, subfolder);
                return true;
            }
        }
        return false;
    }

    public void deleteOldImage(String imageFilename, String subfolder) {
        Path path = Paths.get(imagesFolder.resolve(subfolder).toUri());
        try (Stream<Path> pathStream = Files.walk(path, 3)) {
            pathStream.forEach(file -> searchAndDelete(imageFilename, file));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    private void searchAndDelete(String imageFilename, Path file) {
        String filename = file.getFileName().toString();
        if (filename.equals(imageFilename)) {
            try {
                Files.deleteIfExists(file);
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new HandlerExceptions(e.getMessage());
            }
        }
    }

    private void uploadImageInternal(String name, InputStream data, String subfolder) {
        try (data) {
            if (data.available() > 0) {
                Path subfolderPath = imagesFolder.resolve(subfolder);
                Files.copy(data, subfolderPath.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

}

