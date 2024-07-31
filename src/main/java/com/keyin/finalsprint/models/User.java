package com.keyin.finalsprint.models;

import com.keyin.finalsprint.utils.SecurityUtils;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Entity
public class User {

    private static final long SESSION_TIME = 60 * 60 * 24; // 1 day session time

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "user_sequence")
    private long id;

    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private boolean admin;

    // Password
    private String hashedPassword;
    private String encodedSalt;

    // Session Info
    private Date sessionTime;
    private String session;

    public User() {}

    private User(long id, String email, String firstName, String lastName, boolean admin) {
        super();
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
        this.hashedPassword = null;
        this.encodedSalt = null;
    }

    public User(String email, String firstName, String lastName, boolean admin, String password) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
        if (!this.setPassword(password)) {
            throw new RuntimeException("Failed to hash password for '" + email + "'");
        }
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean setPassword(String password) {
        var salt = SecurityUtils.createSalt(32);
        var hashedPassword = SecurityUtils.hash(password, salt, 128);
        if (hashedPassword == null) return false;
        this.hashedPassword = hashedPassword;
        this.encodedSalt = Base64.getEncoder().encodeToString(salt);
        return true;
    }

    public boolean isPassword(String password) {
        if (this.encodedSalt == null || this.hashedPassword == null) return false;
        if (this.encodedSalt.isEmpty() || this.hashedPassword.isEmpty()) return false;
        var salt = Base64.getDecoder().decode(this.encodedSalt);
        var hashedPassword = SecurityUtils.hash(password, salt, 128);
        return hashedPassword != null && hashedPassword.equals(this.hashedPassword);
    }

    public String createSession() {
        this.sessionTime = new Date();
        this.session = Base64.getEncoder().encodeToString(SecurityUtils.createSalt(128));
        return this.session;
    }

    public boolean isSession(String session) {
        if (this.sessionTime == null || this.session == null || this.session.isBlank()) return false;
        if (Instant.now().getEpochSecond() - this.sessionTime.toInstant().getEpochSecond() > SESSION_TIME) return false;
        return this.session.equals(session);
    }

    // Utility Methods

    public User toBasic() {
        return new User(this.id, this.email, this.firstName, this.lastName, this.admin);
    }

    public record Updated(
            String email,
            String firstName,
            String lastName,
            Boolean admin,
            String password
    ) {

        public static Updated password(String password) {
            return new Updated(null, null, null, null, password);
        }

        public boolean isFull() {
            return email != null && firstName != null && lastName != null && admin != null && password != null;
        }

        public User asUser() {
            return new User(email, firstName, lastName, admin, password);
        }
    }
}
