package com.edm.kassimentz.meupontomobile.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Cassio on 14/05/16.
 */
public abstract class DaoDB<T> implements DAO<T> {

    public Set<T> objects = new HashSet<T>();

    @Override
    public boolean add(T t) {
        if (t != null && !objects.contains(t))
            objects.add(t);

        return objects.contains(t);
    }

    @Override
    public boolean update(int index, T t) {
        if (index >= 0 && t != null) {
            if (objects.remove(getByIndex(index))) {
                objects.add(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        Object obj = getByIndex(index);

        if (obj != null && objects.contains(obj))
            objects.remove(obj);

        return !objects.contains(obj);
    }

    @Override
    public T getByIndex(int index) {
        if (index >= 0 && index < objects.size())
            return new ArrayList<T>(objects).get(index);

        return null;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<T>(objects);
    }
}
