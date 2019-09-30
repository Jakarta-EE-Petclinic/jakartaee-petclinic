#!/usr/bin/env bash


echo "STARTED"
echo $CLASSPATH
echo $JAVA_HOME
echo $HOME
export CLASSPATH=
echo "START MAVEN 1"
../mvnw --batch-mode --log-file=etc/mavenlog1.txt clean install -DskipTests=true -Dmaven.javadoc.skip=true
echo "START MAVEN 2"
../mvnw --batch-mode --log-file=etc/mavenlog2.txt dependency:tree dependency:unpack-dependencies -DskipTests=true -Dmaven.javadoc.skip=true
echo "START MAVEN 3"
../mvnw --batch-mode --log-file=etc/mavenlog3.txt dependency:build-classpath -Dmdep.outputFile=etc/classpath.txt
echo "FINISHED MAVEN"
export MY_CLASSPATH_DEPS=`cat etc/classpath.txt`
export HERE=`pwd`
export MY_CLASSPATH_APP=$HERE/target/classes/
export CLASSPATH=".;$MY_CLASSPATH_APP"
echo $CLASSPATH
cd target/classes
echo "START serialver"
export TARGET_FILE=../../etc/serialversions.txt
serialver org.woehlke.jakartaee.petclinic.frontend.web.owner.OwnerViewFlowState > $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.owner.HasOwnerModelOperations >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.owner.HasOwnersPetModelOperations >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.owner.OwnersPetVisitView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.owner.OwnersPetView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.owner.HasOwnerViewFlowState >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.NavigationView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.LanguageView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.impl.CrudViewFlow >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.impl.NavigationViewImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.impl.FrontendMessagesViewImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.impl.LanguageViewImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.common.HasTreeNode >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.common.HasLanguage >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.common.HasViewModelOperations >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.common.HasCrudFlowState >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.common.HasSearch >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.common.CrudViewFlowState >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.web.common.CrudView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.api.rest.VisitWebservice >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.api.rest.PetWebservice >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.api.rest.JaxRsActivator >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.api.rest.VetWebservice >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.api.rest.SpecialtyWebservice >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.api.rest.OwnerWebservice >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.frontend.api.rest.PetTypeWebservice >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.SpecialtyService >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.VetService >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.PetTypeService >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.SearchIndexService >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.PetService >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.impl.SearchIndexServiceImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.impl.OwnerServiceImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.impl.PetServiceImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.impl.VisitServiceImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.impl.VetServiceImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.impl.SpecialtyServiceImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.impl.PetTypeServiceImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.VisitService >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.OwnerService >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.common.CrudService >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.services.common.SearchableService >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.SpecialtyDao >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.VetDao >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.VisitDao >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.PetDao >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.impl.OwnerDaoImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.impl.VisitDaoImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.impl.SpecialtyDaoImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.impl.PetDaoImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.impl.VetDaoImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.impl.PetTypeDaoImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.OwnerDao >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.common.CrudDao >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.common.Searchable >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.dao.PetTypeDao >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.SpecialtyView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.OwnerView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.impl.PetTypeViewImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.impl.SpecialtyViewImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.impl.VetViewImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.impl.OwnerViewImpl >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.PetTypeView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.flow.SpecialtyViewFlow >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.flow.PetTypeViewFlow >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.flow.OwnerViewFlow >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.flow.VetViewFlow >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.VetView >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.converter.SpecialtyConverter >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.view.converter.PetTypeConverter >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.Specialty >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.Pet >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.listener.PetListener >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.listener.SpecialtyListener >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.listener.ListenerLogger >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.listener.PetTypeListener >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.listener.VetListener >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.listener.VisitListener >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.listener.OwnerListener >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.Owner >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.Vet >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.PetType >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.Visit >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.model.VisitList >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.model.PetTypeList >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.model.SpecialtyList >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.model.OwnerList >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.model.VetList >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.oodm.entities.model.PetList >> $TARGET_FILE
serialver org.woehlke.jakartaee.petclinic.application.SchedulerBean >> $TARGET_FILE
cat $TARGET_FILE
echo "FINISHED"
#exit 0