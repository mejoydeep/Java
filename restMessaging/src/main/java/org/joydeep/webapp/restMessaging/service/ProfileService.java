package org.joydeep.webapp.restMessaging.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joydeep.webapp.restMessaging.database.DatabaseClass;
import org.joydeep.webapp.restMessaging.model.Profile;

public class ProfileService {
  
	
	private Map<String, Profile> profiles= DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("Joydeep", new Profile(1L, "Joydeep", "Chak"));
	}
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values()); 
	}
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size()+1);
		profiles.put(profile.getFirstName(), profile);
		return profile;
	}
	public Profile updateProfiele(Profile profile) {
		if(profile.getFirstName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getFirstName(), profile);
		return profile;
	}
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
	
}
