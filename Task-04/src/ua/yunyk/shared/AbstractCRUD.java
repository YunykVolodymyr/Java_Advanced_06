package ua.yunyk.shared;

import java.util.List;

public interface AbstractCRUD<T, E> {

	T create(T t);

	T read(Integer id);

	T readByParameter(E parameter);

	T update(T t);

	void delete(Integer id);

	List<T> readAll();
}
