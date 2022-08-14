./gradlew build
echo build the mod

sleep 1
echo 5
sleep 1
echo 4
sleep 1
echo 3
sleep 1
echo 2
sleep 1
echo 1
sleep 1
echo GO!!!

rm "C:\Users\Owner\Documents\MultiMC\instances\MC Colosseums\.minecraft\mods\Minecraft_Colosseum-1.0.0.jar"
rm "C:\Users\Owner\Documents\Minecraft\Servers\MC Colosseums Server\mods\Minecraft_Colosseum-1.0.0.jar"
echo deleted the old mods

cp ".\build\libs\Minecraft_Colosseum-1.0.0.jar" "C:\Users\Owner\Documents\MultiMC\instances\MC Colosseums\.minecraft\mods"
cp ".\build\libs\Minecraft_Colosseum-1.0.0.jar" "C:\Users\Owner\Documents\Minecraft\Servers\MC Colosseums Server\mods"
echo copy and paste the new mods

cd "C:\Users\Owner\Documents\Minecraft\Servers\MC Colosseums Server"
start ./start.sh
echo started up the server

cd "C:\Users\Owner\Documents\MultiMC"
start ./MultiMC -l "MC Colosseums" -s 127.0.0.1:25565
echo started up the client