package com.example.petstoreclone.dao;

import java.util.Optional;

public interface CrudDao<T, ID> {
    Optional<T> save(T element);

    Optional<T> findById(ID id);
}
