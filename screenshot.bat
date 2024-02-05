@echo off
setlocal
for /f "tokens=2 delims==" %%a in ('wmic OS Get localdatetime /value') do set "dt=%%a"
set "dt=%dt:~0,8%_%dt:~8,6%"
IF "%~1"=="" (
    set "imageName=%dt%"
) ELSE (
    set "imageName=%~1_%dt%"
)
set "imageName=%imageName%.png"
adb shell screencap -p /sdcard/%imageName%
adb pull /sdcard/%imageName% %imageName%
adb shell rm /sdcard/%imageName%
start %imageName%
endlocal