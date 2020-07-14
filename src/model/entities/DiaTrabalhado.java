package model.entities;

import java.util.List;

public class DiaTrabalhado {
    private long id;
    private List<Registro> registros;

    public DiaTrabalhado() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public String totalhorasTrabalhadas() {
        return null;
    }

    public int minutosDoDia() {
        switch (getRegistros().size()) {
            case 1: {

            }
            case 2: {

            }
        }
        return 0;
    }

    public String diaSemana() {
        return null;
    }
}
