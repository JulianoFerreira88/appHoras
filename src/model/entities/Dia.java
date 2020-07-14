package model.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Dia {

    private int id;
    private String data;
    private ArrayList<Registro> registros;
    private String diaSemana;

    //Construtores
    public Dia(int id, String data, ArrayList<Registro> registros, String diaSemana) {
        this.id = id;
        this.data = data;
        this.registros = registros;
        this.diaSemana = diaSemana;
    }

    public Dia() {
    }

    public Dia(String data) {
        this.data = data;
    }

    public Dia(int id, String data, ArrayList<Registro> registros) {
        this.id = id;
        this.data = data;
        this.registros = registros;
    }
    //Getters and Setters

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
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
        this.diaSemana = retornaDiaSemana(data);
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    //Equals, HashCode e toString
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dia other = (Dia) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return data + " - " + diaSemana + " - Horarios: " + registros + " - " + totalDoDia();
    }

    //Metodos importantes
    /*
     recebe duas Strings com a hora inicial e 
     hora final e retorna a diferenca 
     entre os dois horarios em String
     */
    private String diferencaEntreDoisHorariosEmString(String inicio, String fim) {
        try {
            int minutos = diferencaEntreDoisHorariosEmInt(inicio, fim);
            if (minutos % 60 == 0) {
                return minutos % 60 + ":00";
            } else {
                return "" + (minutos / 60) + ":" + (minutos % 60);
            }

        } catch (Exception e) {
            return null;
        }

    }

    private int diferencaEntreDoisHorariosEmInt(String inicio, String fim) {
        int resultado = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            //d1 = format.parse(inicio);
            //d2 = format.parse(fim);
            Date dt1 = format.parse(inicio);
            Date dt2 = format.parse(fim);
            //DateTime dt1 = new DateTime(d1);
            //DateTime dt2 = new DateTime(d2);
            //resultado = Minutes.minutesBetween(dt1, dt2).getMinutes();
            long diff = dt2.getTime() - dt1.getTime();
            resultado = (int) (diff / (60 * 1000));
            return resultado;
        } catch (Exception e) {
            return 0;
        }
    }

    private static String converteIntEmString(int res) {
        String resultado = "";
        if (res % 60 == 0) {
            res = res / 60;
            if (res < 10) {
                resultado = "0" + res % 60 + ":00";
            } else {
                resultado = res % 60 + ":00";
            }
        } else {
            int horas = res / 60;
            int minutos = res % 60;
            if (horas < 10 && minutos < 10) {
                resultado = "0" + horas + ":0" + minutos;
            } else if (horas < 10 && minutos > 9) {
                resultado = "0" + horas + ":" + minutos;
            } else if (horas > 9 && minutos < 10) {
                resultado = horas + ":0" + minutos;
            } else {
                resultado = horas + ":" + minutos;
            }
        }
        return resultado;
    }

    public static String cargaDoPeriodo(ArrayList<Dia> diasTrabalhadosNoMes) {
        String res = "";
        int minutos = 0;
        for (int i = 0; i < diasTrabalhadosNoMes.size(); i++) {
            minutos += diasTrabalhadosNoMes.get(i).getMinutos();
        }
        if (minutos == 0) {
            return "Em Andamento! Aguarde fechar o ponto!";
        } else {
            if (minutos % 60 == 0) {
                return "" + minutos / 60;
            } else {
                return "" + minutos / 60 + ":" + minutos % 60;
            }
        }
    }

    public static int cargaDoPeriodoEmMinutos(ArrayList<Dia> diasTrabalhadosNoMes) {

        int minutos = 0;
        for (int i = 0; i < diasTrabalhadosNoMes.size(); i++) {
            minutos += diasTrabalhadosNoMes.get(i).getMinutos();
        }
        return minutos;

    }

    private static int diferencaEntreHoras(String inicio, String fim) {
        int resultado = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date dt1 = dt1 = sdf.parse(inicio);
            Date dt2 = dt2 = sdf.parse(fim);

            long diff = dt2.getTime() - dt1.getTime();

            resultado = (int) (diff / (60 * 1000));
            return resultado;

        } catch (Exception e) {
            return 0;
        }
    }

    public String totalDoDia() {
        String cargaHorariaTotal = "";
        if (this.registros != null) {

            if (!this.registros.isEmpty()) {
                switch (registros.size()) {
                    case 1: {
                        cargaHorariaTotal = "Em andamento!";
                        break;
                    }
                    case 2: {
                        cargaHorariaTotal = diferencaEntreDoisHorariosEmString(registros.get(0).getHora(), registros.get(1).getHora());
                        break;
                    }
                    case 3: {
                        cargaHorariaTotal = "Em andamento!";
                        break;
                    }
                    case 4: {
                        int primeiroTurno = diferencaEntreDoisHorariosEmInt(registros.get(0).getHora(), registros.get(1).getHora());
                        int segundoTurno = diferencaEntreDoisHorariosEmInt(registros.get(2).getHora(), registros.get(3).getHora());
                        int total = primeiroTurno + segundoTurno;
                        cargaHorariaTotal = converteIntEmString(total);
                        break;
                    }
                    case 5: {
                        cargaHorariaTotal = "Em andamento!";
                        break;
                    }
                    case 6: {
                        int primeiroTurno = diferencaEntreDoisHorariosEmInt(registros.get(0).getHora(), registros.get(1).getHora());
                        int segundoTurno = diferencaEntreDoisHorariosEmInt(registros.get(2).getHora(), registros.get(3).getHora());
                        int terceiroTurno = diferencaEntreDoisHorariosEmInt(registros.get(4).getHora(), registros.get(5).getHora());
                        int total = primeiroTurno + segundoTurno + terceiroTurno;
                        cargaHorariaTotal = converteIntEmString(total);
                        break;
                    }
                    default: {
                        cargaHorariaTotal = "Voçê registrou mais de 6 horários";
                        break;
                    }

                }
            } else {
                cargaHorariaTotal = "Registros estão vazios!, verifique as informações!";
            }
        }
        return "Total do Dia: " + cargaHorariaTotal;
    }

    public int getMinutos() {
        int minutosDia = 0;
        if (this.registros != null) {

            if (!this.registros.isEmpty()) {
                switch (registros.size()) {
                    case 1: {
                        minutosDia = 0;
                        break;
                    }
                    case 2: {
                        minutosDia += diferencaEntreDoisHorariosEmInt(registros.get(0).getHora(), registros.get(1).getHora());
                        break;
                    }
                    case 3: {

                        break;
                    }
                    case 4: {
                        int primeiroTurno = diferencaEntreDoisHorariosEmInt(registros.get(0).getHora(), registros.get(1).getHora());
                        int segundoTurno = diferencaEntreDoisHorariosEmInt(registros.get(2).getHora(), registros.get(3).getHora());
                        int total = primeiroTurno + segundoTurno;
                        minutosDia = total;
                        break;
                    }
                    case 5: {

                        break;
                    }
                    case 6: {
                        int primeiroTurno = diferencaEntreDoisHorariosEmInt(registros.get(0).getHora(), registros.get(1).getHora());
                        int segundoTurno = diferencaEntreDoisHorariosEmInt(registros.get(2).getHora(), registros.get(3).getHora());
                        int terceiroTurno = diferencaEntreDoisHorariosEmInt(registros.get(4).getHora(), registros.get(5).getHora());
                        minutosDia = primeiroTurno + segundoTurno + terceiroTurno;
                        break;
                    }
                    default: {

                        break;
                    }

                }
            }
        }

        return minutosDia;
    }

    public static String retornaDiaSemana(String data) {
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

    public static String resultadoGeral(ArrayList<Dia> diasTrabalhados) {
        if (!diasTrabalhados.isEmpty()) {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
            String data = sdf.format(d);
            int totalMinutos = 0;
            int minutosNormais = 0;
            ArrayList<Dia> segunda = new ArrayList<>();
            ArrayList<Dia> terca = new ArrayList<>();
            ArrayList<Dia> quarta = new ArrayList<>();
            ArrayList<Dia> quinta = new ArrayList<>();
            ArrayList<Dia> sexta = new ArrayList<>();
            ArrayList<Dia> sabado = new ArrayList<>();
            ArrayList<Dia> domingo = new ArrayList<>();
            ArrayList<Dia> diasNormais = new ArrayList<>();

            StringBuilder resultado = new StringBuilder();
            String dataInicial = diasTrabalhados.get(0).getData();
            String dataFinal = diasTrabalhados.get(diasTrabalhados.size() - 1).getData();

            resultado.append("      Resultado do Período ").append(dataInicial)
                    .append(" - ").append(dataFinal).append("\n");
            resultado.append("      Data do Relatório: ").append(data).append("\n");
            for (int i = 0; i < diasTrabalhados.size(); i++) {
                switch (diasTrabalhados.get(i).getDiaSemana()) {
                    case "Segunda": {

                        segunda.add(diasTrabalhados.get(i));
                        diasNormais.add(diasTrabalhados.get(i));
                        minutosNormais += diasTrabalhados.get(i).getMinutos();
                        break;
                    }

                    case "Terça": {
                        terca.add(diasTrabalhados.get(i));
                        diasNormais.add(diasTrabalhados.get(i));
                        minutosNormais += diasTrabalhados.get(i).getMinutos();
                        break;
                    }

                    case "Quarta": {
                        quarta.add(diasTrabalhados.get(i));
                        diasNormais.add(diasTrabalhados.get(i));
                        minutosNormais += diasTrabalhados.get(i).getMinutos();
                        break;
                    }

                    case "Quinta": {
                        quinta.add(diasTrabalhados.get(i));
                        diasNormais.add(diasTrabalhados.get(i));
                        minutosNormais += diasTrabalhados.get(i).getMinutos();
                        break;
                    }

                    case "Sexta": {
                        sexta.add(diasTrabalhados.get(i));
                        diasNormais.add(diasTrabalhados.get(i));
                        minutosNormais += diasTrabalhados.get(i).getMinutos();
                        break;
                    }

                    case "Sábado": {
                        sabado.add(diasTrabalhados.get(i));
                        diasNormais.add(diasTrabalhados.get(i));
                        minutosNormais += diasTrabalhados.get(i).getMinutos();
                        break;
                    }

                    case "Domingo": {
                        domingo.add(diasTrabalhados.get(i));
                        break;
                    }

                }

                totalMinutos += (diasTrabalhados.get(i).getMinutos());

            }

            //resultado.append("|Total de Minutos Trabalhados: ").append(totalHoras).append("|\n");
            resultado.append("      Total de Horas Trabalhadas: ").append(cargaDoPeriodo(diasTrabalhados)).append("\n");
            resultado.append("      Horas Trabalhadas por dia\n");
            resultado.append("      Segundas: ").append(segunda.size()).append(" Horas: ").append(cargaDoPeriodo(segunda)).append("\n");
            resultado.append("      Terças: ").append(terca.size()).append(" Horas: ").append(cargaDoPeriodo(terca)).append("\n");
            resultado.append("      Quartas: ").append(quarta.size()).append(" Horas: ").append(cargaDoPeriodo(quarta)).append("\n");
            resultado.append("      Quintas: ").append(quinta.size()).append(" Horas: ").append(cargaDoPeriodo(quinta)).append("\n");
            resultado.append("      Sextas: ").append(sexta.size()).append(" Horas: ").append(cargaDoPeriodo(sexta)).append("\n");
            resultado.append("      Sábados: ").append(sabado.size()).append(" Horas: ").append(cargaDoPeriodo(sabado)).append("\n");
            resultado.append("      Domingos: ").append(domingo.size()).append(" Horas: ").append(cargaDoPeriodo(domingo)).append("\n");
            resultado.append("      Horas Normais: ").append(cargaDoPeriodo(diasNormais)).append("\n");
            resultado.append("      Horas 50%: ").append(minutosEmHoras(minutosNormais - 11020)).append("\n");
            //resultado.append("*----------------------------------------*");
            return resultado.toString();

        }
        return "Nenhum registro encontrado!";

    }

    public static String somarDiasEmUmaData(String data, int dias) {
        Date d = null;
        try {
            d = new SimpleDateFormat("dd/MM/yy").parse(data);
        } catch (ParseException ex) {
            return "Erro";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        //System.out.println(cal.getTime()); //data atual
        cal.add(Calendar.DAY_OF_MONTH, dias);
        SimpleDateFormat sdfSaida = new SimpleDateFormat("dd/MM/yy");

        //System.out.println(sdfSaida.format(cal.getTime()));
        return sdfSaida.format(cal.getTime());
    }

    public static String minutosEmHoras(int minutos) {
        String res = "";
        if (minutos % 60 == 0) {
            return "" + minutos / 60;
        } else {
            return "" + minutos / 60 + ":" + minutos % 60;
        }
    }


}
