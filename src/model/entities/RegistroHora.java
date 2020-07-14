package model.entidades;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class RegistroHora implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String data;
    private String diaSemana;
    private String hora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof RegistroHora)) {
            return false;
        }
        RegistroHora other = (RegistroHora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\n" + data + " - hora: " + hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        this.diaSemana = retornaDiaSemana(data);

    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    private String retornaDiaSemana(String data) {
        String diaSemana = "";
        Date d = null;
        try {
            d = new SimpleDateFormat("dd/MM/yy").parse(data);
        } catch (ParseException ex) {
        }
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        int dia = c.get(c.DAY_OF_WEEK);
        switch (dia) {
            case Calendar.SUNDAY:
                diaSemana = "Domingo";
                break;
            case Calendar.MONDAY:
                diaSemana = "Segunda";
                break;
            case Calendar.TUESDAY:
                diaSemana = "Terça";
                break;
            case Calendar.WEDNESDAY:
                diaSemana = "Quarta";
                break;
            case Calendar.THURSDAY:
                diaSemana = "Quinta";
                break;
            case Calendar.FRIDAY:
                diaSemana = "Sexta";
                break;
            case Calendar.SATURDAY:
                diaSemana = "Sábado";
                break;
        }
        return diaSemana;
    }

}
