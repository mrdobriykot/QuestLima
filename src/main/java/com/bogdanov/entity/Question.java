package com.bogdanov.entity;

import com.bogdanov.entity.Quest;

import java.util.ArrayList;
import java.util.Collection;

public class Question implements AbstaractEntity{
   private Long id;
   private Quest quest;
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
