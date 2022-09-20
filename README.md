# **Darts Trainingsspiel (Training Game)**

This is a small Java project with openJFX (JavaFX) to help you train your Darts skills.

Specktulatius made this for Michael some time ago.
I almost reworked everything, but he still remains an author. :)

Highscore: Michael S. 12 Punkte (09.04.2021) (mit Ring)

## Point System

| Triple Treffer -><br>Darts im Board  	| 0  	| 1 	| 2 	| 3 	|
|--------------------------------------	|----	|---	|---	|---	|
| 0                                      	| -1 	| - 	| - 	| - 	|
| 1                                      	| 0  	| 1 	| - 	| - 	|
| 2                                      	| 1  	| 2 	| 3 	| - 	|
| 3                                      	| 2  	| 3 	| 4 	| 5 	|

## How to run this Programm

You will need an openJFX runtime or a JDK, which includes it (e.g. Azul-Zulu).

You can then just start is as you would any jar-file.

### Windows

###### Option 1

1. Double-click on the downloaded jar-file in your file explorer (or right-click -> "Open")
2. Done

###### Option 2

1. Open Powershell
2. Navigate to the folder you downloaded the file to with `cd`
   <br> (e.g. `cd C:/users/user/Downloads`)
3. Execute `java -jar DartsTrainingsspiel-$VERSION.jar`
   <br> (replace $VERSION with the version number you downloaded)

### Linux

1. Navigate to the folder you downloaded the file to with `cd`
   <br> (e.g. `cd C:/users/user/Downloads`)
2. Execute `java -jar DartsTrainingsspiel-$VERSION.jar`
   <br> (replace $VERSION with the version number you downloaded)

## Common Issues

###### Error: Could not find or load main class Main <br> Caused by: java.lang.NoClassDefFoundError: javafx/application/Application

Your java installation does not have openJFX (JavaFX) included. <br>
Install and use one that does (e.g. Azul-Zulu) and try again. <br>
(If your on Windows you can also try using the openJFX runtime.)