package com.isep.lotus.dao;

import com.isep.lotus.models.Eleve;

import java.sql.Connection;

/**
 * Created by IntelliJ.
 * User:  eliott
 * Date: 14/12/17
 * Time: 15:24
 */
public class EleveDAO extends DAO<Eleve> {

    public EleveDAO(Connection connect) {
        super(connect);
    }

    public boolean create(Eleve eleve) {
        return false;
    }

    public boolean delete(Eleve eleve) {
        return false;
    }

    public boolean update(Eleve eleve) {
        return false;
    }

}
