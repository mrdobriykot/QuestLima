package com.bogdanov.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question implements AbstaractEntity{
   private Long id;
   private Long questId;
   private Long idQuestion;
   private String text;
   private GameState gameState;
   private final Collection<Answer> answers = new ArrayList<>();
   public String getImage(){
       return "quest-"+id;
   }


}
