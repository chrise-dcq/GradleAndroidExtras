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
class SetupAndroidBuildTask extends DefaultTask 
{
    // groovy resource locations
    def String PLUGIN_RESOURCE_DIR = "/"
    
    // standard definitions
    def String MANIFEST = "AndroidManifest.xml"
    
    // parent directories
    def String SRC_MAIN_DIR = "src/main/"
    def String SRC_TEST_DIR = "src/instrumentTest/"

    // android specific directories
    def String JAVA_DIR = "java"
    def String RESOURCES_DIR = "resources"
    def String RES_DIR = "res"
    def String ASSETS_DIR = "assets"
    def String AIDL_DIR = "aidl"
    def String RS_DIR = "rs"
    def String JNI_DIR = "jndi"

    // general subdirectories to the res directory
    def String DRAWABLE = "drawable"
    def String DRAWABLE_LDPI = "drawable-ldpi"
    def String DRAWABLE_MDPI = "drawable-mdpi"
    def String DRAWABLE_HDPI = "drawable-hdpi"
    def String DRAWABLE_XHDPI = "drawable-xhdpi"
    def String LAYOUT = "layout"
    def String MENU = "menu"
    def String VALUES = "values"
    def String VALUES_V11 = "values-v11"
    def String VALUES_V14 = "values-v14"
    
    @TaskAction
    def setupAndroidBuild()
    {
        // standard java directories
        new File(SRC_MAIN_DIR + JAVA_DIR).mkdirs();
        new File(SRC_MAIN_DIR + RESOURCES_DIR).mkdirs();

        // android app special directories
        new File(SRC_MAIN_DIR + RES_DIR + DRAWABLE).mkdirs();
        new File(SRC_MAIN_DIR + RES_DIR + DRAWABLE_LDPI).mkdirs();
        new File(SRC_MAIN_DIR + RES_DIR + DRAWABLE_MDPI).mkdirs();
        new File(SRC_MAIN_DIR + RES_DIR + DRAWABLE_HDPI).mkdirs();
        new File(SRC_MAIN_DIR + RES_DIR + DRAWABLE_XHDPI).mkdirs();
        new File(SRC_MAIN_DIR + RES_DIR + LAYOUT).mkdirs();
        new File(SRC_MAIN_DIR + RES_DIR + MENU).mkdirs();
        new File(SRC_MAIN_DIR + RES_DIR + VALUES).mkdirs();
        new File(SRC_MAIN_DIR + RES_DIR + VALUES_V11).mkdirs();
        new File(SRC_MAIN_DIR + RES_DIR + VALUES_V14).mkdirs();
        new File(SRC_MAIN_DIR + ASSETS_DIR).mkdirs();
        new File(SRC_MAIN_DIR + AIDL_DIR).mkdirs();
        new File(SRC_MAIN_DIR + RS_DIR).mkdirs();
        new File(SRC_MAIN_DIR + JNI_DIR).mkdirs();

        // standard test directories
        new File(SRC_TEST_DIR + JAVA_DIR).mkdirs();
        new File(SRC_TEST_DIR + RESOURCES_DIR).mkdirs();
        
        // TODO: create/copy AndroidManifest.xml and project.properties
        new File(SRC_MAIN_DIR + MANIFEST) << getClass().getResourceAsStream(PLUGIN_RESOURCE_DIR + MANIFEST).bytes;
    }

}
