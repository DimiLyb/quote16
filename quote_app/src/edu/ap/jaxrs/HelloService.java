package edu.ap.jaxrs;

import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;

import redis.clients.jedis.Jedis;


// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class HelloService extends ResourceConfig {

  // This method is called if HTML is requested
  @GET
  @Path("/quote")
  @Produces(MediaType.TEXT_HTML)
  public String sayXMLHello() {
	  
	  Jedis jedis = new Jedis("localhost");
      System.out.println("Connection to server sucessfully");
      //check whether server is running or not
      System.out.println("Server is running: "+jedis.ping());
      //String test = jedis.get("auters");
      String name = ""; 
      
      for (String item : jedis.smembers("auters")) {
    	  name += item;
    	  for (String item2 : jedis.smembers(item)) {
      	    name += item2;  
        }   
      }
    return  name;
  }
  
  
  
  @POST
  @Consumes("text/plain")
  public String postClichedMessage(String message) {
	  Jedis jedis = new Jedis("localhost");
	  String name = "";
	  for (String item2 : jedis.smembers(message)) {
    	    name += item2; 
    	    
      }   
	  return  name;
  }
  
  
}
