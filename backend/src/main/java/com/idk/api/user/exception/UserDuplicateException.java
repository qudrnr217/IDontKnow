package com.idk.api.user.exception;

public class UserDuplicateException extends IllegalStateException{
    public UserDuplicateException() {
        super("중복된 유저입니다.");
    }
}
