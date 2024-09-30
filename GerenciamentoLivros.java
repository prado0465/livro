import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GerenciamentoLivros {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Livro> livros = new ArrayList<>();

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            limparTela();
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Pesquisar Livro por Título");
            System.out.println("3. Remover Livro por Título");
            System.out.println("4. Sair");
            System.out.print("Selecione uma opção: ");
            int opcao = validarEntradaNumerica();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    pesquisarLivro();
                    break;
                case 3:
                    removerLivro();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

   
    public static void cadastrarLivro() {
        limparTela();
        System.out.println("=== Cadastro de Livro ===");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        if (autor == null || autor.trim().isEmpty()) {
            System.out.println("Erro: Autor é obrigatório.");
            return;
        }

        System.out.print("Ano de Publicação: ");
        int ano = validarEntradaNumerica();
        int anoAtual = LocalDate.now().getYear();
        if (ano < 1400 || ano > anoAtual) {
            System.out.println("Erro: O ano de publicação deve ser entre 1400 e " + anoAtual + ".");
            return;
        }

        System.out.print("Número de Páginas: ");
        int paginas = validarEntradaNumerica();
        if (paginas <= 0) {
            System.out.println("Erro: O número de páginas deve ser maior que zero.");
            return;
        }

       
        livros.add(new Livro(titulo, autor, ano, paginas));
        System.out.println("Livro cadastrado com sucesso!");
    }

   
    public static void pesquisarLivro() {
        limparTela();
        System.out.println("=== Pesquisa de Livro ===");

        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();
        boolean encontrado = false;

        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("Livro encontrado: " + livro);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum livro encontrado com o título: " + titulo);
        }
    }

    
    public static void removerLivro() {
        limparTela();
        System.out.println("=== Remoção de Livro ===");

        System.out.print("Digite o título do livro a ser removido: ");
        String titulo = scanner.nextLine();
        Iterator<Livro> iterator = livros.iterator();
        boolean removido = false;

        while (iterator.hasNext()) {
            Livro livro = iterator.next();
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                iterator.remove();
                removido = true;
                System.out.println("Livro removido com sucesso.");
            }
        }

        if (!removido) {
            System.out.println("Nenhum livro encontrado com o título: " + titulo);
        }
    }

  
    public static void limparTela() {
   
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

  
    public static int validarEntradaNumerica() {
        while (!scanner.hasNextInt()) {
            System.out.println("Erro: Entrada inválida. Digite um número.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}


class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int numeroPaginas;

    public Livro(String titulo, String autor, int anoPublicacao, int numeroPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.numeroPaginas = numeroPaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Ano: " + anoPublicacao + ", Páginas: " + numeroPaginas;
    }
}
