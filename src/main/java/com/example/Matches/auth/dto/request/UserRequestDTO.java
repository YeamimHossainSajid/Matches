package com.example.Matches.auth.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class UserRequestDTO {
        private String username;
        private String email;
        private String password;// Include MultipartFile

        // Getters and Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

}
