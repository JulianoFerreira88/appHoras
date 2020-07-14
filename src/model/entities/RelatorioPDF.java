package model.entities;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class RelatorioPDF {

    private int id;
    private String titulo;
    private String conteudo;
    private String caminhoSaidaRealtorio;

    public RelatorioPDF() {
    }

    public RelatorioPDF(String titulo, String conteudo, String caminhoSaidaRealtorio) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.caminhoSaidaRealtorio = caminhoSaidaRealtorio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getCaminhoSaidaRealtorio() {
        return caminhoSaidaRealtorio;
    }

    public void setCaminhoSaidaRealtorio(String caminhoSaidaRealtorio) {
        this.caminhoSaidaRealtorio = caminhoSaidaRealtorio;
    }

    public static void saidaPDF() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Juliano/Desktop/pdf.pdf"));
            document.open();
            BaseFont bf = BaseFont.createFont();
            document.add(new Paragraph("Novo Parágrafo", new Font(bf, 50)));

            document.close();
        } catch (DocumentException ex) {

        } catch (IOException ex) {

        }
    }
   
}
