package br.com.projeto.api.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
