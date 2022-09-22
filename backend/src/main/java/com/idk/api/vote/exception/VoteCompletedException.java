package com.idk.api.vote.exception;

import com.idk.api.common.exception.InvalidValueException;

public class VoteCompletedException extends InvalidValueException {
    public VoteCompletedException() {
        super("이미 종료된 투표입니다.");
    }
}
