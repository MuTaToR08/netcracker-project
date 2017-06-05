package com.netcraker.project.bd.shared.containers;

import com.netcraker.project.bd.shared.objects.ObjectBD;

public class SimpleContainer<E extends ObjectBD> {

    private E data;

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public SimpleContainer() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleContainer<ObjectBD> that = (SimpleContainer<ObjectBD>) o;

        return data != null ? data.equals(that.data) : that.data == null;

    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}
