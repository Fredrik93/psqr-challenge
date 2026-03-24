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
* im not sure how but git was set to your repo, i could see anton and some more people commited. I removed git and set my own repo. i had access rights but maybe i should have pushed to my own branch. anyhow i made my own repo.
* tried to add a gitignore (i thought it was added earlier). some class files are commited and i have no idea why. 
* i think trackedIds is giving me a list of packages/parcels, it is not related to ids of the parcel, which was my first assumption. that threw me off a bit. 
* I have now spent 45 minutes just trying to read all classes and docs and writing unusable tests to verify my assumptions. There is no way ill make a 2 hour time limit. 
* Spent the morning figuring out what a trackedId is. i think it means that the operations are used in batches, so we can process many parcels at the same time for one operation. for example, we can CREATE fifty new parcels in one batch. thats why we have trackedItemIds connected to one single event, which was initially confusing to me
* Added three events and mocked the repository call so that when i run my test im actually receiving events. 




# psqr-challenge
