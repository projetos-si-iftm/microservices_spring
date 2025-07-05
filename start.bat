@echo off
setlocal enabledelayedexpansion
set BASE_DIR=%cd%
for /D %%D in (microservice-*) do (
    echo Iniciando %%D...
    start "%%D" cmd /k "cd /d %BASE_DIR%\%%D && mvn spring-boot:run"
)

echo Todos os microserviços estão sendo iniciados em janelas separadas.
pause