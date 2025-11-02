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
import com.app.community_centre_ca.model.*;

@Path("/memberService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberResource {
	
	private MemberDAO mDAO = new MemberDAO();
	private PlanDAO pDAO = new PlanDAO();
	private PaymentDAO paymentDAO = new PaymentDAO();
		  
        //Create member WITHOUT centre - other meth allows me to assign an existing member to a community
	// this one lets me add a member to the member list and the member can later be assigned to a community centre
	    @POST
	    @Path("/persistMembers")
	    public String saveMember(Member member) {
	        mDAO.persist(member);
	        return "Member saved...";
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
	    
	    @DELETE
	    @Path("/members/{id}")
	    public String deleteMember(@PathParam("id") long id) {
	        mDAO.removeMember(id);
	        return "Member deleted ....";
	    }
	  
	    //get all members
	  @GET
	    @Path("/getAllMembers")
		public List<Member> getMembers() {
			return mDAO.getAllMembers();
		}
}
