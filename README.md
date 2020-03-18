# jakartaee8-petclinic
Jakarta EE 8 Petclinic -  a JSF and Jakarta EE Version of Spring Petclinic -  and Update from Java EE 7 Petclinic

## What is this?
* JSF and Jakarta EE 8 Version of [Spring Petclinic](https://github.com/spring-projects/spring-petclinic)
* An Update of [Java EE 7 Petclinic](https://github.com/thomaswoehlke/javaee7-petclinic)

## Where is it?
* Github: [https://github.com/thomaswoehlke/jakartaee8-petclinic](https://github.com/thomaswoehlke/jakartaee8-petclinic)
* Demo on OpenShift: [http://jakartaee8petclinic.rhcloud.com/](http://jakartaee8petclinic.rhcloud.com)
* Blog: [http://thomas-woehlke.blogspot.de/2018/10/jakarta-ee-8-petclinic.html](http://thomas-woehlke.blogspot.de/2018/10/jakarta-ee-8-petclinic.html)

## Motivation
* Some experimental Work with JSF, Jakarta EE 8 and Java EE 8. 
* Comparison between Java EE / Jakarta EE and Spring Frameworks.

## Spring Petclinic
* Github: [https://github.com/spring-projects/spring-petclinic](https://github.com/spring-projects/spring-petclinic)

# Software Design

## Domain Class Modell

![Figure Domain Class Modell](etc/images/DomainClassModell.jpg)

## Use Cases

![Figure Uses Cases Vet](etc/images/UseCases.jpg)

![Figure Uses Cases Owner](etc/images/UseCasesOwner.jpg)

## Page Flow

![Figure Pageflow](etc/images/Pageflow.jpg)

## Screens

### Add Vet with ManyToMany Relation to Specialty

![Figure Add Vet with ManyToMany Relation to Specialty](etc/images/screenAddVet.png)

### New Visit

![Figure New Visit](etc/images/screenNewVisit.png])

### Owner

![Figure Owner](etc/images/screenOwner.png)

## Setup Runtimes:

### TODO: install JBoss Wildfly
install JBoss Wildfly 8.2.1.Final from http://wildfly.org/downloads/ to e.g. /Users/tw/srv/wildfly-8.2.1.Final/
start JBoss by: cd /Users/tw/srv/wildfly-8.2.1.Final/bin ; ./standalone.sh

### TODO: install Glassfish 5.1
I installed Netbeans 8.0.2 with Glassfish 4.1 from https://netbeans.org/downloads/

### TODO: install OpenLiberty
lorem ipsum

## TODO: starting on Mac OSX:

    cd /Applications/NetBeans/glassfish-4.1/bin/
    sudo ./asadmin start-database
    sudo ./asadmin start-domain

For Starting the JavaDB Database with jdk1.7.0_51 refer to: http://thomas-woehlke.blogspot.de/2014/01/start-glassfish4-javadb-running-jdk17051.html

## TODO: Functional Tests with Selenium2 Webdriver, Arquillian Drone and Graphene ##
Samples are tested on Wildfly and GlassFish using the Arquillian ecosystem.
Only one profile can be active at a given time otherwise there will be dependency conflicts.

* ``mvn clean install -Pwildfly-managed``
    This profile  will install a Wildfly server and start up the server.
    Useful for CI servers.

* ``mvn clean install -Pwildfly-remote``
    This profile requires you to start up a Wildfly server outside of the build.
    Useful for development to avoid the server start up cost per sample.

* ``mvn clean install -Pglassfish-remote``
    This profile requires you to start up a GlassFish 4 server outside of the build. Each sample will then
    reuse this instance to run the tests.
    Useful for development to avoid the server start up cost per test.


* ``mvn clean install -Pglassfish-managed``
    This profile  will install a Glassfish 4 server and start up the server.
    Useful for development, but has the downside of server startup per Test.
    You have to start a JavaDB (Derby) Server outside of the build before running the Test.

When developing and runing them from IDE, remember to activate the profile before running the test.
To learn more about Arquillian please refer to the [Arquillian Guides](http://arquillian.org/guides/)

## TODO: build and run ##
git clone https://github.com/thomaswoehlke/javaee8-petclinic.git
build project with: ``mvn clean install wildfly:deploy``
open url in browser: http://localhost:8080/petclinic/

## TODO: openshift ##
The OpenShift `jbossas` cartridge documentation can be found at:
https://github.com/openshift/origin-server/tree/master/cartridges/openshift-origin-cartridge-jbossas/README.md

## First Steps to use ##

* add some PetTypes like dog,cat,mouse,...
* add some Specialties for Vetinarians like dentist, anesthetist, radiology,...
* add a Vetinarian
* add an Owner, add him am a Pet and his Pet a visit.

## visit Spring Petclinic ##
* https://github.com/spring-projects/spring-petclinic

## DEV: mvn Profiles ##
*
 


