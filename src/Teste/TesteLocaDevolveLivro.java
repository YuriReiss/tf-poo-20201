/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import Classes.Locador;

/**
 *
 * @author yuri-
 */
public class TesteLocaDevolveLivro {
    
    //@Test
    public void deveriaLocarLivro(){
        Locador locador  = new Locador();
        boolean valorRecebido = locador.locarLivro(locador, "Tosco");
       
      //  assertTrue(valorRecebido);
    }
    
    //@Test
    public void deveriaDevolverLivro(){
        Locador locador  = new Locador();
        boolean valorRecebido = locador.devolveLivro("Tosco");
       
       // assertTrue(valorRecebido);
    }
}
