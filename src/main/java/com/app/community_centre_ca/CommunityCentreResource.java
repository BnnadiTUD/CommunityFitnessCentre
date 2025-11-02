package com.app.community_centre_ca;

import java.util.List;

import javax.ws.rs.Consumes;
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
	private MemberDAO mDAO = new MemberDAO();

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
	
	//assign an existing member to a community centre
	@PUT
	@Path("/centres/{centreId}/members/{memberId}")
	public Member assignExistingMemberToCentre(
	        @PathParam("centreId") long centreId,
	        @PathParam("memberId") long memberId) {
	    return mDAO.assignExistingMemberToCentre(centreId, memberId);
	}
	
	//assign a new member to a communitcentre
	  @POST
	  @Path("/centres/{centreId}/members")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Member assignNewMemberToCentre(
	          @PathParam("centreId") long centreId,
	          Member m
	  ) {
	      return mDAO.addNewMemberToCentre(centreId, m);
	  }

}
