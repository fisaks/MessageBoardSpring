package com.fpi.resource;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.fpi.exception.ServiceException;
import com.fpi.resource.obj.MessageBean;
/**
 * The MessageBoard Web service interface and its supported operation.
 * Supports both SOAP and REST protocols
 *  
 * @author freddi
 *
 */
@WebService
@Path("/messageboard")
public interface MessageBoard {
    @POST
    @Path("/createMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces( {MediaType.APPLICATION_JSON})
    @Descriptions({
	   @Description(value = "Creates a new message", target = DocTarget.METHOD),
	   @Description(value = "The message added including generated message id", target = DocTarget.RETURN),
	   @Description(value = "Request Documenations", target = DocTarget.REQUEST),
	   @Description(value = "Response Documenations", target = DocTarget.RESPONSE),
	   @Description(value = "Resource Documenations", target = DocTarget.RESOURCE)
	})
	public MessageBean createMessage(@Description("The MessageBean as JSON object without id") @WebParam(name="message") MessageBean message) throws ServiceException;
    
    @GET
    @Path("/listMessages")
    @Produces(MediaType.APPLICATION_JSON)
    @Descriptions({
 	   @Description(value = "List all messages", target = DocTarget.METHOD),
 	   @Description(value = "All messages as V1 format", target = DocTarget.RETURN),
 	   @Description(value = "Response Documenations", target = DocTarget.RESPONSE),
 	   @Description(value = "Resource Documenations", target = DocTarget.RESOURCE)
 	})
	public List<MessageBean> listMessages();
	
    @GET
    @Path("/listMessages/v2")
    @Produces( {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML} )
    @Descriptions({
  	   @Description(value = "List all messages", target = DocTarget.METHOD),
  	   @Description(value = "All messages as V2 format", target = DocTarget.RETURN),
  	   @Description(value = "Response Documenations", target = DocTarget.RESPONSE),
  	   @Description(value = "Resource Documenations", target = DocTarget.RESOURCE)
  	})
	public List<MessageBean> listMessagesV2();
	
    @GET
    @Path("/clear")
    @Produces( {MediaType.APPLICATION_JSON} )
    @Descriptions({
  	   @Description(value = "Clears all messages", target = DocTarget.METHOD)
  	})
    public void clear();

    @GET
    @Path("/get")
    @Produces( {MediaType.APPLICATION_JSON} )
    @Descriptions({
 	   @Description(value = "Get the message with id", target = DocTarget.METHOD),
 	   @Description(value = "The MessageBean for the id", target = DocTarget.RETURN),
 	   @Description(value = "Request Documenations", target = DocTarget.REQUEST),
 	   @Description(value = "Response Documenations", target = DocTarget.RESPONSE),
 	   @Description(value = "Resource Documenations", target = DocTarget.RESOURCE)
 	})
    public MessageBean getMessage(@Description("The id of the message to get") @WebParam(name="messageId") @QueryParam("messageId") Long messageId) throws ServiceException;
    
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces( {MediaType.APPLICATION_JSON} )
    @Descriptions({
  	   @Description(value = "Update the message part of the request and returns the count of the updated messages", target = DocTarget.METHOD),
  	   @Description(value = "Request Documenations", target = DocTarget.REQUEST),
  	   @Description(value = "Response Documenations", target = DocTarget.RESPONSE),
  	   @Description(value = "Resource Documenations", target = DocTarget.RESOURCE)
  	})
    public int update(@Description("The message to update with the updated details") @WebParam(name="message") MessageBean message) throws ServiceException;
    
    @GET
    @Path("/remove")
    @Produces( {MediaType.APPLICATION_JSON} )
    @Descriptions({
   	   @Description(value = "Removes the message with the message id", target = DocTarget.METHOD),
   	   @Description(value = "Returns the removed MessageBean", target = DocTarget.RETURN),
   	   @Description(value = "Request Documenations", target = DocTarget.REQUEST),
   	   @Description(value = "Response Documenations", target = DocTarget.RESPONSE),
   	   @Description(value = "Resource Documenations", target = DocTarget.RESOURCE)
   	})
    public MessageBean removeMessage(@Description("The id of the message to remove")  @WebParam(name="messageId") @QueryParam("messageId") Long messageId) throws ServiceException;

}
