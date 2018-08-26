package model.dao;

import java.util.List;

public interface GenericDao<T> {
    boolean setInDb(T entity);
    T getById(int id);
    List<T> getAll();
    boolean update(T entity);
    boolean delete(int id);
    void close();
}
