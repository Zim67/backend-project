package com.keyin.finalsprint;

import com.keyin.finalsprint.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTests {

    @Test
    public void testUserPassword() {
        String password = "ThisIsAPassword!12345";
        User user = new User("email@email.com", "firstname", "lastname", false, password);
        Assertions.assertTrue(user.isPassword(password));
    }

    @Test
    public void testUserUpdating() {
        User user = new User("email", "first", "last", false, "password");

        User.Update update = new User.Update(
                "newemail",
                "newfirst",
                "newlast",
                true,
                "newpassword"
        );

        user.update(update);

        Assertions.assertEquals(user.getEmail(), "newemail");
        Assertions.assertEquals(user.getFirstName(), "newfirst");
        Assertions.assertEquals(user.getLastName(), "newlast");
        Assertions.assertTrue(user.isAdmin());
        Assertions.assertTrue(user.isPassword("newpassword"));

        User.Update nullUpdate = new User.Update(null, null, null, null, null);

        user.update(nullUpdate);

        Assertions.assertEquals(user.getEmail(), "newemail");
        Assertions.assertEquals(user.getFirstName(), "newfirst");
        Assertions.assertEquals(user.getLastName(), "newlast");
        Assertions.assertTrue(user.isAdmin());
        Assertions.assertTrue(user.isPassword("newpassword"));
    }
}
