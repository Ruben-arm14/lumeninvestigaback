package org.lumeninvestiga.backend.repositorio.tpi;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.ArticleDetail;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.*;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RepositorioTpiApplication {
	private static final int MIN_PDF_SIZE = 1024; // 1KB
	private static final int MAX_PDF_SIZE = 10 * 1024 * 1024; // 10MB

	public static void main(String[] args) {
		SpringApplication.run(RepositorioTpiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, ArticleRepository articleRepository,
											   FolderRepository folderRepository, ReviewRepository reviewRepository,
											   PasswordEncoder passwordEncoder) {
		return args -> {
			createUserIfNotExists(userRepository, passwordEncoder, "student1", "password1", Role.STUDENT);
			createUserIfNotExists(userRepository, passwordEncoder, "student2", "password2", Role.STUDENT);
			createUserIfNotExists(userRepository, passwordEncoder, "teacher1", "password1", Role.TEACHER);
			createUserIfNotExists(userRepository, passwordEncoder, "teacher2", "password2", Role.TEACHER);
			createUserIfNotExists(userRepository, passwordEncoder, "teacher3", "password3", Role.TEACHER);

			// Crear artículos con comentarios
			ArticleDetail articleDetail = new ArticleDetail();
			articleDetail.setTitle("MachineLearning");
			articleDetail.setAuthor("Juan pedro");
			articleDetail.setAdvisor("Roberto Rojas");
			articleDetail.setPeriod("2018-2");
			articleDetail.setOds("1");
			articleDetail.setKeywords("IA, MachineLearning, IOT");
			articleDetail.setArea("IA");
			articleDetail.setSubArea("Supervisado");
			articleDetail.setResume("Este trabajo trata sobre la investigacion de modelos de machine learning...");
			articleDetail.setCurso("Inteligencia Artificial");
			articleDetail.setProfesor("Dr. AI");

			Article article = new Article();
			article.setUser(userRepository.findByUsername("student1").orElseThrow());
			article.setArticleDetail(articleDetail);
			articleRepository.save(article);

			Review review = new Review();
			review.setComment("Muy buen trabajo, saludos");
			review.setUser(userRepository.findByUsername("student1").orElseThrow());
			review.setArticle(article);
			reviewRepository.save(review);

			Review review1 = new Review();
			review1.setComment("Muy buen trabajo me encantó");
			review1.setUser(userRepository.findByUsername("student2").orElseThrow());
			review1.setArticle(article);
			reviewRepository.save(review1);

			Review review2 = new Review();
			review2.setComment("Ya he visto este trabajo en otro lado, lo reportaré");
			review2.setUser(userRepository.findByUsername("teacher1").orElseThrow());
			review2.setArticle(article);
			reviewRepository.save(review2);

			for (Article item : getData()) {
				item.setUser(userRepository.findByUsername("student1").orElseThrow());
				articleRepository.save(item);
				System.out.println("Articulo: " + item.getArticleDetail().getTitle() + " guardado.");
			}

			System.out.println("Usuarios y artículos creados con éxito.");
		};
	}

	private void createUserIfNotExists(UserRepository userRepository, PasswordEncoder passwordEncoder, String username, String password, Role role) {
		if (userRepository.findByUsername(username).isEmpty()) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(password));
			user.setRole(role);

			UserDetail userDetail = new UserDetail();
			userDetail.setName("Nombre " + username);
			userDetail.setLastName("Apellido " + username);
			userDetail.setEmailAddress(username + "@example.com");
			userDetail.setUser(user);
			user.setUserDetail(userDetail);

			if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
				userRepository.save(user);
			}
		}
	}

	public static byte[] generateRandomPdfContent() {
		SecureRandom random = new SecureRandom();
		int pdfSize = random.nextInt(MAX_PDF_SIZE - MIN_PDF_SIZE + 1) + MIN_PDF_SIZE;
		byte[] pdfContent = new byte[pdfSize];
		random.nextBytes(pdfContent);
		return pdfContent;
	}

	public static List<Article> getData() {
		List<Article> articles = new ArrayList<>();

		// Artículo 1
		ArticleDetail detail1 = new ArticleDetail();
		detail1.setArea("Ciencias de la Computación");
		detail1.setSubArea("Inteligencia Artificial");
		detail1.setPeriod("2018-2");
		detail1.setOds("9");
		detail1.setTitle("Una Introducción al Aprendizaje Automático");
		detail1.setAuthor("Juan Pérez");
		detail1.setAdvisor("Dr. García");
		detail1.setResume("Este artículo introduce los conceptos básicos del aprendizaje automático...");
		detail1.setKeywords("Aprendizaje Automático, IA, Algoritmos");
		detail1.setCurso("Curso IA");
		detail1.setProfesor("Dr. Computación");

		Article article1 = new Article();
		article1.setArticleDetail(detail1);
		articles.add(article1);

		// Artículo 2
		ArticleDetail detail2 = new ArticleDetail();
		detail2.setArea("Ciencias Ambientales");
		detail2.setSubArea("Cambio Climático");
		detail2.setPeriod("2019-0");
		detail2.setOds("13");
		detail2.setTitle("El Impacto del Cambio Climático en las Zonas Costeras");
		detail2.setAuthor("Ana López");
		detail2.setAdvisor("Dr. Verde");
		detail2.setResume("Este artículo examina los efectos del cambio climático en las regiones costeras...");
		detail2.setKeywords("Cambio Climático, Zonas Costeras, Ciencias Ambientales");
		detail2.setCurso("Cambio Climático");
		detail2.setProfesor("Dr. Ambiental");

		Article article2 = new Article();
		article2.setArticleDetail(detail2);
		articles.add(article2);

		// Artículo 3
		ArticleDetail detail3 = new ArticleDetail();
		detail3.setArea("Ciencias Médicas");
		detail3.setSubArea("Genética");
		detail3.setPeriod("2019-0");
		detail3.setOds("3");
		detail3.setTitle("Avances en Ingeniería Genética");
		detail3.setAuthor("Laura Gómez");
		detail3.setAdvisor("Dr. Blanco");
		detail3.setResume("Este artículo explora los avances recientes en el campo de la ingeniería genética...");
		detail3.setKeywords("Ingeniería Genética, Ciencias Médicas, CRISPR");
		detail3.setCurso("Genética Avanzada");
		detail3.setProfesor("Dr. Medicina");

		Article article3 = new Article();
		article3.setArticleDetail(detail3);
		articles.add(article3);

		return articles;
	}
}
