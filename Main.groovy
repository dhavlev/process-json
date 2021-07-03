package main

import groovy.cli.commons.CliBuilder
import groovy.cli.commons.OptionAccessor
import groovy.json.JsonException
import groovy.json.JsonSlurper

class Main {
    static void main(args){
        def cli = new CliBuilder(usage: 'groovy Main.groovy [required options] ', header: 'Options:')
        cli.j(args: 1, longOpt: 'json', argName: 'json', 'Input valid json string - {"a":{"b":{"c":"d"}}}', required: true)
        cli.k(args: 1, longOpt: 'key', argName: 'key', 'Input key - a/b/c ', required: true)

        OptionAccessor options = cli.parse(args)

        if (!options) {
            System.exit(1)
        }
        println("")
        ProcessJson output = new ProcessJson(options.j, options.k.toString().split("\\/"))
        output.findValue()
        if(!output.value){
            println("ERROR: Unable to find value. Make sure key is present in the json.")
            System.exit(1)
        }
        println("Output --> ${output.value}")
    }
}

class ProcessJson{
    def json
    String[] key
    def value

    ProcessJson(String json, String[] key){
        try{
            this.json = new JsonSlurper().parseText(json)
        } catch(JsonException e){
            println("ERROR: Problem parsing/reading json, make sure json string is well formed")
            System.exit(1)
        }
        this.key = key
    }

    def findValue(){
        this.value = this.json
        this.key.each {
            this.value = this.value[it]
        }
        return this.value
    }
}

/*
groovy Main.groovy -j '{"tech":"node","labels":{"micro-service":"workflowagent"},"deployment":{"image":{"sleep":5,"pullSecrets":"gcpdev01aksacr","registry":"gcpdevacr.azurecr.io","repository":"digital/dotcom/workflowagent","args":["export PORT=3000 && node dist/index.js"],"tag":"296"},"containerPort":3000,"resources":{"limits":{"cpu":"300m","memory":"512Mi"},"requests":{"cpu":"250m","memory":"256Mi"}}}}' -k 'deployment/resources/limits'
*/

