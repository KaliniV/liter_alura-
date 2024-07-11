package br.com.alura.literalura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer anoDeNascimento;

    private Integer anoDeFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<Livro>();

      public Autor() {}

      public Autor(String titulo, DadosAutor dadosAutor) {
          this.nome = dadosAutor.nome();
          this.anoDeNascimento = dadosAutor.anoDeNascimento();
          this.anoDeFalecimento = dadosAutor.anoDeFalecimento();
      }
    public List<Livro> getLivros() {
        return livros;
    }

    public void addLivros(Livro livro) {
        livro.setAutor(this);
        this.livros.add(livro);
    }

    public void setLivros(Livro livro) {
        this.livros.add(livro);
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(Integer anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public Integer getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(Integer anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }
@Override
    public String toString() {
        String livrosStr = livros.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.joining(", "));

        return  "\n++++++++++ Autor +++++++++" +
                "\nNome: " + nome +
                "\nAno de Nascimento: " + anoDeNascimento +
                "\nAno de Falecimento: " + anoDeFalecimento +
                "\nLivros: " + livrosStr +
                "\n+++++++++++++++++++++++++++\n";
    }
    
}
