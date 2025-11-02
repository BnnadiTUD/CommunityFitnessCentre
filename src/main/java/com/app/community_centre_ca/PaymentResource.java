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

@Path("/paymentService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {
	
	private PaymentDAO pDAO = new PaymentDAO();
		  
	/*
	 *Not needed because a payment will be created and assigned to a member at the same time 
	 * @POST
	 * 
	 * @Path("/persistPayments") public String saveMember(Member member) {
	 * mDAO.persist(member); return "Member saved..."; }
	 */
	
	  //creating a payment and assigning it to a member
	  @POST
	  @Path("/members/{memberId}/payments")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Payment assignNewPlanToMember(
	          @PathParam("memberId") long memberId,
	          Payment p
	  ) {
	      return pDAO.addNewPaymentToMember(memberId, p);
	  }
	  
	    //get all paymnts
	  @GET
	    @Path("/getAllPayments")
		public List<Payment> getAllPayments() {
			return pDAO.getAllPayments();
		}
	  
	    @DELETE
	    @Path("/payment/{id}")
	    public String deletePayment(@PathParam("id") long id) {
	        pDAO.removePayment(id);
	        return "Payment deleted ....";
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
