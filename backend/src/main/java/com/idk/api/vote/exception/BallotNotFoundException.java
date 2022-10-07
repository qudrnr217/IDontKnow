package com.idk.api.vote.exception;

import javax.persistence.EntityNotFoundException;

public class BallotNotFoundException extends EntityNotFoundException {
    public BallotNotFoundException() {
        super("존재하지 않는 투표참여입니다.");
    }
}
