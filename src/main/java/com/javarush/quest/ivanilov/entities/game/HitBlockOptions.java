package com.javarush.quest.ivanilov.entities.game;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class HitBlockOptions {

    private final BodyPart[] bodyParts;
    private final List<String> hitOptions;
    private final List<String> blockOptions;

    public HitBlockOptions(BodyPart[] bodyParts) {
        this.bodyParts = bodyParts;
        hitOptions = initHitOptions(bodyParts);
        blockOptions = initBlockOptions(bodyParts);
    }

    private List<String> initBlockOptions(BodyPart[] bodyParts) {
        List<String> blockOptions = new ArrayList<>();
        for (int i = 0; i < bodyParts.length - 1; i++) {
            blockOptions.add(bodyParts[i] + " and " + bodyParts[i + 1]);
        }
        return blockOptions;
    }

    private List<String> initHitOptions(BodyPart[] bodyParts) {
        List<String> hitOptions = new ArrayList<>();

        for (var bodyPart : bodyParts) {
            hitOptions.add(bodyPart.toString());
        }

        return hitOptions;
    }

    public Set<String> parseBlock(String text) {
        Set<String> blockOptions = new HashSet<>();

        for (var bodyPart : BodyPart.values()) {
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
