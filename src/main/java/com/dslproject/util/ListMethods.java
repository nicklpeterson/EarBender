package com.dslproject.util;

import java.util.Collection;

/*
This class allows us to add these methods to any class with a List<T> field, using the annotation:
@Delegate(types={ListMethods.class})
 */
public interface ListMethods<T> {
    T get(int index);
    T add(T item);
    T addAll(Collection<T> collection);
}
