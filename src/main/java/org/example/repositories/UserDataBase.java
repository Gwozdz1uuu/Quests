package org.example.repositories;

import org.example.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataBase {
    private List<User> users = new ArrayList<User>();


    public UserDataBase() {
        User user1 = new User(0, "Maciek223");
        User user2 = new User(1, "GramBoLubie");
        User user3 = new User(2, "BedwarsDestroyer");

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
    public User getUser(int id) {
        return  users.get(id);
    }
    public List<User> getUsers() {
        return users;
    }
}
