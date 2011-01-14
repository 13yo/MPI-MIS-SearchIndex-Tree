package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

public abstract class IndexNode<T> {

    public abstract Long getId();

    public abstract T getValue();

    public abstract void setValue(T value);

}
