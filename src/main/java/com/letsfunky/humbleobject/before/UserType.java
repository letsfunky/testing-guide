package com.letsfunky.humbleobject.before;

enum UserType {
    Customer(1), Employee(2);

    private int typeCode;

    UserType(int typeCode) {
        this.typeCode = typeCode;
    }
}
