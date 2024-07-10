package br.com.alura.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosService(@JsonAlias("title") String titulo,
                       @JsonAlias("authors") List<DadosAutor> autor,
                       @JsonAlias("download_count") Integer quantidadeDeDownloads,
                       @JsonAlias("languages") List<String> idiomas) {

}