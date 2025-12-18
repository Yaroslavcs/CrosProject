package org.acme.user;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepository {

    private final Map<String, User> users = Collections.synchronizedMap(new LinkedHashMap<>());

    public UserRepository() {
        // Add some fake users
        users.put("user1", new User("user1", "john_doe", "john.doe@example.com", "123 Main St"));
        users.put("user2", new User("user2", "jane_smith", "jane.smith@example.com", "456 Oak Ave"));
    }

    public User getUserById(String id) {
        return users.get(id);
    }

    public User getUserByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void updateUser(User updatedUser) {
        users.put(updatedUser.getId(), updatedUser);
    }

    public void deleteUser(String id) {
        users.remove(id);
    }
}
