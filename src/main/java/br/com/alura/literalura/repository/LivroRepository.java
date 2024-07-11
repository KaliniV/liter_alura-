package br.com.alura.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.literalura.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
      Livro getByTitulo(String titulo);

    @SuppressWarnings("null")
    List<Livro> findAll();

    @Query("SELECT COUNT(l) FROM Livro l WHERE l.idioma LIKE %:idioma%")
    long contaLivrosEmIdioma(String idioma);

    List<Livro> findLivroByIdioma(String idioma);
}
