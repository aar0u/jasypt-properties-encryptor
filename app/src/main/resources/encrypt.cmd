@echo off
if [%1]==[]  (
    @echo Drag properties file on the batch file to run.
    @goto end
)

set /p pwd="Encrypt password: "
java -jar %~dp0app-uber.jar %pwd% "%1"

:end

pause