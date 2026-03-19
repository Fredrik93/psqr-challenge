**Persequor Dev Test**

Scope: 
+ No need to implement interfaces. Implement only the `TODO` comments as you see fit.
+ You can change anything you like. Frameworks, patterns, interfaces etc.
+ We suggest to work on it between 0.5 to 2 hours, but feel free to use as much time as you need.
+ Commit as often as you see fit.
+ There is no requirement to finish this, just to have some code for evaluation and further talks.
+ If you have time, review the code in the com.persequor.extensions package.

## Diary 
*  opening up project in intellij first time looks ok. mvn clean works, mvn install does not. something with java 9 and List.of()? 
* trying to just call the empty handle method in EventStorageListener fails with a weird unable to make protected final String error, never seen this. 
* okay so mockito was version 1 and initMocks is very old and stuff is breaking because of this, maybe because im using a java version that is older than 9. i upgraded mockito and some imports and it seems to work. 
* im not sure how but git was set to your repo, i could see anton and some more people commited. I removed git and set my own repo. i had access rights but maybe i should have pushed to my 
# psqr-challenge
