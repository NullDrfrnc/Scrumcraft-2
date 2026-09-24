# Scrumcraft-2

Scrumcraft 2 is een vervolg op Scrumcraft, een minecraft mod gemaakt tijdens een hackathon van school. Net als Scrumcraft zal Scrumcraft 2 vol zitten met geweldige referenties naar onze ervaringen hier op de opleiding en binnen Scalda.

---

Clone dit project en open het met intelliJ. Deze begint dan het gradle project te importeren.  
Als je tijdens dit process een error krijgt over je Gradle versie, ga dan naar `Setting > Build, Execution, Deployment > Build Tools > Gradle` en selecteer hier je project in de `Gradle Projects` lijst. Zet hier vervolgens je `Gradle JVM` naar `java 25`. 
Je kan hier ook komen door op `open settings` te klikken in de error log.  
Zet vervolgens je project zn SDK naar java 25 onder `Project Structure`.

## Server
De mod draait op Minecraft 26.3 (Fabric). Bouw de jar met `./gradlew build`, die komt in `build/libs/`.  
Op de server (Java 25): installeer Fabric loader 0.19.5+ via de [Fabric server launcher](https://fabricmc.net/use/server/) en zet `Scrumcraft2-<versie>.jar` en [Fabric API](https://modrinth.com/mod/fabric-api) voor 26.3 in de `mods` map.  
Spelers hebben dezelfde mod en Fabric API ook in hun client nodig.

## Items toevoegen
[test](https://docs.fabricmc.net/develop/items/first-item)

