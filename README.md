# Worttrainer-Anwendung

Die Worttrainer-Anwendung ist eine JavaFX-Anwendung, die besonders für Grundschulkinder geeignet ist. Die Schüler können erraten, was das Wort des aktuell angezeigten Bildes ist und am Ende können sie auch sehen, wie viele sie falsch oder richtig lagen. Die Anwendung ist in Java geschrieben und verwendet eine SQL-Datenbank für die Speicherung von Daten. Die Anwendung wurde mit Gradle erstellt und verwendet JavaFX für die Benutzeroberfläche.

## Funktionen

- Benutzeranmeldung und Registrierung
- Anzeige von Wörtern und Bildern
- Wort und das zugehörige Bild für alle Benutzer hinzufügen
- Statistiken anschauen
- Das Spiel neu starten
## Systemanforderungen

Um die Worttrainer-Anwendung auszuführen, benötigen Sie:

- Java SE Development Kit (JDK) 11 oder höher
- JavaFX-Bibliothek (normalerweise in modernen JDK-Versionen enthalten)
- Eine SQL-Datenbank (z. B. SQLite) für die Speicherung von Daten (Konfiguration in `DatabaseHandler` erforderlich)
- Gradle 6.8.3 oder höher

## Installation

1. Laden Sie das Projekt von GitHub herunter oder klonen Sie es auf Ihren lokalen Computer.

2. Stellen Sie sicher, dass Sie JDK 11 oder höher installiert haben.

3. Stellen Sie sicher, dass Sie Gradle 6.8.3 oder höher installiert haben.

4. Stellen Sie sicher, dass Sie eine SQL-Datenbank installiert haben und konfigurieren Sie die Verbindung in `DatabaseHandler`.

5. Führen Sie `gradle run` aus, um die Anwendung auszuführen.
