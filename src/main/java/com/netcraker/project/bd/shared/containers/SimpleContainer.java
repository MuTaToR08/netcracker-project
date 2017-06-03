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
}
