# The Battleship Challenge

*“[Battleship](https://en.wikipedia.org/wiki/Battleship_(game)) is known worldwide as a pencil and paper game which dates from World War I. It was published by various companies as a pad-and-pencil game in the 1930s, and was released as a plastic board game by Milton Bradley in 1967. The game has spawned electronic versions, video games, smart device apps and a film.”*

In our simplified version of battleship, a board can be thought of as a grid of potentially attacked positions along with one or more ships each of which occupy one or more positions. Your task is to model a board and implement the ability to attack a position on that board.

**Managing multiple players, reading user input, or actually playing a game is beyond the scope of the exercise, which is simply to implement the backend state and logic required to execute an attack. We strongly advise implementing requested functionality before adding extra functionality to demonstrate your abilities.**

An attack should result in the new state of the board along with one of the following outcomes:

* ‘Hit’ if there is a ship occupying the position
* ‘Miss’ if no ship occupies the position
* ‘Sunk’ if the attack hits the last remaining position of a ship
* ‘Win’ if the attack sinks the last remaining ship

Your program will need to manage state from previous attacks in order to supports outcomes such as 'Sunk' or 'Win'.

Note that no specific version of battleship is specified for this task (eg: American, Indian, Japanese, etc). You can and should constrain your implementation in any way you see fit given the time recommendation. Interesting features and mechanics from the various rulesets used around the world may be used as enhancement tasks in the live coding session, so we recommend keeping your code simple, flexible, and extensible!

Using a scripting/dynamically typed language if you know one is also likely to help you make the most of your time.

**Note that you may not be able to finish this challenge before your time is up on the on-site. If that is the case we will try to evaluate you based on the portion of the challenge that you did complete.**
