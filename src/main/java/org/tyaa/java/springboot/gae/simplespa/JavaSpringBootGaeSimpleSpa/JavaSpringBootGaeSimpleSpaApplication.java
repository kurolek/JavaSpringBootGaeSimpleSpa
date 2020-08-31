package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JavaSpringBootGaeSimpleSpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringBootGaeSimpleSpaApplication.class, args);
	}

}
