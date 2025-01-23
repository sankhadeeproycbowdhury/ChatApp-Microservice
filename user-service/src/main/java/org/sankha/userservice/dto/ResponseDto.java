package org.sankha.userservice.dto;

public class ResponseDto {
    private Integer id;
    private String username;
    private String email;
    private boolean online;

    public ResponseDto() {}

    public ResponseDto(Integer id, String username, String email, boolean online) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.online = online;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
