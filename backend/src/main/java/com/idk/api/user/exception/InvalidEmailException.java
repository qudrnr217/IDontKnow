package com.idk.api.user.exception;

public class InvalidEmailException extends IllegalStateException{
    public InvalidEmailException() {
        super("등록된 사용자 정보가 없습니.");
    }
}
