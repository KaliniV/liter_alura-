package br.com.alura.literalura.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Dados;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.LivroService;


public class Principal {

    private Scanner sc = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();

    private ConverteDados conversor = new ConverteDados();

    private Livro livro = new Livro();

    private List<Livro> livros = new ArrayList<>();

    private List<Autor> autores = new ArrayList<>();



    private LivroService livroService;
    
    private final String ENDERECO = "https://gutendex.com/books/?search=";


    public Principal(LivroService livroService) {
        this.livroService = livroService;
    }
    public void exibeMenu() {

        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    *** Escolha uma das opções:

                    1 - Buscar livro pelo título

                    2 - Listar livros registrados

                    3 - Listar autores registrados

                    4 - Listar autores vivos em determinado ano

                    5 - Listar livros em um determinado idioma

                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosData();
                    break;
                case 5:
                    contarLivrosDoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }


    private void buscarLivroPeloTitulo() {
        System.out.println("Digite o nome do livro para busca: ");
        var tituloLivro = sc.nextLine();
        var json = consumo.obterDados(ENDERECO + tituloLivro.replace(" ", "+"));

        //dados desserializados
        Dados dados = conversor.obterDados(json, Dados.class);

        //pega o campo results e desserializa dados do livro buscado
        List<DadosLivro> dadosLivros = new ArrayList<>();
        dadosLivros = dados.livros();

        try {
            livro = new Livro(dadosLivros.get(0));
            System.out.println(livro);

            List<Autor> autores = dadosLivros.stream()
                    .limit(1)
                    .flatMap(b -> b.autor().stream()
                            .map(d -> new Autor(b.titulo(), d))
                    ).collect(Collectors.toList());

            autores.forEach(System.out::println);
            Autor author = autores.get(0);

            livro.setAutor(author);
            livroService.salvarLivro(livro);
        } catch (Exception e) {
            System.out.println("\n***Livro não encontrado\n");
        }
    
    }    
    private void listarLivrosRegistrados() {
        livros = livroService.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getNomeAutor))
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = livroService.findAllAutor();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }

    private void listarAutoresVivosData() {
        System.out.println("Informe o ano de referência: ");
        var anoInformado = sc.nextInt();
        autores = livroService.autorVivoNoAno(anoInformado);
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }

    private void contarLivrosDoIdioma() {
        System.out.println("Informe o idioma: " +
                "\nPortuguês: pt" +
                "\nInglês: en" +
                "\nEspanhol: es" +
                "\nItaliano: it");
        var idiomaBuscado = sc.nextLine();
        Integer ocorrencias = livroService.contaLivrosEmIdioma(idiomaBuscado);

        var traduzido = traduzIdioma(idiomaBuscado);

        if(ocorrencias == 0) {
            System.out.println("Não há ocorrência de livros neste idioma: " + traduzido + ". ");
        } if(ocorrencias == 1) {
            System.out.println("\n*** Tem uma ocorrência em " + traduzido + "\n");
        } else {
            System.out.println("\n*** Tem " + ocorrencias + " livros em " + traduzido + "\n");
        }
        listarLivrosEmIdioma(idiomaBuscado);
    }

    private void listarLivrosEmIdioma(String idioma) {
        livros  = livroService.listaDeLivrosPorIdioma(idioma);
        livros.forEach(System.out::println);
    }

    private String traduzIdioma(String idioma) {
        var traduz = "";
        switch (idioma) {
            case "pt": traduz = "Português";
            break;
            case "en": traduz = "Inglês";
            break;
            case "es": traduz = "Espanhol";
            break;
            case "it": traduz = "Italiano";
            break;
        }
        return traduz;
    }


}
