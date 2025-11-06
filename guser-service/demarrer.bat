@echo off
echo Démarrage guser-service (Port 8081)...
cd /d %~dp0
cd ..
if exist mvnw.cmd (
    call mvnw.cmd -pl guser-service -am spring-boot:run
) else (
    mvn -pl guser-service -am spring-boot:run
)
pause

