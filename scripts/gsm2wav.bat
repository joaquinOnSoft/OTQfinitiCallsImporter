REM GSM to WAV conversion
REM ---------------------
REM 
REM Usage:
REM 
REM 	c:\scripts\gsm2wav.bat PATH-WITH-GSM-FILES
REM 
REM Example:
REM 
REM 	C:\scripts\gsm2wav.bat c:\audios
@ECHO OFF
CLS
ECHO.

IF "%~1"=="" GOTO NO_PARAMS


:GSM2WAV
REM Please, modify this path with the right one of your environment
SET FFMPEG=C:\ffmpeg-2023-01-12-git-fc263f073e-essentials_build\bin\ffmpeg.exe
cd %1
FOR %%G IN (*.gsm) DO %FFMPEG% -i %%G %%~nG.wav
EXIT /B


:NO_PARAMS
ECHO:
ECHO GSM to WAV conversion
ECHO ---------------------
ECHO:
ECHO Usage: 
ECHO:
ECHO 	%0 PATH-WITH-GSM-FILES
ECHO:
ECHO Example:
ECHO:
ECHO 	%0 c:\temp
ECHO:
ECHO:
EXIT /B