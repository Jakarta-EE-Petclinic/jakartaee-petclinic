package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.primefaces.extensions.arquillian.AbstractPrimePage;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 26.01.14
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
@Location("home.jsf")
public class HomePage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(HomePage.class.getName());

    public void assertTitle(){
        Assert.assertEquals("Jakarta EE 8 Petclinic", webDriver.getTitle());
    }
}
