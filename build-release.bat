@echo off
REM 清除所有可能导致问题的 Java 环境变量
set JAVA_TOOL_OPTIONS=
set _JAVA_OPTIONS=
set JAVA_OPTS=
set GRADLE_OPTS=

REM 显示当前 Java 版本
echo Checking Java version...
java -version
echo.

REM 构建 Release APK
echo Building Release APK...
call gradlew.bat assembleRelease

echo.
echo Build complete!
pause
