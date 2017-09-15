package ifma.dcomp.mybookstore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesteCriptografia {
	
	public static void main(String[] args) {
		
		String encode = new BCryptPasswordEncoder().encode("123");
		System.out.println("######### " + encode );
		
	}	

}
