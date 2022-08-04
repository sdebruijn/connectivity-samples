package com.example.android.bluetoothchat;

import java.util.Objects;

public class ChatMessage {
    private String content;
    private boolean isMine;

    public ChatMessage(String content) {
        this(content, false);
    }
    public ChatMessage(String content, boolean isMine) {
        Objects.requireNonNull(content);
        this.content = content;
        this.isMine = isMine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage message = (ChatMessage) o;
        return content.equals(message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    public String toString() {
        return content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
