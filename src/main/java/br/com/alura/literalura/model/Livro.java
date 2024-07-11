package br.com.alura.literalura.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String nomeAutor;

    private Integer quantidadeDeDownloads;

    private String idioma;

    @ManyToOne
    private Autor autor;

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.nomeAutor = dadosLivro.autor().get(0).nome();
        this.quantidadeDeDownloads = dadosLivro.quantidadeDeDownloads();
        this.idioma = dadosLivro.idiomas().get(0).toString();
    }

    public Livro() {
        //TODO Auto-generated constructor stub
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }


    public Integer getQuantidadeDeDownloads() {
        return quantidadeDeDownloads;
    }

    public void setQuantidadeDeDownloads(Integer quantidadeDeDownloads) {
        this.quantidadeDeDownloads = quantidadeDeDownloads;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "++++++++++ Livro ++++++++++" +
                "\nTitulo: " + titulo +
                "\nAutor: " + nomeAutor +
                "\nNúmero de Downloads: " + quantidadeDeDownloads +
                "\nIdioma: " + idioma +
                "\n+++++++++++++++++++++++++++\n";
    }


}
