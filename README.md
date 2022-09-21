<sup>[Click here for the english version](#EnglishVersion) </sup>

# **Darts Trainingsspiel**

Idee von Michael S.

Dies ist ein kleines Java -Projekt mit OpenJFX (Javafx),
mit dem Sie Ihre Darts-Fähigkeiten mithilfe eines Punktesystems trainieren können.

Specktulatius hat das Spiel vor einiger Zeit für Michael gemacht. <br>
Allerdings habe ich inzwischen fast alles überarbeitet,
aber er verbleibt dennoch als Autor in den Docs. :)

## Punkte System

| Triple Treffer -><br>Darts im Board  	| 0  	| 1 	| 2 	| 3 	|
|--------------------------------------	|----	|---	|---	|---	|
| 0                                      	| -1 	| - 	| - 	| - 	|
| 1                                      	| 0  	| 1 	| - 	| - 	|
| 2                                      	| 1  	| 2 	| 3 	| - 	|
| 3                                      	| 2  	| 3 	| 4 	| 5 	|

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

Ich benutze Intellij-Community von Netbeans mit dem Azul-Zulu JDK (18.0.2).

<br>

# English Version

## **Darts Training Game**

Idea and requested by Michael S.

This is a small Java project with openJFX (JavaFX) to help you train your Darts skills with a point reward system.

Specktulatius made this for Michael some time ago. <br>
I almost reworked everything, but he still remains an author in the Docs. :)

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

I use IntelliJ-Community by NetBeans with the Azul-Zulu JDK (18.0.2).