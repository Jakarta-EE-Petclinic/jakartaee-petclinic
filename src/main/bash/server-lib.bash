#!/usr/bin/env bash

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
  ./mvnw clean install -DskipTests=true
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

function startAppServer(){
  echo "-----------------------------------------"
  echo "start remote AppServer"
  #startRemoteAppServerWildfly17
  startRemoteAppServerOpenLibertyWlp
  #startRemoteAppServerGlassfish51
}

function stopAppServer(){
  echo "-----------------------------------------"
  echo "stop remote AppServer"
  #stopRemoteAppServerWildfly17
  stopRemoteAppServerOpenLibertyWlp
  #stopRemoteAppServerGlassfish51
}
