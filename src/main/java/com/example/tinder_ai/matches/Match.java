package com.example.tinder_ai.matches;

import com.example.tinder_ai.profiles.Profile;

public record Match(String id, Profile profile, String conversationId) {
}
