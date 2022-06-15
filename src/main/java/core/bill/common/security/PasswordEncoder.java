package core.bill.common.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
public class PasswordEncoder {
	
	private static int workload = 13;
	
	public static String BCryptEncoder(String password)
	{
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password, salt);
		return(hashed_password);
	}
	
	public static boolean checkPassword(String password, String hashed) {
		boolean verified = false;

		if(null == hashed || password==null)
			return(verified);

		verified = BCrypt.checkpw(password, hashed);
		return(verified);
	}

}
