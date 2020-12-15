package Classes;

import java.io.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Autor extends Pessoa {

    private String[] principaisObras = new String[3];
    private String nacionalidade;
    private ArrayList<Autor> listaAutores = new ArrayList<Autor>();

    public ArrayList<Autor> criaArrayAutor() throws FileNotFoundException, IOException {

        FileReader arquivo = new FileReader("C:\\Users\\yuri-\\Documents\\NetBeansProjects\\tf_poo_202011\\src\\Arquivos\\autores.txt");
        BufferedReader leitor = new BufferedReader(arquivo);
        String s;

        while ((s = leitor.readLine()) != null) {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data;
            String[] principaisObras = new String[3];
            Autor autor = new Autor();
            try {
                String numRegistroLocal = s;
                String nome = leitor.readLine();
                data = (Date) formato.parse(leitor.readLine());
                String email = leitor.readLine();
                principaisObras[0] = leitor.readLine();
                principaisObras[1] = leitor.readLine();
                principaisObras[2] = leitor.readLine();
                String nacionalidade = leitor.readLine();

                autor = new Autor(numRegistroLocal, nome, data, email, principaisObras, nacionalidade);

            } catch (ParseException e) {
                System.out.println("Erro: " + e);
            }
            listaAutores.add(autor);

        }
        return listaAutores;

    }

    public void gravarArquivo() throws IOException {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        File arquivo = new File("C:\\Users\\yuri-\\Documents\\NetBeansProjects\\tf_poo_202011\\src\\Arquivos\\autores.txt");
        FileWriter arq = new FileWriter(arquivo);
        try (PrintWriter pw = new PrintWriter(arq)) {
            for (int i = 0; i < listaAutores.size(); i++) {
                pw.println(listaAutores.get(i).getNumRegistro());
                pw.println(listaAutores.get(i).getNome());
                pw.println(formatador.format(listaAutores.get(i).getDataNascimento()));
                pw.println(listaAutores.get(i).getEmail());
                pw.println(listaAutores.get(i).getPrincipaisObras()[0]);
                pw.println(listaAutores.get(i).getPrincipaisObras()[1]);
                pw.println(listaAutores.get(i).getPrincipaisObras()[2]);
                pw.println(listaAutores.get(i).getNacionalidade());
            }
            pw.flush();
            arq.close();
        }

    }

    public Autor() {
        super();
    }

    public Autor(String numRegistro, String nome, Date dataNascimento, String email, String[] principaisObras,
            String nacionalidade) {
        super(numRegistro, nome, dataNascimento, email);
        this.principaisObras = principaisObras;
        this.nacionalidade = nacionalidade;
    }

    public String cadastra(Autor autor) {
        listaAutores.add(autor);
        try {
            this.gravarArquivo();
        } catch (IOException e) {
            System.out.println("Erro:" + e);
        }
        return "Autor cadastrado com sucesso!";
    }


    public String excluiAutor(String numRegistro, ArrayList<Autor> lista) throws IOException {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumRegistro().equals(numRegistro)) {
                lista.remove(i);
            }
        }
        this.gravarArquivo();
        return "Autor excluido";
    }

    public Object consultaAutor(String numRegistro, ArrayList<Autor> lista) throws IOException {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumRegistro().equals(numRegistro)) {
                return lista.get(i);
            }
        }
        this.gravarArquivo();
        return "Livro excluido";
    }

    public String altera(Autor autor, ArrayList<Autor> lista) throws IOException {

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumRegistro().equals(autor.getNumRegistro())) {
                lista.get(i).setNumRegistro(autor.getNumRegistro());
                lista.get(i).setNome(autor.getNome());
                lista.get(i).setDataNascimento(autor.getDataNascimento());
                lista.get(i).setEmail(autor.getEmail());
                lista.get(i).setPrincipaisObras(autor.getPrincipaisObras());
                lista.get(i).setNacionalidade(autor.getNacionalidade());
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
                str += this.consultaAutor(s, this.criaArrayAutor()).toString();
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

    public String[] getPrincipaisObras() {
        return this.principaisObras;
    }

    public void setPrincipaisObras(String[] principaisObras) {
        this.principaisObras = principaisObras;
    }

    public String getNacionalidade() {
        return this.nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return "==========================================================\n" + "Autor " + getNome() + ":\n\n"
                + "Número de Registro: " + getNumRegistro() + "\nNome: " + getNome() + "\nData de Nascimento: "
                + getDataNascimento() + "\nE-mail: " + getEmail() + "\nPrincipais Obras: " + getPrincipaisObras()[0]
                + ", " + getPrincipaisObras()[1] + ", " + getPrincipaisObras()[2]
                + "\nNacionalidade: " + getNacionalidade()
                + "\n==========================================================\n";
    }

}
