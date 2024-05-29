package org.lumeninvestiga.backend.repositorio.tpi;

import org.lumeninvestiga.backend.repositorio.tpi.controllers.UserController;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Folder;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.MIME_TYPE;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Role;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.UserDetail;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.SecureRandom;

@SpringBootApplication
public class RepositorioTpiApplication {
	private static final int MIN_PDF_SIZE = 1024; // 1KB
	private static final int MAX_PDF_SIZE = 10 * 1024 * 1024; // 10MB

	public static void main(String[] args) {
		SpringApplication.run(RepositorioTpiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository,
										FileRepository fileRepository, FolderRepository folderRepository) {
		Role roleAdmin = new Role("ROLE_ADMIN");
		Role roleUser = new Role("ROLE_USER");

		User user = new User();
		User user1 = new User();

		File file = new File();
		file.setName("Diagrama de clases - actualización.drawio");
		file.setSize(654700L);
		file.setMimeType(MIME_TYPE.PDF.getMimeType());
		file.setData(generateRandomPdfContent());
		file.setUser(user);

		Folder folder = new Folder();
		folder.setName("Trabajos");
		folder.setShared(false);
		folder.setUser(user);
		folder.addFile(file);

		UserDetail userD = new UserDetail();
		userD.setName("Pedro");
		userD.setLastName("Sanchez");
		userD.setEmailAddress("1234@example.com");
		userD.setPassword("contraseña");

		UserDetail userD1 = new UserDetail();
		userD1.setName("Juan");
		userD1.setLastName("Rodriguez");
		userD1.setEmailAddress("12341234@example.com");
		userD1.setPassword("nosequeponer");


		userD.setUser(user);
		user.setUserDetail(userD);

		userD1.setUser(user1);
		user1.setUserDetail(userD1);

		user.addRole(roleAdmin);
		user.addRole(roleUser);

		user1.addRole(roleUser);

		Review review = new Review();
		review.setLiked(true);
		review.setComment("Muy buen trabajo, saludos");
		review.setUser(user);
		user.addReview(review);

		return args -> {
			roleRepository.save(roleAdmin);
			roleRepository.save(roleUser);
			System.out.println("Roles guardados " + roleAdmin.getName() + " y " + roleUser.getName());
			userRepository.save(user);
			System.out.println("Usuario guardado " + user.getUserDetail().getName());
			userRepository.save(user1);
			System.out.println("Usuario guardado " + user1.getUserDetail().getName());
			folderRepository.save(folder);
			fileRepository.save(file);
		};
	}

	public static byte[] generateRandomPdfContent() {
		SecureRandom random = new SecureRandom();
		int pdfSize = random.nextInt(MAX_PDF_SIZE - MIN_PDF_SIZE + 1) + MIN_PDF_SIZE;

		byte[] pdfContent = new byte[pdfSize];
		random.nextBytes(pdfContent);

		return pdfContent;
	}
}
