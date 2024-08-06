package com.keyin.finalsprint.models;

import com.keyin.finalsprint.utils.SecurityUtils;
import com.keyin.finalsprint.utils.Updateable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Updateable<User.Update> {

    private static final long SESSION_TIME = 60 * 60 * 24; // 1 day session time

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private boolean admin;

    // Password
    @Getter(AccessLevel.NONE) private String hashedPassword;
    @Getter(AccessLevel.NONE) private String encodedSalt;

    // Session Info
    @Getter(AccessLevel.NONE) private Date sessionTime;
    @Getter(AccessLevel.NONE) private String session;

    private User(long id, String email, String firstName, String lastName, boolean admin) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
    }

    public User(String email, String firstName, String lastName, boolean admin, String password) {
        this(0, email, firstName, lastName, admin);
        if (!this.setPassword(password)) {
            throw new RuntimeException("Failed to hash password for '" + email + "'");
        }
    }

    @Override
    public boolean update(Update data) {
        if (data.email != null) this.email = data.email;
        if (data.firstName != null) this.firstName = data.firstName;
        if (data.lastName != null) this.lastName = data.lastName;
        if (data.admin != null) this.admin = data.admin;
        return data.password == null || setPassword(data.password);
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

    public long getId() {
        return this.id;
    }


    public record Update(
            String email,
            String firstName,
            String lastName,
            Boolean admin,
            String password
    ) implements Updateable.UpdateData {

        @Override
        public boolean isComplete() {
            return email != null && firstName != null && lastName != null && admin != null && password != null;
        }
    }
}
