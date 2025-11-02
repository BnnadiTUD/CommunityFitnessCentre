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
	  
	  //there is an option to add a plan that has already been created to a an existing user
	  @PUT
		@Path("/members/{memberId}/plans/{planId}")
		public Plan assignPlanToMember(
		        @PathParam("memberId") long memberId,
		        @PathParam("planId") long planId) {
		    return pDAO.assignExistingPlanToMember(memberId, planId);
		}
	  
	  //creating a payment and assigning it to a member
	  @POST
	  @Path("/members/{memberId}/payments")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Payment assignNewPlanToMember(
	          @PathParam("memberId") long memberId,
	          Payment p
	  ) {
	      return paymentDAO.addNewPaymentToMember(memberId, p);
	  }
	  
		/*Not actually needed, not realistic in the real world.
		 * @PUT
		 * Add an already existing payment to a member
		 * @Path("/members/{memberId}/payments/{paymentId}") public Payment
		 * assignPaymentsToMember(
		 * 
		 * @PathParam("memberId") long memberId,
		 * 
		 * @PathParam("paymentId") long paymentId) { return
		 * paymentDAO.assignExistingPaymentToMmber(memberId, paymentId); }
		 */
}
