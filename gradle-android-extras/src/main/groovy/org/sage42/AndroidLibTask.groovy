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
 */
class AndroidLibTask extends DefaultTask
{
    // arguments for this plugin
    def String ARG_ACTION = "action"
    def String ARG_LIBRARY_NAME = "lib"
    
    // possible values for the "action" argument
    def String ACTION_HELP = "help"
    def String ACTION_ADD_LIBRARY = "add"
    
    // libraries to add
    

    /**
     * Main entry point for this task
     * 
     * @return
     */
    @TaskAction
    def main()
    {
        // determine action from args
        def action = project.hasProperty(ARG_ACTION) ? project.getProperty(ARG_ACTION) : null

        // switch between different actions
        if (action.equals(ACTION_ADD_LIBRARY))
        {
            doAddLibrary()
            return
        }
        
        // default action prints help
        doShowHelp();
    }

    /**
     * Add library to current project
     * 
     * @return
     */
    def doAddLibrary()
    {
        def libName = project.hasProperty(ARG_LIBRARY_NAME) ? project.getProperty(ARG_LIBRARY_NAME) : null
        if (libName == null)
        {
            println("Please supply argument in the form: -P" + ARG_LIBRARY_NAME + "=<some value>")
            return
        }

        /**
         * Notes: to install ActionBarSherlock, you also need to install android support lib v4
         */
        
        // TODO: check if its installed already
        
        // TODO: download the AAR file from online maven repo (if needed)
        
        // TODO: install AAR into local repo
        
        // TODO: update project config
    }
    
    /**
     * Show plugin help
     * 
     * @return
     */
    def doShowHelp()
    {
        println("\n*** Android Lib Task ***\n")
        println("Usage: gradle -Paction=<action> -P<additional param>=<value> androidLib\n")
        println("Supported actions: ")
        println(ACTION_ADD_LIBRARY + " - Add android library to the current project")
        println(ACTION_HELP + " - Displays this help (default action)")
        println("\n")
    }
}
