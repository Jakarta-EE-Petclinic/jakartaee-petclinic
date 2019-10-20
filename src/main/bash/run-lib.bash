#!/usr/bin/env bash

#export MAVEN=./mvnw
export MAVEN=mvn
export LOGFILE=log/run.log.txt

export ALL_SERVERS="wildfly openliberty glassfish payara"
export ALL_BROWSERS="chrome firefox safari opera"

export ALL_SRV_PROFILES_REMOTE="run-wildfly-remote run-openliberty-remote run-glassfish-remote"
export ALL_SRV_PROFILES_MANAGED="run-wildfly-managed run-openliberty-managed"
export ALL_SRV_PROFILES="it-default $ALL_SRV_PROFILES_REMOTE $ALL_SRV_PROFILES_MANAGED"
export ALL_BROWSER_PROFILES="it-browser-chrome it-browser-firefox it-browser-safari it-browser-opera"

export BROWSER_PROFILE_CHROME=it-browser-chrome
export BROWSER_PROFILE_FIREFOX=it-browser-firefox
export BROWSER_PROFILE_SAFARI=it-browser-safari
export BROWSER_PROFILE_OPERA=it-browser-opera

function echoEnv() {
  echo "CLASSPATH: $CLASSPATH"
  echo "JAVA_HOME: $JAVA_HOME"
  echo "HOME:      $HOME"
}

function setSerialVersionId() {
  LOGFILE2="log/setSerialVersionId.log.txt"
  RUN_SCRIPT="log/setSerialVersionId.sh"
  LOGFILE_RESULT="log/setSerialVersionId.log.txt"
  echo "STARTED"
  echo "$MAVEN -Pprepare-serialver clean install | grep WARNING | grep serialVersionUID > $LOGFILE2"
  #$MAVEN -Pprepare-serialver clean install | grep WARNING | grep serialVersionUID > $LOGFILE2
  echo "FINISHED MAVEN"
  echo "cat $LOGFILE2"
  cat $LOGFILE2 | cut -d" " -f5 | sed "s/^/serialver /g" > $RUN_SCRIPT
  HERE=`pwd`
  cd target/prepareserialver/
  echo "--------------------------------------------------------------------------"
  source ../../$RUN_SCRIPT
  echo "--------------------------------------------------------------------------"
  cd $HERE
  echo "FINISHED"
}

function checkProfileDependencies() {
    BROWSER_PROFILE=$1
    SRV_PROFILE=$2
    echo "--------------------------------------------------------------------------"
    echo " checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE"
    echo "--------------------------------------------------------------------------"
    echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE \\"
    echo "        clean \\"
    echo "        dependency:resolve \\"
    echo "        dependency:sources \\"
    echo "        dependency:resolve-plugins \\"
    echo "        dependency:analyze \\"
    echo "        dependency:analyze-dep-mgt \\"
    echo "        dependency:analyze-duplicate \\"
    echo "        dependency:tree"
    echo "--------------------------------------------------------------------------"
    $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE \
      clean \
      dependency:resolve \
      dependency:sources \
      dependency:resolve-plugins \
      dependency:analyze \
      dependency:analyze-dep-mgt \
      dependency:analyze-duplicate \
      dependency:tree
    echo "--------------------------------------------------------------------------"
}

function checkDependencies() {
  BROWSER_PROFILE=$1
  echo "--------------------------------------------------------------------------"
  echo " check Dependencies $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  for SRV_PROFILE in $ALL_SRV_PROFILES
  do
    checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  done
  echo "------------------------------------"
}

function checkAllDependencies(){
  echo "--------------------------------------------------------------------------"
  echo " checkAllDependencies"
  echo "--------------------------------------------------------------------------"
  for BROWSER_PROFILE in $ALL_BROWSER_PROFILES
  do
      checkDependencies $BROWSER_PROFILE
  done
}

