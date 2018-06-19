@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  UTServer startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and UT_SERVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\UTServer-1.0-SNAPSHOT.jar;%APP_HOME%\lib\log4j-api-2.11.0.jar;%APP_HOME%\lib\log4j-core-2.11.0.jar;%APP_HOME%\lib\airline-0.6.jar;%APP_HOME%\lib\json-simple-1.1.1.jar;%APP_HOME%\lib\hk2-2.5.0-b30.jar;%APP_HOME%\lib\jersey-hk2-2.26.jar;%APP_HOME%\lib\jackson-core-2.9.0.jar;%APP_HOME%\lib\jackson-databind-2.9.0.jar;%APP_HOME%\lib\jackson-dataformat-csv-2.9.0.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\jersey-media-json-jackson-2.26.jar;%APP_HOME%\lib\jmockit-1.31.jar;%APP_HOME%\lib\hk2-extras-2.5.0-b30.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.8.11.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.8.11.jar;%APP_HOME%\lib\jackson-datatype-jaxrs-2.8.11.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.8.11.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.8.11.jar;%APP_HOME%\lib\grizzly-http-server-2.4.3.jar;%APP_HOME%\lib\jersey-container-grizzly2-servlet-2.27.jar;%APP_HOME%\lib\hk2-metadata-generator-2.5.0-b61.jar;%APP_HOME%\lib\jedis-2.9.0.jar;%APP_HOME%\lib\grizzly-http-server-monitoring-2.3.28.jar;%APP_HOME%\lib\grizzly-http-monitoring-2.3.28.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\guava-12.0.jar;%APP_HOME%\lib\config-types-2.5.0-b30.jar;%APP_HOME%\lib\hk2-core-2.5.0-b30.jar;%APP_HOME%\lib\hk2-config-2.5.0-b30.jar;%APP_HOME%\lib\hk2-runlevel-2.5.0-b30.jar;%APP_HOME%\lib\class-model-2.5.0-b30.jar;%APP_HOME%\lib\javax.ws.rs-api-2.1.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.jar;%APP_HOME%\lib\jersey-entity-filtering-2.26.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.8.11.jar;%APP_HOME%\lib\javax.servlet-api-4.0.0.jar;%APP_HOME%\lib\grizzly-http-servlet-2.4.0.jar;%APP_HOME%\lib\commons-pool2-2.4.2.jar;%APP_HOME%\lib\grizzly-framework-monitoring-2.3.28.jar;%APP_HOME%\lib\jsr305-1.3.9.jar;%APP_HOME%\lib\tiger-types-1.4.jar;%APP_HOME%\lib\hibernate-validator-5.2.4.Final.jar;%APP_HOME%\lib\asm-all-repackaged-2.5.0-b30.jar;%APP_HOME%\lib\gmbal-api-only-3.2.0-b003.jar;%APP_HOME%\lib\gmbal-3.2.0-b003.jar;%APP_HOME%\lib\jboss-logging-3.2.1.Final.jar;%APP_HOME%\lib\classmate-1.1.0.jar;%APP_HOME%\lib\management-api-3.0.0-b012.jar;%APP_HOME%\lib\pfl-basic-1.0.0-b001.jar;%APP_HOME%\lib\pfl-tf-1.0.0-b001.jar;%APP_HOME%\lib\pfl-tf-tools-1.0.0-b001.jar;%APP_HOME%\lib\pfl-basic-tools-1.0.0-b001.jar;%APP_HOME%\lib\pfl-asm-1.0.0-b001.jar;%APP_HOME%\lib\hk2-locator-2.5.0-b42.jar;%APP_HOME%\lib\javassist-3.22.0-CR2.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.8.11.jar;%APP_HOME%\lib\jersey-container-servlet-2.27.jar;%APP_HOME%\lib\jersey-container-grizzly2-http-2.27.jar;%APP_HOME%\lib\jersey-common-2.27.jar;%APP_HOME%\lib\jersey-server-2.27.jar;%APP_HOME%\lib\jersey-client-2.27.jar;%APP_HOME%\lib\jersey-media-jaxb-2.27.jar;%APP_HOME%\lib\grizzly-http-2.4.3.jar;%APP_HOME%\lib\hk2-api-2.5.0-b61.jar;%APP_HOME%\lib\hk2-utils-2.5.0-b61.jar;%APP_HOME%\lib\javax.inject-2.5.0-b61.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.27.jar;%APP_HOME%\lib\grizzly-framework-2.4.3.jar;%APP_HOME%\lib\aopalliance-repackaged-2.5.0-b61.jar

@rem Execute UTServer
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %UT_SERVER_OPTS%  -classpath "%CLASSPATH%" uts.cli.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable UT_SERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%UT_SERVER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
