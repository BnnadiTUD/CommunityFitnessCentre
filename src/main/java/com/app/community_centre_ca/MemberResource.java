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
import com.app.community_centre_ca.dao.MemberDAO;
import com.app.community_centre_ca.model.Member;

@Path("/memberService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberResource {
	
	private MemberDAO mDAO = new MemberDAO();
	
	@PUT
	@Path("/centres/{centreId}/members/{memberId}")
	public Member assignMemberToCentre(
	        @PathParam("centreId") long centreId,
	        @PathParam("memberId") long memberId) {
	    return mDAO.assignExistingMemberToCentre(centreId, memberId);
	}
	  
        //Create member WITHOUT centre
	    @POST
	    @Path("/persistMembers")
	    public String saveMember(Member member) {
	        mDAO.persist(member);
	        return "Member saved...";
	    }
	  
	  @GET
	    @Path("/getAllMembers")
		public List<Member> getMembers() {
			return mDAO.getAllMembers();
		}
}
