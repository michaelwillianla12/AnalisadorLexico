/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael M
 */
public class RemoveBlank {

    ArrayList a = new ArrayList();
    String texto = "";

    void exec(BufferedReader br) {

        String l;

        try {
            while (((l = br.readLine()) != null)) {

                if (l.trim().length() != 0) {
                    texto += l + "\n";
                }

                //função para eliminar comentários simples
                int offset = texto.indexOf("//");

                if (-1 != offset) {
                    texto = texto.substring(0, offset);
                }

            }

            //expressão que elimina todos os comentários. Obs.: nao aceita "*" dentro do comentário
            texto = texto.replaceAll("/\\*([^*]|[\\r\\n])*\\*/", "");

            //remove espaços em brancos
            texto = texto.replaceAll(" ", "");
            texto = texto.trim();

            System.out.println(texto);

        } catch (IOException ex) {
            Logger.getLogger(RemoveBlank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
