package model.entities;

import java.util.Objects;

public class Registro {
    private int id;
    private String data;
    private String diaSemana;
    private String hora;

    public Registro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Registro)) return false;
        Registro registro = (Registro) o;
        return id == registro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Registro{");
        sb.append("data='").append(data).append('\'');
        sb.append(", hora='").append(hora).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