function buildSiblings(){
  echo "--------------------------------------------------------------------------"
  echo " buildSiblings"
  echo "--------------------------------------------------------------------------"
  cd $HOME/IdeaProjects/jakartaee-api
  $MAVEN -Pstaging install
  cd $HOME/IdeaProjects/jakartaee-petclinic
}

function startRemoteAppServerWildfly17(){
  echo "-----------------------------------------"
  echo "start remote AppServer Wildfly 17"
  echo "-----------------------------------------"
  echo "reminder: hit CTRL-C to stop this Server"
  echo "-----------------------------------------"
  cd ~/j/srv/wildfly-17.0.1.Final/bin
  ./standalone.sh
}

function stopRemoteAppServerWildfly17() {
  echo "-----------------------------------------"
  echo "reminder: hit CTRL-C to stop this Server"
  echo "-----------------------------------------"
}

function stopRemoteAppServerGlassfish51(){
  echo "-------------------------------------"
  echo "stop remote AppServer Glassfish 5.1"
  echo "-------------------------------------"
  cd ~/j/srv/glassfish-5.1.0/bin
  ./asadmin stop-domain
  ./asadmin stop-database
}

function startRemoteAppServerGlassfish51(){
  echo "-------------------------------------"
  echo "start remote AppServer Glassfish 5.1"
  echo "-------------------------------------"
  cd ~/j/srv/glassfish-5.1.0/bin
  ./asadmin start-database
  ./asadmin start-domain
  echo "http://localhost:8080/"
}

function stopRemoteAppServerOpenLibertyWlp(){
  echo "-----------------------------------------"
  echo "stop remote AppServer Open Liberty WLP"
  echo "-----------------------------------------"
  cd ~/j/srv/openliberty-19.0.0.9/wlp/bin
  ./server stop
}

function setupRemoteAppServerOpenLibertyWlpDeployServerXml(){
  mkdir -p ~/j/srv/openliberty-19.0.0.9/wlp/usr/servers/defaultServer/lib
  cp -f src/main/liberty/config/server.xml ~/j/srv/openliberty-19.0.0.9/wlp/usr/servers/defaultServer/
}

function setupRemoteAppServerOpenLibertyWlpDeployServerXmlDeployDatabaseJar(){
  $MAVEN clean install -DskipTests=true
  cp -f target/petclinic/WEB-INF/lib/postgresql-42.2.7.jar ~/j/srv/openliberty-19.0.0.9/wlp/usr/servers/defaultServer/lib/postgresql.jar
}

function setupRemoteAppServerOpenLibertyWlp(){
  setupRemoteAppServerOpenLibertyWlpDeployServerXml
  #setupRemoteAppServerOpenLibertyWlpDeployServerXmlDeployDatabaseJar
}

function startRemoteAppServerOpenLibertyWlp(){
  echo "-----------------------------------------"
  echo "start remote AppServer Open Liberty WLP"
  echo "-----------------------------------------"
  setupRemoteAppServerOpenLibertyWlp
  cd ~/j/srv/openliberty-19.0.0.9/wlp/bin
  ./server start
  echo "http://localhost:9080/"
  #tail -f ~/j/srv/openliberty-19.0.0.9/wlp/usr/servers/defaultServer/logs/messages.log &
  tail -f ~/j/srv/openliberty-19.0.0.9/wlp/usr/servers/defaultServer/logs/console.log
}

function testRemoteLiberty(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=test-openliberty-remote
  checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " test Remote Liberty $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE test"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE test
  echo "--------------------------------------------------------------------------"
}

function runRemoteLiberty(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=run-openliberty-remote
  checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Remote Liberty $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy
  echo "--------------------------------------------------------------------------"
  echo " http://10.4.25.161:8080/petclinic/ (IP-Number may vary)"
  echo "--------------------------------------------------------------------------"
}

function testRemoteWildfly(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=test-wildfly-remote
  checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " test Remote Wildfly $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE test"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE test
  echo "--------------------------------------------------------------------------"
}

