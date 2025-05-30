package com.example.tinder_ai.conversations;

import com.example.tinder_ai.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class ConversationController {
    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;

    public ConversationController(ConversationRepository conversationRepository, ProfileRepository profileRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    @PostMapping("/conversations")
    public Conversation createNewConversation(@RequestBody CreateConversationRecord request){
        profileRepository.findById(request.profileId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId(),
                new ArrayList<>()
        );
        conversationRepository.save(conversation);
        return conversation;
    }
    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId,
                                                 @RequestBody ChatMessage chatMessage){
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, "No conversation found"));
        profileRepository.findById(chatMessage.authorId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT,"No profile found"));
        // TODO need to check author of a message happens to be only profile associated with the message or user
        ChatMessage chatMessagewithTime = new ChatMessage(
                chatMessage.messageText(),
                chatMessage.authorId(),
                LocalDateTime.now()
        );
        conversation.chatMessages().add(chatMessagewithTime);
        conversationRepository.save(conversation);
        return conversation;

    }
    @GetMapping("/conversations/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId){
        return  conversationRepository.findById(conversationId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, "No conversation found"));
    }

    public record CreateConversationRecord(
            String profileId
    ){}
}
