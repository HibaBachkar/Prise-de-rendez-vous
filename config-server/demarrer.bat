@echo off
echo Démarrage Config Server (Port 8888)...
cd /d %~dp0
cd ..
if exist mvnw.cmd (
    call mvnw.cmd -pl config-server -am spring-boot:run
) else (
    mvn -pl config-server -am spring-boot:run
)
pause

