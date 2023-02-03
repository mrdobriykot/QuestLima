package com.bogdanov.service;

import com.bogdanov.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

public enum ImageService {

    IMAGE_SERVICE;

    public static final String IMAGES_FOLDER = "images";
    public static final String PART_NAME = "images";
    public static final String FILENAME_PREFIX = "image-";
    public static final String NO_IMAGE_PNG = "no-image.jpg";
    public static final  List<String> EXTENSIONS = List.of(
            ".jpg","jpeg",".png",".bmp",".gif",".webp"
    );

    private final Path imageFolder = Config.WEB_INF.resolve(IMAGES_FOLDER);

    @SneakyThrows
    ImageService() {
        Files.createDirectories(imageFolder);
    }

    public Path getImagePath(String filename){
        return EXTENSIONS.stream()
        .map(ext-> imageFolder.resolve(filename+ ext))
                .filter(Files::exists)
                .findAny()
                .orElse(imageFolder.resolve(NO_IMAGE_PNG));
    }

    public void uploadImage(HttpServletRequest request, long id){
        Part data= null;
        try {
            data = request.getPart(PART_NAME);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
        try {
            if(Objects.nonNull(data) && data.getInputStream().available()>0){
                String filename = data.getSubmittedFileName();
                String ext = filename.substring(filename.lastIndexOf("."));
                deleteOldFiles(FILENAME_PREFIX+id);
                filename = FILENAME_PREFIX+id+ext;
                uploadImageInternal(filename,data.getInputStream());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteOldFiles(String filename) {
        EXTENSIONS.stream()
                .map(ext -> imageFolder.resolve(filename + ext))
                .filter(Files::exists)
                .forEach(p -> {
                    try {
                        Files.deleteIfExists(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }


    private void uploadImageInternal(String name, InputStream data) throws IOException {
        try (data) {
            if (data.available() > 0) {
                Files.copy(data, imageFolder.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
