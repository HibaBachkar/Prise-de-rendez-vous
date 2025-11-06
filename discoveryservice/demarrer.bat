@echo off
echo Démarrage Eureka Server (Port 8761)...
cd /d %~dp0
cd ..
if exist mvnw.cmd (
    call mvnw.cmd -pl discoveryservice -am spring-boot:run
) else (
    mvn -pl discoveryservice -am spring-boot:run
)
pause

