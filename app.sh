export PATH=/opt/homebrew/bin:$PATH
./gradlew assembleDevelopmentDebug && ./gradlew installDevelopmentDebug
adb shell am force-stop com.mayburger.starter
adb shell am start -n com.mayburger.starter/.MainActivity