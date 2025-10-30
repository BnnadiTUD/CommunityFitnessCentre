package com.app.community_centre_ca;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.app.community_centre_ca.dao.CommunityCentreDAO;
import com.app.community_centre_ca.model.CommunityCentre;

@Path("/communityCentreService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommunityCentreResource {
	
    private CommunityCentreDAO ccDAO = new CommunityCentreDAO();

    @POST
    @Path("/persistCommunityCentre")
    @Produces(MediaType.TEXT_PLAIN)
    public String saveCommunityCentre(CommunityCentre centre) {
        ccDAO.persist(centre);
        return "Community centre saved...";
    }
    
	@GET
    @Path("/getAllCommunityCentres")
	public List<CommunityCentre> getCommunityCentres() {
		return ccDAO.getAllCommunityCentres();
	}

}
