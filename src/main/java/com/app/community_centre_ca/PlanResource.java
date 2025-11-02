package com.app.community_centre_ca;

import java.util.List;

import javax.persistence.EntityManager;
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

@Path("/planService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanResource {
	private PlanDAO pDAO = new PlanDAO();

//add a plan --- add plan and assign it to member is in member resource	
    @POST
    @Path("/persistPlans")
    public String saveMember(Plan p) {
        pDAO.persist(p);
        return "Plan saved...";
    }
    
	  //there is an option to add a plan that has already been created to a an existing user
	  @PUT
		@Path("/members/{memberId}/plans/{planId}")
		public Plan assignExistingPlanToMember(
		        @PathParam("memberId") long memberId,
		        @PathParam("planId") long planId) {
		    return pDAO.assignExistingPlanToMember(memberId, planId);
		}
	  
	  @POST
	  @Path("/members/{memberId}/plans")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Plan assignNewPlanToMember(
	          @PathParam("memberId") long memberId,
Plan p
) {
	      return pDAO.addNewPlanToMember(memberId, p);
	  }
	  
	  @GET
	    @Path("/getAllPlans")
		public List<Plan> getAllPlans() {
			return pDAO.getAllPlans();
		}
	  
	    @DELETE
	    @Path("/plan/{id}")
	    public String deletePlan(@PathParam("id") long id) {
	        pDAO.removePlan(id);
	        return "Plan deleted ....";
	    }
	  

}
