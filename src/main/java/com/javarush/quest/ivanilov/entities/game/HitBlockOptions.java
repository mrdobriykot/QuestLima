package com.javarush.quest.ivanilov.entities.game;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class HitBlockOptions {
    private final List<String> hitOptions = initHitOptions();
    private final List<String> blockOptions = initBlockOptions();

    private List<String> initBlockOptions() {
        List<String> blockOptions = new ArrayList<>();
        blockOptions.add(BodyParts.HEAD + " and " + BodyParts.CHEST);
        blockOptions.add(BodyParts.CHEST + " and " + BodyParts.BELLY);
        blockOptions.add(BodyParts.BELLY + " and " + BodyParts.LEGS);
        return blockOptions;
    }

    private List<String> initHitOptions() {
        List<String> hitOptions = new ArrayList<>();

        for (var bodyPart : BodyParts.values()) {
            hitOptions.add(bodyPart.toString());
        }

        return hitOptions;
    }

    public Set<String> parseBlock(String text) {
        Set<String> blockOptions = new HashSet<>();

        for (var bodyPart : BodyParts.values()) {
            if (text.contains(bodyPart.toString())) {
                blockOptions.add(bodyPart.toString());
            }
        }

        return blockOptions;
    }

    public String randomHit() {
        int i = ThreadLocalRandom.current().nextInt(0, hitOptions.size());
        return hitOptions.get(i);
    }

    public Set<String> randomBlock(int quantity) {
        Set<String> blocks = new HashSet<>();

        while (quantity > 0) {
            int i = ThreadLocalRandom.current().nextInt(0, blockOptions.size());
            if (!blocks.contains(blockOptions.get(i))) {
                blocks.add(blockOptions.get(i));
                quantity--;
            }
        }

        return blocks;
    }
}
