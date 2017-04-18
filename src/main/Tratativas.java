/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author Michael M
 */
public class Tratativas {

  

    public boolean seleciona(ArrayList conteudoLinha, int i, StringBuilder sb) {

        //verifica se o conteudo do próximo char é "=" se o char atual for um "=" ou qualquer simbolo que forme uma operacao
        //entao salva o conjunto de caracteres já bufferizados e após itera uma posição
        if (conteudoLinha.get(i + 1).equals('=') && !conteudoLinha.get(i).equals('=') && !conteudoLinha.get(i).equals('!')
                && !conteudoLinha.get(i).equals('<') && !conteudoLinha.get(i).equals('>')) {

            sb.append(conteudoLinha.get(i));
        
         

        }

        //se o char atual é um "=" e os proximos nao forem outro "=" ou um espaço, entao salva a bufferização atual
        if (conteudoLinha.get(i).equals('=') && !conteudoLinha.get(i + 1).equals(' ') && !conteudoLinha.get(i + 1).equals('=')) {

            sb.append(conteudoLinha.get(i));
           
             

        }

        if (conteudoLinha.get(i + 1).equals('*') || conteudoLinha.get(i + 1).equals('/') || conteudoLinha.get(i + 1).equals('+')
                || conteudoLinha.get(i + 1).equals('-')) {

            sb.append(conteudoLinha.get(i));
           
        }

        if ((conteudoLinha.get(i).equals('*') || conteudoLinha.get(i).equals('/') || conteudoLinha.get(i).equals('+')
                || conteudoLinha.get(i).equals('-')) && (!conteudoLinha.get(i + 1).equals(' '))) {

            sb.append(conteudoLinha.get(i));
             
        }

        //se o próximo char a ser lido for um parenteses entao salva a bufferização atual
        if (conteudoLinha.get(i + 1).equals('(')) {
            sb.append(conteudoLinha.get(i));
           

        }
        return false;

    }

}
