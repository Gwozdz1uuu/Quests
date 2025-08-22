# Quests
Console application "Quests".

There are users that can collect items or kill mobs.
Quests are loaded from json file so its easly configurable.
Quests are splitted into Collect and Kill type.
There are 3 variants of each of them. Collectables: Wheat, Diamonds, Iron. Killable: Zombie, Skeleton and Spider.

[Architecture]

* Controllers :
  - GameController (handles game loop and display everything in console.)
* Models:
  - Quest (entitty of a quest that contains stats like QuestTypes, progress and simple methods)
  - QuestDto (a representable version of a quest that is displayed, also it is held in memory (without current progress of it).
  - User 
  * Enums: 
    - QuestType ( Collect, kill) 
    - MobSpec (Zombie, skeleton, spider)
    - ItemSpec (Diamond, iron, wheat)
* Repositories :
  - QuestDataBase ( in-memory repository. Retrieves data from json object. It is the closest class that works with saving, uploading, deleting quest from memory.
  - UserDataBase ( simply holds some users )
* Services:
  - UserQuestService ( holds business logic conected with users action (dropQuest, assignQuest), comunicates between User and QuestService. Gets and action and sends it to QuestService.
  - QuestService ( works especially with Quests (list quests) and quest repository.  if service gets an action from UserQuestService sends it to repository to execute.
* Interfaces:
  - QuestSpec ( MobSpec and ItemSpec ) generalize the specs.
  - Quest ( contains generalized methods )
* Mappers:
  - QuestMapper ( converts Quest to dto, dto to Quest )

How it works:
At the beginning we choose a player (that are hard coded, but QUESTS are NOT).

![alt text](https://github.com/Gwozdz1uuu/Quests/blob/main/src/main/resources/screenShots/img.png)

Then we choose an action: 

![alt text](https://github.com/Gwozdz1uuu/Quests/blob/main/src/main/resources/screenShots/img_1.png)

![alt text](https://github.com/Gwozdz1uuu/Quests/blob/main/src/main/resources/screenShots/img_2.png)

![alt text](https://github.com/Gwozdz1uuu/Quests/blob/main/src/main/resources/screenShots/img_3.png)

![alt text](https://github.com/Gwozdz1uuu/Quests/blob/main/src/main/resources/screenShots/img_4.png)

Also we can switch betbeen users without loosing any changes. I have completed one quest and chose another one on different user.
List of quests is updating: 

![alt text](https://github.com/Gwozdz1uuu/Quests/blob/main/src/main/resources/screenShots/img_5.png)
