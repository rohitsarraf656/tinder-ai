package com.example.tinder_ai;

import com.example.tinder_ai.conversations.ChatMessage;
import com.example.tinder_ai.conversations.Conversation;
import com.example.tinder_ai.conversations.ConversationRepository;
import com.example.tinder_ai.profiles.Gender;
import com.example.tinder_ai.profiles.Profile;
import com.example.tinder_ai.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiApplication implements CommandLineRunner {

	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	ConversationRepository conversationRepository;
	public static void main(String[] args) {
		SpringApplication.run(TinderAiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Profile profile = new Profile(
				"1",
				"Rohit",
				"Sarraf",
				32,
				"Indian",
				Gender.MALE,
				"Passionate Coder",
				"foo.jpg",
				"INTP"

		);
		Profile profile1 = new Profile(
				"2",
				"Sweta",
				"Sarraf",
				30,
				"Indian",
				Gender.FEMALE,
				"Passionate Dancer",
				"foo.jpg",
				"INTP"

		);
		profileRepository.save(profile);
		profileRepository.save(profile1);
		profileRepository.findAll().forEach(System.out::println);
		Conversation c1 = new Conversation(
				"1",
				profile.id(),
				List.of(
						new ChatMessage("Hello", profile.id(), LocalDateTime.now())
				)
		);
		conversationRepository.save(c1);
		conversationRepository.findAll().forEach(System.out::println);
	}
}
