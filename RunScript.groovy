dryRun = true

def runCommand(command){
    if(! dryRun){
        Process process = command.execute()
        def out = new StringBuffer()
        def err = new StringBuffer()
        process.consumeProcessOutput( out, err )
        process.waitFor()
        if( out.size() > 0 ) println out
        if( err.size() > 0 ) println err
    }
}

def checkDependencies() {
    def profiles = [
            "it-default",
            "it-wildfly-managed",
            "it-wildfly-remote",
            "it-openliberty-managed",
            "it-openliberty-remote"
    ] as String[]
    def goals = [
            "clean",
            "dependency:resolve",
            "dependency:sources",
            "dependency:resolve-plugins",
            "dependency:analyze",
            "dependency:analyze-dep-mgt",
            "dependency:analyze-duplicate",
            "dependency:tree"
    ] as String[]
    profiles.each {
        def cmd1 = "./mvnw -P${it} " + goals.join(' ')
        println cmd1
        runCommand(cmd1)
    }
}

def runRemoteLiberty(){
    def cmd1 = "./mvnw -Pit-openliberty-remote clean install liberty:deploy test"
    println cmd1
    runCommand(cmd1)
}

def runRemoteWildfly(){
    def cmd1 = "./mvnw -Pit-wildfly-remote clean install wildfly:deploy test"
    println cmd1
    runCommand(cmd1)
}

def runManagedWildfly(){
    def cmd1 = "./mvnw -Pit-wildfly-managed clean install wildfly:start test"
    def cmd2 = "./mvnw -Pit-wildfly-managed wildfly:shutdown"
    println cmd1
    runCommand(cmd1)
    println cmd2
    runCommand(cmd2)
}

def runManagedLiberty(){
    def cmd1 = "./mvnw -Pit-openliberty-managed clean install liberty:start test"
    def cmd2 = "./mvnw -Pit-openliberty-managed liberty:stop"
    println cmd1
    runCommand(cmd1)
    println cmd2
    runCommand(cmd2)
}

def main(){
    checkDependencies()
    runManagedWildfly()
    //runRemoteWildfly()
    //runManagedLiberty()
    //runRemoteLiberty()
}

main()