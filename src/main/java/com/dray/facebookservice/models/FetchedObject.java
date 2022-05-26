package com.dray.facebookservice.models;

import java.util.List;

public class FetchedObject<T> {
    int offset;
    int limit;
    int totalSize;
    List<T> objects;

    public int getOffset() {
        return offset;
    }

    public FetchedObject withOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public FetchedObject withLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public FetchedObject withTotalSize(int totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public List<T> getObjects() {
        return objects;
    }

    public FetchedObject withObjects(List<T> objects) {
        this.objects = objects;
        return this;
    }
}
