package ie.secure.eai;

import java.util.HashMap;
import java.util.Map;

public class EAIHeaders {
	
	
	private static final Map<EAIHeader, String> map = new HashMap<EAIHeader, String>();
	
	static{		
		map.put(EAIHeader.EAI_USER_ID_HEADER, EAIHeaderConstants.EAI_USER_ID_HEADER_NAME);
	}
	
	public static String headerName(EAIHeader eaiHeader){
		return map.get(eaiHeader);
	}

}
