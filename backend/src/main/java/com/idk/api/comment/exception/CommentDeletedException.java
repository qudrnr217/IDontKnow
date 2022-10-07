package com.idk.api.comment.exception;

import javax.persistence.EntityNotFoundException;

public class CommentDeletedException extends EntityNotFoundException {
    public CommentDeletedException() {
        super("삭제된 댓글입니다.");
    }
}
