cd ./gradle-android-extras/
gradle build install

cd ../sample/
rm -rf src/
gradle -PpackageName=org.sage42.myexample setupAndroidBuild
