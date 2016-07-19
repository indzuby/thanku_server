package com.thanks.service;

import java.util.List;

/**
 * Created by micky on 2016. 7. 17..
 */
public interface ServiceBase<T> {

    List<T> findAll();

    T find(Long key);

    T add(T data);

    T update(Long key, T data);

    void remove(Long key);

}
