
package Classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public abstract class Pessoa {
    private String numRegistro;
    private String nome;
    private Date dataNascimento;
    private String email;

    public Pessoa(){
        
    }
    
    public Pessoa(String numRegistro, String nome, Date dataNascimento, String email) {
        this.nome = nome;
        this.numRegistro = numRegistro;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public String getNumRegistro() {
        return this.numRegistro;
    }

    public void setNumRegistro(String numRegistro) {
        this.numRegistro = numRegistro;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;

    }


    @Override
    public String toString() {
        return "{" +
            " numRegistro='" + getNumRegistro() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataNascimento='" + getDataNascimento() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }

}