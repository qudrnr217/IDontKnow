package com.idk.api.home.exception;

import javax.persistence.EntityNotFoundException;

public class HotspotNotExistsException extends EntityNotFoundException {
    public HotspotNotExistsException() {
        super("인기 장소 데이터가 없습니다.");
    }
}
