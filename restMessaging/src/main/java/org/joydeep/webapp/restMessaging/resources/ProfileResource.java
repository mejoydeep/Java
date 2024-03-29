package org.joydeep.webapp.restMessaging.resources;

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

import org.joydeep.webapp.restMessaging.model.Profile;
import org.joydeep.webapp.restMessaging.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService profileService=new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles(){
		return profileService.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);	
   }
	@POST
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}
	@PUT
	@Path("/{profileName}")
	public Profile upDateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setFirstName(profileName);
		profileService.updateProfiele(profile);
		return profile;
	}
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName")String profileName) {
		profileService.removeProfile(profileName);
	}
}
