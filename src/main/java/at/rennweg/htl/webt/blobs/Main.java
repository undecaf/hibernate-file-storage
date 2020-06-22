package at.rennweg.htl.webt.blobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Starts the server for static content and for the REST API.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
