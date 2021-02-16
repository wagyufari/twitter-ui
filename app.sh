export PATH=/opt/homebrew/bin:$PATH
./gradlew assembleDevelopmentDebug && ./gradlew installDevelopmentDebug
adb shell am force-stop com.mayburger.twitter
adb shell am start -n com.mayburger.twitter/.ui.SplashActivity