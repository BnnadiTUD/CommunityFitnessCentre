package com.app.community_centre_ca;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.app.community_centre_ca.dao.*;
import com.app.community_centre_ca.model.CommunityCentre;
import com.app.community_centre_ca.model.Member;
import com.app.community_centre_ca.model.Payment;

@Path("/communityCentreService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommunityCentreResource {
	
    private CommunityCentreDAO ccDAO = new CommunityCentreDAO();

	//create a community centre (only really need one)
    @POST
    @Path("/persistCommunityCentre")
    @Produces(MediaType.TEXT_PLAIN)
    public String saveCommunityCentre(CommunityCentre centre) {
        ccDAO.persist(centre);
        return "Community centre saved...";
    }
    
    //list all centres
	@GET
    @Path("/getAllCommunityCentres")
	public List<CommunityCentre> getCommunityCentres() {
		return ccDAO.getAllCommunityCentres();
	}
	
	    @DELETE
    @Path("/centre/{id}")
    public String removeCommunityCentre(@PathParam("id") long id) {
        ccDAO.removeCommunityCentre(id);
        return "Member deleted ....";
    }

}
