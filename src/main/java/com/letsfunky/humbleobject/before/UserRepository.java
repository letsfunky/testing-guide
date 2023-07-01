package com.letsfunky.humbleobject.before;

interface UserRepository {
    User findById(int userId);
    void save(User user);
}
