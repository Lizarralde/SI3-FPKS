rem set PATH=../../lib/;../../jre/bin/;../ressources/lib/
rem javac -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar;../ressources/lib/xstream-1.4.4.jar;../ressources/lib/miglayout-4.0-swing.jar -Djava.library.path=../ressources/lib/ -d ../bin devintAPI/*.java jeu/*.java

javac -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar;../ressources/lib/ -d ../bin devintAPI/*.java jeu/*.java  
pause
