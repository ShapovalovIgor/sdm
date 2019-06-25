package ru.shapovalov.sdm.repository.support;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class Page<T> {
    private Collection<T> list;

    private long count = 0;

    public Page() {
    }

    public Page(List<T> list, long count) {
        this.list = list;
        this.count = count;
    }
}

