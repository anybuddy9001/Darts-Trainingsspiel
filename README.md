<sup>[Click here for the english version](#english-version) </sup>

# **Darts Trainingsspiel**

Dies ist ein kleines Java -Projekt mit OpenJFX (Javafx),
mit dem Sie Ihre Darts-Fähigkeiten mithilfe eines Punktesystems trainieren können.

Specktulatius hat das Spiel vor einiger Zeit für Michael gemacht. <br>
Allerdings habe ich inzwischen fast alles überarbeitet,
aber er verbleibt dennoch als Autor in den Docs. :)

## Anleitung zum Spiel:

Hänge einen beliebigen Practice Ring bei dem T20er Segment auf und versuche,
innerhalb vom Ring zu treffen. Unten siehst du das Punkte-System aufgeführt.
Als Triple Treffer werden auch Treffer in der T1 und T5 gezählt.
Versuche, möglichst viele Punkte zu erreichen. Das Spiel ist verloren,
sobald du bei 0 Punkten bist und keinen Treffer hast.

## Punkte-System

| Triple Treffer -> <br> Darts im Practice Ring  	 | 0  	 | 1 	 | 2 	 | 3 	 |
|--------------------------------------------------|------|-----|-----|-----|
| 0                                      	         | -1 	 | - 	 | - 	 | - 	 |
| 1                                      	         | 0  	 | 1 	 | - 	 | - 	 |
| 2                                      	         | 1  	 | 2 	 | 3 	 | - 	 |
| 3                                      	         | 2  	 | 3 	 | 4 	 | 5 	 |

## Wie starte ich das Programm?

Es wird eine OpenJFX-Runtime oder ein JDK, die sie (z.B. Azul-Zulu) beinhaltet benötigt. <br>
Die Vorgehensweise ist dann wie bei jedem anderen Java Programm,
das als jar-Datei ausgeliefert wird.

### Windows

###### Option 1

1. Doppelklicken Sie in Ihrem Datei-Explorer auf das heruntergeladene Jar-File (oder klicken Sie mit der rechten
   Maustaste-> "Öffnen").
2. Spaß haben

###### Option 2

1. Öffnen Sie PowerShell
2. Navigieren Sie mit `cd` zu dem Ordner in dem die heruntergeladene Datei liegt <br>
   (z. B. `cd C:/users/user/Downloads`)
3. Führen Sie `java -jar DartStrainingsspiel-$Version.jar` aus <br>
   (`$Version` durch die Versionsnummer der heruntergeladenen Datei ersetzen)

### Linux

1. Navigieren Sie mit `cd` zu dem Ordner in dem die heruntergeladene Datei liegt <br>
   (z. B. `cd C:/users/user/Downloads`)
2. Führen Sie `java -jar DartStrainingsspiel-$Version.jar` aus <br>
   (`$Version` durch die Versionsnummer der heruntergeladenen Datei ersetzen)

## Häufige Probleme

###### Error: Could not find or load main class Main <br> Caused by: java.lang.NoClassDefFoundError: javafx/application/Application

Ihre Java-Installation enthält keine OpenJFX (Javafx) Bibliothek. <br>
Installieren Sie ein JDK, das diese enthält (z.B. Azul-Zulu) und versuchen es erneut. <br>
(Unter Windows können Sie auch die OpenJFX-Runtime verwenden.)

## Entwicklung

Ich benutze Intellij-Community von JetBrains mit dem Azul-Zulu JDK (18.0.2).

## Mitwirkende

* Michael S. - Idee, Beratung, Darts-Spezifisches
* Specktulatius - Grundlage des Projekts

<br>

# English Version

## **Darts Training Game**

This is a small Java project with openJFX (JavaFX) to help you train your Darts skills with a point reward system.

Specktulatius made this for Michael some time ago. <br>
I almost reworked everything, but he still remains an author in the Docs. :)

## Instructions for the game:

Hang up any practice ring at the T20 segment and attempt to hit within the ring.
You will see the points system listed below.
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

You will need an openJFX runtime or a JDK, which includes it (e.g. Azul-Zulu).

You can then just start is as you would any jar-file.

### Windows

###### Option 1

1. Double-click on the downloaded jar-file in your file explorer (or right-click -> "Open")
2. Enjoy

###### Option 2

1. Open Powershell
2. Navigate to the folder you downloaded the file to with `cd`
   <br> (e.g. `cd C:/users/user/Downloads`)
3. Execute `java -jar DartsTrainingsspiel-$VERSION.jar`
   <br> (replace `$VERSION` with the version number you downloaded)

### Linux

1. Navigate to the folder you downloaded the file to with `cd` <br>
   (e.g. `cd C:/users/user/Downloads`)
2. Execute `java -jar DartsTrainingsspiel-$VERSION.jar` <br>
   (replace `$VERSION` with the version number you downloaded)

## Common Issues

###### Error: Could not find or load main class Main <br> Caused by: java.lang.NoClassDefFoundError: javafx/application/Application

Your java installation does not have openJFX (JavaFX) included. <br>
Install and use one that does (e.g. Azul-Zulu) and try again. <br>
(If your on Windows you can also try using the openJFX runtime.)

## Development

I use IntelliJ-Community by JetBrains with the Azul-Zulu JDK (18.0.2).

## Contributors

* Michael S. - Idea, Counseling, Darts specifics
* Specktulatius - Base project