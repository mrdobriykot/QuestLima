package com.javarush.quest.osypenko.repository.entityDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.osypenko.costants.Constant;
import com.javarush.quest.osypenko.repository.DB;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.TreeMap;

@ExtendWith(MockitoExtension.class)
class AlgorithmsTest {
    @Mock
    TreeMap<Long, DB> map;
    @Mock
    ObjectMapper mapper;

    @Test
    void getConstantIDTest() {
        try {
            //noinspection unchecked
            map = mapper.readValue(AlgorithmsTest.class.getResource(Constant.ALGORITHMS_JSON), TreeMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}