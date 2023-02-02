package com.javarush.quest.ivanilov.utils.transfers;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestDto {
    long questId;
    String authorName;
    String name;
}
