package com.idk.api.vote.exception;

import javax.persistence.EntityNotFoundException;

public class VoteNotFoundException extends EntityNotFoundException {
    public VoteNotFoundException() {
        super("존재하지 않는 투표입니다.");
    }
}
