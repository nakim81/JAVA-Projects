# Project 3 Prep

**For tessellating pluses, one of the hardest parts is figuring out where to place each plus/how to easily place plus on screen in an algorithmic way.
If you did not implement tesselation, please try to come up with an algorithmic approach to place pluses on the screen. This means a strategy, pseudocode, or even actual code! 
Consider the `HexWorld` implementation provided near the end of the lab (within the lab video). Note that `HexWorld` was the lab assignment from a previous semester (in which students drew hexagons instead of pluses). 
How did your proposed implementation/algorithm differ from the given one (aside from obviously hexagons versus pluses) ? What lessons can be learned from it?**

Answer:
A possible answer might be getting positions and size of nearby pluses and put a newly generated plus in the empty area, but right next to the neighboring pluses. This can be done by using for loops for each neighbor pluses
such as topright, bottomright, topleft, or bottomleft. Furthermore, by creating shifting functions or adding a trait of preset of position, we can put our plus at the desired location.
-----

**Can you think of an analogy between the process of tessellating pluses and randomly generating a world using rooms and hallways?
What is the plus and what is the tesselation on the Project 3 side?**

Answer:
In my opinion, center box of the plus is a room and other four boxes are hall ways so that hall ways are connected to each other and rooms are connected to each other by hall ways.
On the Project 3 side, plus is floor and walls, and tesselating is generating a world using the rooms and hallways.
-----
**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating pluses.**

Answer:
I would randomly generate a room and wall first, and by using several methods, I will place more rooms and walls beside the first one.
However I will make sure that all floors are accessible form everywhere in the map.
-----
**What distinguishes a hallway from a room? How are they similar?**
They are not particularly different from each other.
Answer:
