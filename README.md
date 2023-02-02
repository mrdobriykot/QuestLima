# QuestLima (Ivanilov)

This project was created for educational purpose on Java Rush University course. 
Its purpose is to train web-app development using servlets and jsp technologies.

## Quick start guide
1. Mount the docker file. 
2. Visit http://localhost:8080/ to open index page. 
3. Create new account or use one of the following:
   1. **(Admin)** login _admin_, password _admin_
   2. login _testUser_, password _safari_
   3. login _testUser2_, password _google_

## Key features:
1. Play one of quest of your choice. Quests consist of **_questions_** of **_fights_**. 
   1. Players are to choose only one option in each question. The answer may lead to win, lose or other question or fight.
   2. During fight players choose one hit option and one block option, the opponents does the same. If a hit was blocked no damage dealt. Players damage is calculated as a random number between minimal damage ("1" by default) and strength (it depends on quest settings). 
2. Account page provides statistics on number of played and won quests.
3. Quest creator allows to build your own quest using special syntax.  
4**(Admins only)** User management: create, modify or delete users and get their stats from one page.
5**(Admins only)** Quest management: delete any quest you don't like :)

## Issues:
1. Passwords are stored as plain text.
2. Authorization is based on session information and can be easily hacked.
3. No logging.
4. Game progress in fights isn't saved. 
