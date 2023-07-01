package com.letsfunky.humbleobject.before;

interface MessageBus {
    void sendEmailChangedMessage(int userId, String newEmail);
}
