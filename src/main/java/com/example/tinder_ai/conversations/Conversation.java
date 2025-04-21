package com.example.tinder_ai.conversations;

import java.util.List;

public record Conversation(
        String id,
        String profileId,
        List<ChatMessage> chatMessages
) {
}
