package com.app.community_centre_ca;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.app.community_centre_ca.dao.*;
import com.app.community_centre_ca.model.*;

public class PlanResource {
	private PlanDAO pDAO = new PlanDAO();

//add a plan --- add plan and assign it to member is in member resource	
    @POST
    @Path("/persistMembers")
    public String saveMember(Plan p) {
        pDAO.persist(p);
        return "Plan saved...";
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
