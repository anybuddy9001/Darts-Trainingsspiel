<sup>[Click here for the english version](#english-version) </sup>

# **Darts Trainingsspiel**

Dies ist ein Java-Projekt mit OpenJFX (Javafx)
mit dem mithilfe eines Punktesystems Darts-Fähigkeiten trainiert werden können.

Specktulatius hat das Spiel vor einiger Zeit für Michael gemacht. <br>
Allerdings habe ich inzwischen fast alles überarbeitet,
aber er verbleibt dennoch als Autor in den Docs. :)

## Anleitung zum Spiel:

Hänge einen beliebigen Practice Ring bei dem T20er Segment auf und versuche,
innerhalb vom Ring zu treffen. Unten siehst du die Punktetabelle aufgeführt.
Als Triple Treffer werden auch Treffer in der T1 und T5 gezählt.
Versuche, möglichst viele Punkte zu erreichen. Das Spiel ist verloren,
sobald du bei 0 Punkten bist und keinen Treffer hast.

## Punktetabelle

| Triple Treffer -> <br> Darts im Practice Ring  	 | 0  	 | 1 	 | 2 	 | 3 	 |
|--------------------------------------------------|------|-----|-----|-----|
| 0                                      	         | -1 	 | - 	 | - 	 | - 	 |
| 1                                      	         | 0  	 | 1 	 | - 	 | - 	 |
| 2                                      	         | 1  	 | 2 	 | 3 	 | - 	 |
| 3                                      	         | 2  	 | 3 	 | 4 	 | 5 	 |

## Wie starte ich das Programm?

Es wird eine OpenJFX-Runtime oder ein JDK, die sie beinhaltet benötigt. (
z.B. [Azul-Zulu](https://www.azul.com/downloads/?package=jdk-fx))

Die Vorgehensweise ist dann wie bei jedem anderen Java Programm,
das als jar-Datei ausgeliefert wird.

### Windows

###### Option 1

1. Im Datei-Explorer doppelklick auf die heruntergeladene Jar-Datei (oder klick mit der rechten
   Maustaste -> "Öffnen").
2. Spaß haben

###### Option 2 (nicht empfohlen)

1. PowerShell öffnen
2. Mithilfe von `cd` zu dem Ordner, in dem die heruntergeladene Datei liegt navigieren <br>
   (z. B. `cd C:\users\user\Downloads`)
3. Mit dem Kommando `java -jar Dartstrainingsspiel-$Version.jar` die Datei ausführen <br>
   (`$Version` durch die Versionsnummer der heruntergeladenen Datei ersetzen)

### Linux

1. Terminal der Wahl öffnen
2. Mithilfe von `cd` zu dem Ordner, in dem die heruntergeladene Datei liegt navigieren <br>
   (z. B. `cd ~/Downloads`)
3. Mit dem Kommando `java -jar Dartstrainingsspiel-$Version.jar` die Datei ausführen <br>
   (`$Version` durch die Versionsnummer der heruntergeladenen Datei ersetzen)

## Häufige Probleme

###### Alle Schaltflächen zur Punkteeingabe sind ausgegraut und reagieren auf keine Eingaben

Um Punkteingaben tätigen zu können muss zuerst der Timer gestartet werden.

###### Error: Could not find or load main class Main <br> Caused by: java.lang.NoClassDefFoundError: javafx/application/Application

Ihre Java-Installation enthält keine OpenJFX (Javafx) Bibliothek. <br>
Installieren Sie ein JDK, das diese enthält (z.B. Azul-Zulu) und versuchen es erneut. <br>
(Unter Windows können Sie auch die OpenJFX-Runtime verwenden.)

## Entwicklung

Ich benutze Intellij-Ultimate von JetBrains (für Studierende) mit dem Azul-Zulu JDK (18.0.2). <br>
Zusätzlich verwende ich die [lombok](https://projectlombok.org/)- und [org.json](https://github.com/stleary/JSON-java)
-Bibliothek.

## Mitwirkende

* Michael S. - Idee, Beratung, Darts-Spezifisches
* Specktulatius - Grundlage des Projekts

<br>

# English Version

## **Darts Training Game**

This is a Java project with openJFX (JavaFX) to help train your Darts skills with a point reward system.

Specktulatius made this for Michael some time ago. <br>
I almost reworked everything, but he still remains an author in the Docs. :)

## Instructions for the game:

Hang up any practice ring at the T20 segment and attempt to hit within the ring.
You will see the point system listed below.
Hits in T1 and T5 are also counted as triple hits.
Try to achieve as many points as possible.
The game is lost when you are at 0 points and don't hit anything.

## Point System

| Triple Hits -> <br> Darts in Practice Ring  	 | 0  	 | 1 	 | 2 	 | 3 	 |
|-----------------------------------------------|------|-----|-----|-----|
| 0                                      	      | -1 	 | - 	 | - 	 | - 	 |
| 1                                      	      | 0  	 | 1 	 | - 	 | - 	 |
| 2                                      	      | 1  	 | 2 	 | 3 	 | - 	 |
| 3                                      	      | 2  	 | 3 	 | 4 	 | 5 	 |

## How to run this Programm

You will need an openJFX runtime or a JDK, which includes it (
e.g. [Azul-Zulu](https://www.azul.com/downloads/?package=jdk-fx)).

You can then just start is as you would any jar-file.

### Windows

###### Option 1

1. Double-click on the downloaded jar-file in your file explorer (or right-click -> "Open")
2. Enjoy

###### Option 2 (not recommended)

1. Open Powershell
2. Navigate to the folder you downloaded the file to with `cd` <br>
   (e.g. `cd C:\users\user\Downloads`)
3. Execute `java -jar DartsTrainingsspiel-$VERSION.jar` <br>
   (replace `$VERSION` with the version number of the game you downloaded)

### Linux

1. Navigate to the folder you downloaded the file to with `cd` <br>
   (e.g. `cd ~/Downloads`)
2. Execute `java -jar DartsTrainingsspiel-$VERSION.jar` <br>
   (replace `$VERSION` with the version number of the game you downloaded)

## Common Issues

###### All buttons for the point input are grayed out and do not respond to any actions

In order to be able to make point inputs, the timer must be running.

###### Error: Could not find or load main class Main <br> Caused by: java.lang.NoClassDefFoundError: javafx/application/Application

Your java installation does not have openJFX (JavaFX) included. <br>
Install and use one that does (e.g. Azul-Zulu) and try again. <br>
(If your on Windows you can also try using the openJFX runtime.)

## Development

I use IntelliJ-Ultimate by JetBrains (for Students) with the Azul-Zulu JDK (18.0.2). <br>
Additionally I use the [lombok](https://projectlombok.org/) and [org.json](https://github.com/stleary/JSON-java)
library.

## Contributors

* Michael S. - Idea, Counseling, Darts specifics
* Specktulatius - Base project