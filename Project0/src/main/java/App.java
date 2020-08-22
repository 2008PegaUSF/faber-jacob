import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

//import org.apache.

public class App {
	public static void main(String[] args) {
		Logger log = LogManager.getLogger(App.class);
		Configurator.initialize(null, "log4j2.xml");
		User currentUser;
		
		
	}
}
