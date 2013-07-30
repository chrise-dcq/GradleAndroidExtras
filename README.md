GradleAndroidExtras
===================

A humble attempt at making android development easier with custom Gradle plugins
WARNING: This is my first attempt at any Groovy/Gradle so this is definiately a "work in progress".


##Feature: setupAndroidBuild

This creates the default android studio/gradle structure and adds some basic android files (e.g. AndroidManifest.xml)

TODO: 
* Add more support for command line arguments that tweak the behavior (e.g packageName)
* Be able to detect the existance of files in these locations and warn but not overwrite them.
* Add support for adding library (sub) projects

###Usage:

1.  Create a folder for your new project

2.  Create a basic gradle.build config, for example

```
    buildscript 
    {
        // define link to local maven repo
        def localMavenRepo = 'file://' + new File(System.getProperty('user.home'), '.m2/repository').absolutePath
        repositories 
        {
            mavenCentral()
            mavenRepo url: localMavenRepo
        }
        
        dependencies 
        {
            classpath 'org.sage42:gradle-android-extras:1.0-SNAPSHOT'
        }
    }

    apply plugin: 'setupAndroidBuild'
```

3. Run the command


```
    gradle setupAndroidBuild
    
    // Optional Command-line Arguments (prefix each with -P):
    packageName: changes the packageName inside the AndroidManifest.xml (default: com.android.example)
    
    // Sample with custom package
    gradle -PpackageName=com.foo.bar setupAndroidBuild
```

4. Enjoy

5. Support the project, fork, star, advertise, send pull requests, add feature requests, or just send us happy thoughts.


----------------------------------------------------------------------------------------------------------------------------------------

##Feature(TODO): Add android libraries to project

I want this feature to be able to as much as possible to obtain and configure standard android libraries.
For example to be able issue 1 command and have the plugin obtain the latest copy of ActionBarSherlock, add add all the appropriate config to the project
