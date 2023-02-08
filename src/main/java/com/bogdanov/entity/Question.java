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
   private Long idQuestion;
   private Long questId;
   private String text;
   private GameState gameState;
   private final Collection<Answer> answers = new ArrayList<>();
   public String getImage(){
       return "quest-"+id;
   }

   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(Long id) {
      this.id = id;
   }
}
