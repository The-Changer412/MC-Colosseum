./gradlew build
rm "C:\Users\Owner\Documents\MultiMC\instances\MC Colosseums\.minecraft\mods\Minecraft_Colosseum-1.0.0.jar"
cp ".\build\libs\Minecraft_Colosseum-1.0.0.jar" "C:\Users\Owner\Documents\MultiMC\instances\MC Colosseums\.minecraft\mods"
rm "C:\Users\Owner\Documents\Minecraft\Servers\MC Colosseums Server\mods\Minecraft_Colosseum-1.0.0.jar"
cp ".\build\libs\Minecraft_Colosseum-1.0.0.jar" "C:\Users\Owner\Documents\Minecraft\Servers\MC Colosseums Server\mods"
cd "C:\Users\Owner\Documents\MultiMC"
./MultiMC -l "MC Colosseums" -s 127.0.0.1:25565
cd "C:\Users\Owner\Documents\Minecraft\Servers\MC Colosseums Server"
./start.sh