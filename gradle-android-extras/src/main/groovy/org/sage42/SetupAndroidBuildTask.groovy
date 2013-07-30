package org.sage42

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Copyright (C) 2013- Sage 42 App Sdn Bhd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Corey Scott (corey.scott@sage42.com)
 *
 */
class SetupAndroidBuildTask extends DefaultTask {

    // groovy resource locations
    def String PLUGIN_RESOURCE_DIR = "/"
    def String BASE = "base/"

    // standard definitions
    def String MANIFEST = "AndroidManifest.xml"
    def String IC_LAUNCHER = "ic_launcher.png"
    def String STRINGS = "strings.xml"
    def String STYLES = "styles.xml"
    def String PROGUARD = "proguard-project.txt"
    def String PROJECT_PROPS = "project.properties"

    // wildcards used to customize the resultant files
    def String WILDCARD_PACKAGE = "##packageName##"

    // parent directories
    def String SRC_MAIN_DIR = "src/main/"
    def String SRC_TEST_DIR = "src/instrumentTest/"

    // android specific directories
    def String JAVA_DIR = "java/"
    def String RESOURCES_DIR = "resources/"
    def String RES_DIR = "res/"
    def String ASSETS_DIR = "assets/"
    def String AIDL_DIR = "aidl/"
    def String RS_DIR = "rs/"
    def String JNI_DIR = "jndi/"

    // general subdirectories to the res directory
    def String DRAWABLE = "drawable/"
    def String DRAWABLE_LDPI = "drawable-ldpi/"
    def String DRAWABLE_MDPI = "drawable-mdpi/"
    def String DRAWABLE_HDPI = "drawable-hdpi/"
    def String DRAWABLE_XHDPI = "drawable-xhdpi/"
    def String LAYOUT = "layout/"
    def String MENU = "menu/"
    def String VALUES = "values/"
    def String VALUES_V11 = "values-v11/"
    def String VALUES_V14 = "values-v14/"

    @TaskAction
    def setupAndroidBuild()
    {
        // standard java directories
        createBaseDirectories();

        // android app special directories
        createAndroidDirectories();

        // standard test directories
        createTestDirectories();

        // copy AndroidManifest.xml (using custom packageName if supplied)
        createManifest();

        // copy other default android files
        // these files are taken from the eclipse new project tool
        copyResources();
    }

    /**
     * Create the directories the top level directories that will contain  
     * everything else
     */
    def createBaseDirectories()
    {
        createDir(SRC_MAIN_DIR + JAVA_DIR);
        createDir(SRC_MAIN_DIR + RESOURCES_DIR);
    }

    /**
     * Create the directories for the android app (top-level) project     
     */
    def createAndroidDirectories()
    {
        createDir(SRC_MAIN_DIR + RES_DIR + DRAWABLE);
        createDir(SRC_MAIN_DIR + RES_DIR + DRAWABLE_LDPI);
        createDir(SRC_MAIN_DIR + RES_DIR + DRAWABLE_MDPI);
        createDir(SRC_MAIN_DIR + RES_DIR + DRAWABLE_HDPI);
        createDir(SRC_MAIN_DIR + RES_DIR + DRAWABLE_XHDPI);
        createDir(SRC_MAIN_DIR + RES_DIR + LAYOUT);
        createDir(SRC_MAIN_DIR + RES_DIR + MENU);
        createDir(SRC_MAIN_DIR + RES_DIR + VALUES);
        createDir(SRC_MAIN_DIR + RES_DIR + VALUES_V11);
        createDir(SRC_MAIN_DIR + RES_DIR + VALUES_V14);
        createDir(SRC_MAIN_DIR + ASSETS_DIR);
        createDir(SRC_MAIN_DIR + AIDL_DIR);
        createDir(SRC_MAIN_DIR + RS_DIR);
        createDir(SRC_MAIN_DIR + JNI_DIR);
    }

    /**
     * Create the directories for the test project     
     */
    def createTestDirectories()
    {
        createDir(SRC_TEST_DIR + JAVA_DIR);
        createDir(SRC_TEST_DIR + RESOURCES_DIR);
    }

    /**
     * Create the specified directory
     */
    def createDir(path)
    {
        File dest = new File(path)
        if (dest.exists())
        {
            println("Skipping (exists): " + path)
        }
        else
        {
            println("Creating: " + path)
            dest.mkdirs();
        }
    }

    /**
     * Copy over standard AndroidManifest and customize with supplied (or default) package name
     */
    def createManifest()
    {
        def customPackageName = project.hasProperty("packageName") ? project.getProperty("packageName") : "com.example.android"

        File destAndroidManifest = new File(SRC_MAIN_DIR + MANIFEST);

        if (destAndroidManifest.exists())
        {
            println("Skipping (exists): " + SRC_MAIN_DIR + MANIFEST)
        }
        else
        {
            println("Creating AndroidManifest.xml with packageName: " + customPackageName)
            getClass().getResourceAsStream(PLUGIN_RESOURCE_DIR + MANIFEST).eachLine
            { line ->
                destAndroidManifest.append(line.replace(WILDCARD_PACKAGE, customPackageName))
                destAndroidManifest.append(System.getProperty("line.separator"))
            }
        }
    }

    /**
     * Copy other standard project resources
     * NOTE: there is probably a better way but i can't get the FileTree and FileCollection as I expected    
     */
    def copyResources()
    {
        // launcher icons
        copyFile(PLUGIN_RESOURCE_DIR + DRAWABLE_MDPI + IC_LAUNCHER, SRC_MAIN_DIR + RES_DIR + DRAWABLE_MDPI + IC_LAUNCHER)
        copyFile(PLUGIN_RESOURCE_DIR + DRAWABLE_HDPI + IC_LAUNCHER, SRC_MAIN_DIR + RES_DIR + DRAWABLE_HDPI + IC_LAUNCHER)
        copyFile(PLUGIN_RESOURCE_DIR + DRAWABLE_XHDPI + IC_LAUNCHER, SRC_MAIN_DIR + RES_DIR + DRAWABLE_XHDPI + IC_LAUNCHER)

        // strings and styles
        copyFile(PLUGIN_RESOURCE_DIR + VALUES + STRINGS, SRC_MAIN_DIR + RES_DIR + VALUES + STRINGS)
        copyFile(PLUGIN_RESOURCE_DIR + VALUES + STYLES, SRC_MAIN_DIR + RES_DIR + VALUES + STYLES)
        copyFile(PLUGIN_RESOURCE_DIR + VALUES_V11 + STYLES, SRC_MAIN_DIR + RES_DIR + VALUES_V11 + STYLES)
        copyFile(PLUGIN_RESOURCE_DIR + VALUES_V14 + STYLES, SRC_MAIN_DIR + RES_DIR + VALUES_V14 + STYLES)

        // other default files
        copyFile(PLUGIN_RESOURCE_DIR + BASE + PROGUARD, SRC_MAIN_DIR + PROGUARD)
        copyFile(PLUGIN_RESOURCE_DIR + BASE + PROJECT_PROPS, SRC_MAIN_DIR + PROJECT_PROPS)

    }

    def copyFile(srcPath, destPath)
    {
        File destFile = new File(destPath)
        if (destFile.exists())
        {
            println("Skipping (exists): " + destPath)
        }
        else
        {
            println("Creating: " + destPath)
            destFile << getClass().getResourceAsStream(srcPath).bytes
        }
    }

}
