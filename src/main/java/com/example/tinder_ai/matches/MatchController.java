package com.example.tinder_ai.matches;

import com.example.tinder_ai.conversations.Conversation;
import com.example.tinder_ai.conversations.ConversationRepository;
import com.example.tinder_ai.profiles.Profile;
import com.example.tinder_ai.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
public class MatchController {
    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;
    private final MatchRepository matchRepository;

    public MatchController(ConversationRepository conversationRepository, ProfileRepository profileRepository, MatchRepository matchRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
        this.matchRepository = matchRepository;
    }

    @PostMapping("/matches")
    public Match createNewMatch(@RequestBody CreateMatchRecord request){
        Profile profile = profileRepository.findById(request.profileId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Profile not found!"));
        // TODO: make sure there are no existing conversations with this profile
        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId(),
                new ArrayList<>()
        );
        conversationRepository.save(conversation);
        Match match = new Match(UUID.randomUUID().toString(),profile,conversation.id());
        matchRepository.save(match);
        return match;
    }
    @GetMapping("/matches")
    public Set<Match> getAllMatches(){
        return new HashSet<>(matchRepository.findAll());
    }
    public record CreateMatchRecord(String profileId){}

}
