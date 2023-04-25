package com.javarush.khmelov.mapping;

import com.javarush.khmelov.dto.*;
import com.javarush.khmelov.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Dto {

    Dto MAPPER = Mappers.getMapper(Dto.class);

    UserDto from(User user);

    @Mapping(target = "userId", source = "author.id")
    QuestDto from(Quest quest);

    QuestionDto from(Question question);

    AnswerDto from(Answer answer);

    @Mapping(target = "question", ignore = true)
    GameDto from(Game game);

}
