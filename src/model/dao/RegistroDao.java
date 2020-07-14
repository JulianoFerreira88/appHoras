package dao;

import java.util.ArrayList;

import model.entities.Registro;
import util.exception.DomainException;

public class RegistroDao {

    public RegistroDao() {

    }

    public static void insert(Registro registro) throws DomainException {
        try {
            
        } catch (Exception e) {
            throw new DomainException("Erro ao inserir registro");
        }
    }

    public static String delete(Registro registro) {
        return null;
    }

    public static String update(Registro registro) {
        return null;
    }

    public static Registro findById(int id) {
        return null;
    }

    public static ArrayList<Registro> findAll() {
        return null;
    }

    public static ArrayList<Registro> findByDate(String date) {
        return null;
    }

}
