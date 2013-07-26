cd ./plugin/
gradle build install

cd ../sample/
rm -rf src/
gradle setupAndroidBuild
