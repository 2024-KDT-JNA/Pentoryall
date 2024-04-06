package com.pentoryall.common.dto;

import com.pentoryall.common.page.SelectCriteria;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PageResponse<T> {

    private List<T> list = new ArrayList<>();
    private final SelectCriteria criteria;

    public PageResponse() {
        this.list.addAll(new ArrayList<>());
        this.criteria = null;
    }

    public PageResponse(List<T> list, SelectCriteria criteria) {
        this.list.addAll(list);
        this.criteria = criteria;
    }
}
