package com.edm.kassimentz.meupontomobile.database;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kassi on 14/05/16.
 */
public interface DAO<T> {

    public boolean add(T t);
    public boolean update(int index, T t);
    public boolean remove(int index);
    public T getByIndex(int index);
    public List<T> getAll();
}
