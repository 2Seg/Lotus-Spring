package com.isep.lotus.dao;

import java.sql.Connection;

/**
 * Created by IntelliJ.
 * User:  eliott
 * Date: 14/12/17
 * Time: 15:20
 */
public abstract class DAO<T> {

    protected Connection connect = null;

    public DAO(Connection connect) {
        this.connect = connect;
    }

    public abstract boolean create(T object);

    public abstract boolean delete(T object);

    public abstract boolean update(T object);

//    public abstract T find(int id);

}
