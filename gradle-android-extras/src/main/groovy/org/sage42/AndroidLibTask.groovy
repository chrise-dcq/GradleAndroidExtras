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
class AndroidLibTask extends DefaultTask
{
    // arguments for this plugin
    def String ARG_ACTION = "action"

    // possible values for the "action" argument
    def String ACTION_HELP = "help"
    def String ACTION_ADD_LIBRARY = "add"

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
        if (action.equals(ACTION_ADD))
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
        println("Add library")
    }
    
    /**
     * Show plugin help
     * 
     * @return
     */
    def doShowHelp()
    {
        println("Show help")
    }
}
