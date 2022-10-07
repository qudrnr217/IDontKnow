package com.idk.api.vote.exception;

import javax.persistence.EntityNotFoundException;

public class VoteDeletedException extends EntityNotFoundException {
    public VoteDeletedException() {
        super("삭제된 투표입니다.");
    }
}
