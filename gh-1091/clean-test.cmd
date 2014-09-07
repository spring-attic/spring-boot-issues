@pushd "%~dp0"
start "gradlew clean test" cmd /k call gradlew clean test
@popd
