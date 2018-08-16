package model.dao;

import java.util.List;

public interface GenericDao<T> {
    boolean setInDb(T entiry);
    T getById(int id);
    List<T> getAll();
    boolean update(T entity);
    boolean delete(T entity);
    void close();
}
