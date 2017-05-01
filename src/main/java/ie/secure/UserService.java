package ie.secure;

import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	public String email(Identity fromIdentity){
		return userRegistryLookup(fromIdentity);
	}

	public String email(String fromUserName){
		return userRegistryLookup(fromUserName);
	}
	
	private String userRegistryLookup(Identity identity){
		return "test@test.ie";
	}
	
	private String userRegistryLookup(String identity){
		return "test@test.ie";
	}
}
