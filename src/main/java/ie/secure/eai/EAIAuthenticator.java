package ie.secure.eai;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.secure.Identity;

public class EAIAuthenticator implements Authenticator {
	
	private static final String EAI_USER_ID_HEADER = "am-eai-user-id";

	@Override
	public void authenticate(HttpServletRequest req, HttpServletResponse resp) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void authenticate(Identity identity, HttpServletResponse resp) {
		resp.addHeader(EAI_USER_ID_HEADER, identity.getEmail());
	}

	@Override
	public void authenticate(HttpServletResponse resp) {
		throw new UnsupportedOperationException();
	}

}