function runRemoteWildfly(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=run-wildfly-remote
  #checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Remote Wildfly $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy
  echo "--------------------------------------------------------------------------"
  echo " http://localhost:8080/petclinic/"
  echo "--------------------------------------------------------------------------"
}

function runManagedWildfly(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=run-wildfly-managed
  checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Managed Wildfly $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install wildfly:start wildfly:deploy"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install wildfly:start wildfly:deploy
  echo "--------------------------------------------------------------------------"
  echo " App Server is running, to Stop it run: ./server-managed-stop.sh"
  echo "--------------------------------------------------------------------------"
}

function testManagedWildfly(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=test-wildfly-managed
  checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Managed Wildfly $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install "
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install
  echo "--------------------------------------------------------------------------"
}

function stopManagedWildfly(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=test-wildfly-managed
  echo "--------------------------------------------------------------------------"
  echo " run Managed Wildfly $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE wildfly:shutdown"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE wildfly:shutdown
  echo "--------------------------------------------------------------------------"
}

function testManagedLiberty(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=test-openliberty-managed
  checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " test Managed Liberty $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE liberty:test-start-server"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE test"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE liberty:test-start-server
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE test
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server
  echo "--------------------------------------------------------------------------"
}

function runManagedLiberty(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=run-openliberty-managed
  checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Managed Liberty $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install liberty:start-server liberty:deploy"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install liberty:start-server liberty:deploy
  echo "--------------------------------------------------------------------------"
  echo " App Server is running, to Stop it run: ./server-managed-stop.sh"
  echo "--------------------------------------------------------------------------"
}

function stopManagedLiberty(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=run-openliberty-managed
  echo "--------------------------------------------------------------------------"
  echo " stop Managed Liberty $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE liberty:stop-server"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE liberty:stop-server
  echo "--------------------------------------------------------------------------"
}

function runManagedGlassfish(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=run-glassfish-managed
  checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Managed Glassfish"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install
  echo "--------------------------------------------------------------------------"
  echo " App Server is running, to Stop it run: ./server-managed-stop.sh"
  echo "--------------------------------------------------------------------------"
}

function runRemoteGlassfish(){
  BROWSER_PROFILE=$1
  SRV_PROFILE=run-glassfish-remote
  checkProfileDependencies $SRV_PROFILE $BROWSER_PROFILE
  echo "-----------------------------------------"
  echo "run Remote Glassfish"
  echo "-----------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install"
  echo "-----------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$BROWSER_PROFILE clean install
  echo "http://localhost:8080/petclinic/"
  echo "-----------------------------------------"
}

function runQa() {
  BROWSER_PROFILE=$1
  $MAVEN -Pcheck-code -P$BROWSER_PROFILE clean install > $LOGFILE
  cat $LOGFILE
}

function resolveDependencies() {
  BROWSER_PROFILE=$1
  #checkAllDependencies
  checkDependencies $BROWSER_PROFILE
}

function stopManagedServer(){
  echo "-----------------------------------------"
  echo "stop managed AppServer"
	stopManagedWildfly
  #stopManagedLiberty
  #stopManagedGlassfish
  #stopManagedPayara
  echo "-----------------------------------------"
}

function startWithMessage(){
	MESSAGE=$1
  echo "-----------------------------------------"
  echo " $MESSAGE"
  echo "-----------------------------------------"
}

function doneAndReady(){
  echo "-----------------------------------------"
  echo " DONE and READY"
  echo "-----------------------------------------"
}


function main(){
  BROWSER_PROFILE=$1
  echo "-------------------"
  echo " main"
  echo "-------------------"
  #resolveDependencies $BROWSER_PROFILE
  #runQa $BROWSER_PROFILE
  #setSerialVersionId
  #runRemote $BROWSER_PROFILE
  runManaged $BROWSER_PROFILE
  #testManaged $BROWSER_PROFILE
  #testRemote $BROWSER_PROFILE
  echo "-------------------"
  echo " DONE and READY"
  echo "-------------------"
}
