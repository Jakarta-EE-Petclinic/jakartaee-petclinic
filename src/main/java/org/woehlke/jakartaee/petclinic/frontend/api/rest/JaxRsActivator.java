package org.woehlke.jakartaee.petclinic.frontend.api.rest;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 05.01.14
 * Time: 09:27
 * To change this template use File | Settings | File Templates.
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application implements Serializable {

    private static final long serialVersionUID = 6101321761195838117L;
}
