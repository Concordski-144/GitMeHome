package data_access;

import entity.User;
import use_case.clear_users.ClearUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, ClearUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param user the data to save
     */
    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public String[] clear() {
        String[] clearedUsernames = users.keySet().toArray(new String[0]);
        users.clear();
        return clearedUsernames;
    }
}
