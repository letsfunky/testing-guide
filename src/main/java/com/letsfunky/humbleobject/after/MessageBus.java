package com.letsfunky.humbleobject.after;

interface MessageBus {
    void sendEmailChangedMessage(long userId, String newEmail);
}
