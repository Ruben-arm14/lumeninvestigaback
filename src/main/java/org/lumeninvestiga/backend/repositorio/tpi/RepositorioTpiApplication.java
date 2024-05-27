package org.lumeninvestiga.backend.repositorio.tpi;

import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.UserDetail;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.ReviewRepository;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RepositorioTpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepositorioTpiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, ReviewRepository reviewRepository) {
		User user = new User();

		UserDetail userD = new UserDetail();
		userD.setName("Pedro");
		userD.setLastName("Sanchez");
		userD.setEmailAddress("1234@example.com");

		userD.setUser(user);
		user.setUserDetail(userD);

		Review review = new Review();
		review.setLiked(true);
		review.setComment("Muy buen trabajo, saludos");
		review.setUser(user);
		user.addReview(review);


		return args -> {
			userRepository.save(user);
			System.out.println("Usuario guardado " + user.getUserDetail().getName());
		};
	}

}
