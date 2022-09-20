package com.idk.api.user.exception;

public class InvalidLoginException extends IllegalStateException{
    public InvalidLoginException() {
        super("비밀번호를 다시 입력하세요.");
    }
}
