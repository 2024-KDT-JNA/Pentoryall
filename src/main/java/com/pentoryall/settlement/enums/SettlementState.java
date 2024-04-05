package com.pentoryall.settlement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SettlementState {

    REQUESTED("요청"),
    APPROVED("승인"),
    REJECTED("반려");

    private final String name;
}
