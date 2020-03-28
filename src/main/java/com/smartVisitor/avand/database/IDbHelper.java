package com.smartVisitor.avand.database;

import java.util.List;

public interface IDbHelper<T> {
        boolean create(T t);
        List<T> search(String keyword);
        List<T> findAll();
        T find(int id);
        boolean update(T t);
        boolean delete(int id);
        boolean delete();
}
