package br.com.alura.literalura.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.repository.AutorRepository;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void salvarLivro(Livro livro) {
        Livro livroExistente = livroRepository.getByTitulo(livro.getTitulo());

        if(livroExistente == null) {
            // Salvando o autor primeiro
            Autor autor = livro.getAutor();

            // Verifica se o autor já existe para evitar duplicatas
            Autor autorExistente = autorRepository.findByNomeAndAnoDeNascimentoAndAnoDeFalecimento(
                    autor.getNome(), autor.getAnoDeNascimento(), autor.getAnoDeFalecimento());

            if (autorExistente != null) {
                livro.setAutor(autorExistente);
                autorExistente.addLivros(livro);
            } else {
                Autor autorSalvo = autorRepository.save(autor);
                livro.setAutor(autorSalvo);
                autorSalvo.addLivros(livroExistente);
            }

            livroRepository.save(livro);
            System.out.println("\n+++ Livro cadastrado com sucesso!");
        } else {
            System.out.println("\nLivro já cadastrado!");
        }

    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public List<Autor> findAllAutor() {
        return autorRepository.findAll();
    }

    public List<Autor> autorVivoNoAno(Integer anoInformado) {
        return autorRepository.autorVivoNoAno(anoInformado);
    }

    public Integer contaLivrosEmIdioma(String idiomaBuscado) {
        return (int) livroRepository.contaLivrosEmIdioma(idiomaBuscado);
    }

    public List<Livro> listaDeLivrosPorIdioma(String idioma) {
        return livroRepository.findLivroByIdioma(idioma);
    }
}