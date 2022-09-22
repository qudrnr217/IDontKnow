package com.idk.api.vote.exception;

import com.idk.api.common.exception.BusinessException;

public class BallotDuplicatedException extends BusinessException {
    public BallotDuplicatedException() {
        super("이미 완료된 투표입니다.");
    }
}
