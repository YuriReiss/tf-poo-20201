/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Brenda
 */
public class Livro {

    private int idLivro, qtdPaginas, anoLancamento, qtdDisponiveis, edicao;
    private float altura, largura, peso;
    private String nome, descricao, idioma, editora, genero;
    private String autor;
    ArrayList<Livro> listaLivro = new ArrayList();

    public ArrayList<Livro> criaArrayLivro() throws FileNotFoundException, IOException {

        FileReader arquivo = new FileReader("C:\\Users\\yuri-\\Documents\\NetBeansProjects\\tf_poo_202011\\src\\Arquivos\\livros.txt");
        BufferedReader leitor = new BufferedReader(arquivo);
        String s;

        while ((s = leitor.readLine()) != null) {

            Livro livro = new Livro(s, Integer.parseInt(leitor.readLine()), leitor.readLine(), leitor.readLine(), Integer.parseInt(leitor.readLine()),
                    leitor.readLine(), Integer.parseInt(leitor.readLine()), leitor.readLine(), Float.parseFloat(leitor.readLine()), Integer.parseInt(leitor.readLine()),
                    Float.parseFloat(leitor.readLine()), Integer.parseInt(leitor.readLine()), Float.parseFloat(leitor.readLine()), leitor.readLine());

            listaLivro.add(livro);

        }
        return listaLivro;

    }

    public void gravarArquivo() throws IOException {

        File arquivo = new File("C:\\Users\\yuri-\\Documents\\NetBeansProjects\\tf_poo_202011\\src\\Arquivos\\livros.txt");
        FileWriter arq = new FileWriter(arquivo);
        try (PrintWriter pw = new PrintWriter(arq)) {
            for (int i = 0; i < listaLivro.size(); i++) {
                pw.println(listaLivro.get(i).getNome());
                pw.println(listaLivro.get(i).getIdLivro());
                pw.println(listaLivro.get(i).getAutor());
                pw.println(listaLivro.get(i).getEditora());
                pw.println(listaLivro.get(i).getEdicao());
                pw.println(listaLivro.get(i).getGenero());
                pw.println(listaLivro.get(i).getAnoLancamento());
                pw.println(listaLivro.get(i).getIdioma());
                pw.println(listaLivro.get(i).getAltura());
                pw.println(listaLivro.get(i).getQtdPaginas());
                pw.println(listaLivro.get(i).getLargura());
                pw.println(listaLivro.get(i).getQtdDisponiveis());
                pw.println(listaLivro.get(i).getPeso());
                pw.println(listaLivro.get(i).getDescricao());

            }
            pw.flush();
            arq.close();
        }

    }

    public String cadastrarLivro(Livro livro) throws IOException {

        listaLivro.add(livro);
        this.gravarArquivo();
        return "Livro cadastrado com sucesso!";
    }

    public String excluirLivro(String nomeRemover) throws IOException {

        for (int i = 0; i < listaLivro.size(); i++) {
            if (listaLivro.get(i).nome.equals(nomeRemover)) {
                listaLivro.remove(i);
            }
        }
        this.gravarArquivo();
        return "Livro excluido";

    }

    public String alterarLivro(Livro livro,ArrayList<Livro> lista) throws IOException {

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equals(livro.getNome())) {
                lista.get(i).setNome(livro.getNome());
                lista.get(i).setIdLivro(livro.getIdLivro());
                lista.get(i).setAutor(livro.getAutor());
                lista.get(i).setEditora(livro.getEditora());
                lista.get(i).setEdicao(livro.getEdicao());
                lista.get(i).setGenero(livro.getGenero());
                lista.get(i).setAnoLancamento(livro.getAnoLancamento());
                lista.get(i).setQtdPaginas(livro.getQtdPaginas());
                lista.get(i).setQtdDisponiveis(livro.getQtdDisponiveis());
                lista.get(i).setAltura(livro.getAltura());
                lista.get(i).setLargura(livro.getLargura());
                lista.get(i).setPeso(livro.getPeso());
                lista.get(i).setDescricao(livro.getDescricao());

            }
        }
        this.gravarArquivo();
        return "Alterado com sucesso!";

    }

    /**
     *
     * @param nome
     * @return
     * @throws IOException
     */
    public Object consultaLivro(String nome, ArrayList<Livro> lista) throws IOException {

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).nome.equals(nome)) {
                return lista.get(i);
            }
        }

        return "Livro não encontrado!";
    }

    public Object listaLivro() throws FileNotFoundException, IOException {

        FileReader arquivo = new FileReader("C:\\Users\\yuri-\\Documents\\NetBeansProjects\\tf_poo_202011\\src\\Arquivos\\livros.txt");
        BufferedReader leitor = new BufferedReader(arquivo);
        String linha;
        ArrayList criandoLista = new ArrayList();
        while ((linha = leitor.readLine()) != null) {

            if (linha != null && !linha.isEmpty()) {

                criandoLista.add(linha);
                criandoLista.add("\n");

            }
        }
        leitor.close();
        StringBuffer texto = new StringBuffer(); //convertendo array p string

        for (Object s : criandoLista) {
            texto.append(s);

        }
        String str = texto.toString();

        return str;

    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getQtdPaginas() {
        return qtdPaginas;
    }

    public void setQtdPaginas(int qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getQtdDisponiveis() {
        return qtdDisponiveis;
    }

    public void setQtdDisponiveis(int qtdDisponiveis) {
        this.qtdDisponiveis = qtdDisponiveis;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public ArrayList<Livro> getListaLivro() {
        return listaLivro;
    }

    public void setListaLivro(ArrayList<Livro> listaLivro) {
        this.listaLivro = listaLivro;
    }

    public Livro(String nome, int idLivro, String autor, String editora, int edicao, String genero, int anoLancamento, String idioma, float altura, int qtdPaginas, float largura, int qtdDisponiveis, float peso, String descricao) {
        this.idLivro = idLivro;
        this.qtdPaginas = qtdPaginas;
        this.anoLancamento = anoLancamento;
        this.qtdDisponiveis = qtdDisponiveis;
        this.edicao = edicao;
        this.altura = altura;
        this.largura = largura;
        this.peso = peso;
        this.nome = nome;
        this.descricao = descricao;
        this.idioma = idioma;
        this.editora = editora;
        this.genero = genero;
        this.autor = autor;
    }

    public Livro() {
    }

    @Override
    public String toString() {
        return String.format("IdLivro: %s\nNome: %s\nAutor: %s\nGenero:"
                + " %s\nIdioma: %s\nQuantidade de Páginas: %d\nEditora: %s\nEdição %d\nQuatidade Disponíveis: %d\n"
                + "Altura: %.0fcm\nLarugra: %.0fcm\nPeso: %.0fg\nAno Lançamento: %d\nDescrição: %s", idLivro, nome, autor, genero, idioma, qtdPaginas, editora,
                edicao, qtdDisponiveis, altura, largura, peso, anoLancamento, descricao);

    }

}
