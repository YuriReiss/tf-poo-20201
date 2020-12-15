package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Locador extends Pessoa {

    private String cpf;
    private String telefone;
    private ArrayList<Locador> listaLocadores = new ArrayList<Locador>();
    private String[] livrosLocados = new String[3];

    public Locador() {
        super();
    }

    public Locador(String numRegistro, String nome, Date dataNascimento, String email, String cpf,
            String telefone) {
        super(numRegistro, nome, dataNascimento, email);
        this.cpf = cpf;
        this.telefone = telefone;
        this.livrosLocados[0] = null;
        this.livrosLocados[1] = null;
        this.livrosLocados[2] = null;
    }

    public Locador(String numRegistro, String nome, Date dataNascimento, String email, String cpf,
            String telefone, String[] livrosLocados) {
        super(numRegistro, nome, dataNascimento, email);
        this.cpf = cpf;
        this.telefone = telefone;
        this.livrosLocados = livrosLocados;

    }

    public ArrayList<Locador> criaArrayLocador() throws FileNotFoundException, IOException {

        FileReader arquivo = new FileReader("C:\\Users\\yuri-\\Documents\\NetBeansProjects\\tf_poo_202011\\src\\Arquivos\\locadores.txt");
        BufferedReader leitor = new BufferedReader(arquivo);
        String s;

        while ((s = leitor.readLine()) != null) {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data;
            String[] livrosLocados = new String[3];
            Locador locador = new Locador();
            try {
                String numRegistroLocal = s;
                String nome = leitor.readLine();
                data = (Date) formato.parse(leitor.readLine());
                String email = leitor.readLine();
                String cpf = leitor.readLine();
                String telefone = leitor.readLine();
                livrosLocados[0] = leitor.readLine();
                livrosLocados[1] = leitor.readLine();
                livrosLocados[2] = leitor.readLine();

                locador = new Locador(numRegistroLocal, nome, data, email, cpf, telefone, livrosLocados);

            } catch (ParseException e) {
                System.out.println("Erro: " + e);
            }
            listaLocadores.add(locador);

        }
        return listaLocadores;

    }

    public void gravarArquivo() throws IOException {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        File arquivo = new File("C:\\Users\\yuri-\\Documents\\NetBeansProjects\\tf_poo_202011\\src\\Arquivos\\locadores.txt");
        FileWriter arq = new FileWriter(arquivo);
        try (PrintWriter pw = new PrintWriter(arq)) {
            for (int i = 0; i < listaLocadores.size(); i++) {
                pw.println(listaLocadores.get(i).getNumRegistro());
                pw.println(listaLocadores.get(i).getNome());
                pw.println(formatador.format(listaLocadores.get(i).getDataNascimento()));
                pw.println(listaLocadores.get(i).getEmail());
                pw.println(listaLocadores.get(i).cpf);
                pw.println(listaLocadores.get(i).telefone);
                pw.println(listaLocadores.get(i).livrosLocados[0]);
                pw.println(listaLocadores.get(i).livrosLocados[1]);
                pw.println(listaLocadores.get(i).livrosLocados[2]);

            }
            pw.flush();
            arq.close();
        }

    }

    public boolean locarLivro(Locador locador,String nome) {
        Livro livro = new Livro();
        try {
            livro = (Livro) livro.consultaLivro(nome, livro.criaArrayLivro());
        } catch (IOException e) {
            System.out.println("Erro:" + e);
        }
        String[] livrosLocados = locador.getLivrosLocados();
        System.out.println(livrosLocados);
        int i;
        for (i = 0; i < 3; i++) {
            if (livrosLocados[i].equals("null")) {
                livrosLocados[i] = nome;
                break;
            }
        }
        if (i == 3) {
            System.out.println("passei1");
            return false;
        } else {
            System.out.println("passei2");
            livro.setQtdDisponiveis(livro.getQtdDisponiveis() - 1);
            try {
                livro.alterarLivro(livro, livro.criaArrayLivro());
            } catch (IOException e) {
                System.out.println("Erro:" + e);
            }
            locador.setLivrosLocados(livrosLocados);
            try {
                this.altera(this, this.criaArrayLocador());
            } catch (IOException e) {
                System.out.println("Erro: " + e);
            }
            return true;
        }

    }

    public boolean devolveLivro(String nome) {
        Livro livro = new Livro();
        try {
            livro = (Livro) livro.consultaLivro(nome, livro.criaArrayLivro());
        } catch (IOException e) {
            System.out.println("Erro:" + e);
        }
        String[] livrosLocados = this.getLivrosLocados();
        int i;
        for (i = 0; i < 3; i++) {
            if (livrosLocados[i].equals(nome)) {
                livrosLocados[i] = null;
                break;
            }
        }
        if (i == 3) {
            return false;
        } else {
            livro.setQtdDisponiveis(livro.getQtdDisponiveis() + 1);
            try {
                livro.alterarLivro(livro, livro.criaArrayLivro());
            } catch (IOException e) {
                System.out.println("Erro:" + e);
            }
            this.setLivrosLocados(livrosLocados);
            try {
                this.altera(this, this.criaArrayLocador());
            } catch (IOException e) {
                System.out.println("Erro: " + e);
            }
            return true;
        }

    }

    public String cadastra(Locador locador) {
        listaLocadores.add(locador);
        try {
            this.gravarArquivo();
        } catch (IOException e) {
            System.out.println("Erro:" + e);
        }
        return "Locador cadastrado com sucesso!";
    }

    public String excluiLocador(String numRegistro, ArrayList<Locador> lista) throws IOException {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumRegistro().equals(numRegistro)) {
                lista.remove(i);
                break;
            }
        }
        this.gravarArquivo();
        return "Locador excluido";
    }

    public Object consultaLocador(String numRegistro, ArrayList<Locador> lista) throws IOException {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumRegistro().equals(numRegistro)) {
                return lista.get(i);
            }
        }
        this.gravarArquivo();
        return "Locador excluido";
    }

    public String altera(Locador locador, ArrayList<Locador> lista) throws IOException {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumRegistro().equals(locador.getNumRegistro())) {
                lista.get(i).setNumRegistro(locador.getNumRegistro());
                lista.get(i).setNome(locador.getNome());
                lista.get(i).setDataNascimento(locador.getDataNascimento());
                lista.get(i).setEmail(locador.getEmail());
                lista.get(i).setCpf(locador.getCpf());
                lista.get(i).setTelefone(locador.getTelefone());
                lista.get(i).setLivrosLocados(locador.getLivrosLocados());
            }
        }
        this.gravarArquivo();
        return "Alterado com sucesso!";
    }

    public String lista() throws IOException {

        String s = "";
        String str = "";

        try {
            FileReader autores = new FileReader("C:\\Users\\yuri-\\Documents\\NetBeansProjects\\tf_poo_202011\\src\\Arquivos\\autores.txt");
            BufferedReader in = new BufferedReader(autores);

            while ((s = in.readLine()) != null) {
                str += this.consultaLocador(s, this.criaArrayLocador()).toString();
                for (int i = 0; i < 7; i++) {
                    s = in.readLine();
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.print("Erro: " + e);
        }
        return str;
    }

    public String[] getLivrosLocados() {
        return this.livrosLocados;
    }

    public void setLivrosLocados(String[] livrosLocados) {
        this.livrosLocados = livrosLocados;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "==========================================================\n" + "Locador " + getNome() + ":\n\n"
                + "Número de Registro: " + getNumRegistro() + "\nNome: " + getNome() + "\nData de Nascimento: "
                + getDataNascimento() + "\nE-mail: " + getEmail() + "\nCPF: " + getCpf()
                + "\nLivros Locados: " + getLivrosLocados()[0] + ", " + getLivrosLocados()[1] + ", " + getLivrosLocados()[2] + "\nTelefone: " + getTelefone()
                + "\n==========================================================\n";
    }

}
