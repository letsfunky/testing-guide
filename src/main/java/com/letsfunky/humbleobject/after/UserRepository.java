package com.letsfunky.humbleobject.after;

interface UserRepository {
    User findById(int userId);
    void save(User user);
}
