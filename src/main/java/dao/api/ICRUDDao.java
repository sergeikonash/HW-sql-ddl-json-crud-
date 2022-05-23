package dao.api;

import java.util.List;

public interface ICRUDDao<T, ID> {

    T create(T item);

    List<T> get();

    T update(ID id, T item);

    void delete(ID id);
}
