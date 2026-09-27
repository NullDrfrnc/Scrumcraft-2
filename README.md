# Scrumcraft-2

Scrumcraft 2 is een vervolg op Scrumcraft, een minecraft mod gemaakt tijdens een hackathon van school. Net als Scrumcraft zal Scrumcraft 2 vol zitten met geweldige referenties naar onze ervaringen hier op de opleiding en binnen Scalda.

---

Clone dit project en open het met intelliJ. Deze begint dan het gradle project te importeren.  
Als je tijdens dit process een error krijgt over je Gradle versie, ga dan naar `Setting > Build, Execution, Deployment > Build Tools > Gradle` en selecteer hier je project in de `Gradle Projects` lijst. Zet hier vervolgens je `Gradle JVM` naar `java 25`. 
Je kan hier ook komen door op `open settings` te klikken in de error log.  
Zet vervolgens je project zn SDK naar java 25 onder `Project Structure`.

## Server
Jar bouwen: `./gradlew build`, de jar staat dan in `build/libs/`.  
Zet de jar samen met [Fabric API](https://modrinth.com/mod/fabric-api) in de `mods` map van een [Fabric](https://fabricmc.net/use/server/) 26.3 server (Java 25).  
Spelers moeten de mod en Fabric API zelf ook installeren.

## Items toevoegen
[test](https://docs.fabricmc.net/develop/items/first-item)

