package br.com.alura.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.literalura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
   Autor findByNomeAndAnoDeNascimentoAndAnoDeFalecimento(String nome, Integer anoDeNascimento, Integer anoDeFalecimento);

    @Query("SELECT a FROM Autor a WHERE a.anoDeFalecimento >= :anoInformado")
    List<Autor> autorVivoNoAno(Integer anoInformado);
    
} 
