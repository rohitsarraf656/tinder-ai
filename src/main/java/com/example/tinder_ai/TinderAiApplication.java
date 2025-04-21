package com.example.tinder_ai;

import com.example.tinder_ai.conversations.ConversationRepository;
import com.example.tinder_ai.profiles.ProfileCreationService;
import com.example.tinder_ai.profiles.ProfileRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderAiApplication implements CommandLineRunner {

	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	ConversationRepository conversationRepository;
	private final ChatClient ollamaClient;
	@Autowired
	private ProfileCreationService profileCreationService;
	public TinderAiApplication(ChatClient.Builder chatClientBuilder){
		this.ollamaClient = chatClientBuilder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(TinderAiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		conversationRepository.deleteAll();
		profileRepository.deleteAll();
		profileCreationService.saveProfilesToDB();
//		Profile profile = new Profile(
//				"1",
//				"Rohit",
//				"Sarraf",
//				32,
//				"Indian",
//				Gender.MALE,
//				"Passionate Coder",
//				"foo.jpg",
//				"INTP"
//
//		);
//		Profile profile1 = new Profile(
//				"2",
//				"Sweta",
//				"Sarraf",
//				30,
//				"Indian",
//				Gender.FEMALE,
//				"Passionate Dancer",
//				"foo.jpg",
//				"INTP"
//
//		);

		// System.out.println(this.ollamaClient.prompt("Hi").call().content());
//		profileRepository.save(profile);
//		profileRepository.save(profile1);
//		profileRepository.findAll().forEach(System.out::println);
//		Conversation c1 = new Conversation(
//				"1",
//				profile.id(),
//				List.of(
//						new ChatMessage("Hello", profile.id(), LocalDateTime.now())
//				)
//		);
//		conversationRepository.save(c1);
//		conversationRepository.findAll().forEach(System.out::println);
	}
}
