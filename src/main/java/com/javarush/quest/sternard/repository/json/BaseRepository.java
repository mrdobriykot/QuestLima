package com.javarush.quest.sternard.repository.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.AbstractEntity;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import com.javarush.quest.sternard.repository.Repository;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import static com.javarush.quest.sternard.util.message.LoggerMessages.DOES_NOT_EXIST;

@Slf4j
public abstract class BaseRepository<T extends AbstractEntity> implements Repository<T> {

    protected final AtomicLong id = new AtomicLong(System.currentTimeMillis());
    protected static final Path PATH_USERS_JSON_DB = Path.of(Settings.get().getUsersJsonFilename());
    protected static final Path PATH_QUESTS_JSON_DB = Path.of(Settings.get().getQuestsJsonFilename());
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected Map<Long, T> readMapFromJson(Path path, Class<T> clz) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path.toString())) {
            if (inputStream != null) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                    Type type = TypeToken.getParameterized(Map.class, Long.class, clz).getType();

                    return gson().fromJson(bufferedReader, type);
                }
            } else {
                log.error(DOES_NOT_EXIST, path);
                throw new HandlerExceptions(path + DOES_NOT_EXIST);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    protected JsonNode readJsonTreeValue(Path path) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path.toString())) {
            if (inputStream != null) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                    return OBJECT_MAPPER.readTree(bufferedReader);
                }
            } else {
                log.error(DOES_NOT_EXIST, path);
                throw new HandlerExceptions(path + DOES_NOT_EXIST);
            }
        }
    }

    protected void writeJsonTreeValue(JsonNode rootTree, Path path) {
        URL resource = getClass().getClassLoader().getResource(path.toString());
        if (resource != null) {
            String filePath = Objects.requireNonNull(resource).getFile();
            try (OutputStream output = new FileOutputStream(filePath)) {
                OBJECT_MAPPER.writeValue(output, rootTree);
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new HandlerExceptions(e.getMessage());
            }
        } else {
            log.error(DOES_NOT_EXIST, path);
            throw new HandlerExceptions(path + DOES_NOT_EXIST);
        }
    }

    @Override
    public Collection<T> getAllEntities() {
        return getEntitiesMap().values();
    }

    private Gson gson() {
        return new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                        (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive()
                                .getAsString()))
                .create();
    }
}