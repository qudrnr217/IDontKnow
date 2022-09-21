package com.idk.api.user.exception;

public class InvalidPasswordException extends IllegalStateException{
    public InvalidPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
