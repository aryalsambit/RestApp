package uncc.sambit.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RESTAppConfig extends ResourceConfig{
	public RESTAppConfig(){
		packages("uncc.sambit.rest");
	}
}
