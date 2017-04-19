/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael M
 */
public class Main {

    private String pa;
    private final ArrayList<String> aPalavras = new ArrayList<>();
    private final ArrayList<String> pr;
    private int id = 0;
    private final StringBuilder sb = new StringBuilder();
    private final List<Elemento> tokens = new ArrayList();

    public Main(ArrayList<String> pr) {
        // recebe os arrays de palavras reservadas e salva em um arrayList de Strings para ser usado na classe
        this.pr = pr;

    }

    public void exec(ArrayList conteudoLinha) {

        //percorre cada caracter da linha
        for (int i = 0; i < conteudoLinha.size(); i++) {

            if (!conteudoLinha.get(i).equals(' ') && i != conteudoLinha.size() - 1) {

                //verifica se o conteudo do próximo char é "=" se o char atual for um "=" ou qualquer simbolo que forme uma operacao
                //entao salva o conjunto de caracteres já bufferizados e após itera uma posição
                if (conteudoLinha.get(i + 1).equals('=') && !conteudoLinha.get(i).equals('=') && !conteudoLinha.get(i).equals('!')
                        && !conteudoLinha.get(i).equals('<') && !conteudoLinha.get(i).equals('>')) {

                    sb.append(conteudoLinha.get(i));
                    alim();
                    i++;

                }

                //se o char atual é um "=" e os proximos nao forem outro "=" ou um espaço, entao salva a bufferização atual
                if (conteudoLinha.get(i).equals('=') && !conteudoLinha.get(i + 1).equals(' ') && !conteudoLinha.get(i + 1).equals('=')) {

                    sb.append(conteudoLinha.get(i));
                    alim();
                    i++;
                }

                if (conteudoLinha.get(i).equals('>') && !conteudoLinha.get(i + 1).equals(' ') && !conteudoLinha.get(i + 1).equals('=')) {

                    sb.append(conteudoLinha.get(i));
                    alim();
                    i++;
                }

                if (conteudoLinha.get(i + 1).equals('*') || conteudoLinha.get(i + 1).equals('/') || conteudoLinha.get(i + 1).equals('+')
                        || conteudoLinha.get(i + 1).equals('-')) {

                    sb.append(conteudoLinha.get(i));
                    alim();
                    i++;
                }

                if ((conteudoLinha.get(i).equals('*') || conteudoLinha.get(i).equals('/') || conteudoLinha.get(i).equals('+')
                        || conteudoLinha.get(i).equals('-')) && (!conteudoLinha.get(i + 1).equals(' '))) {

                    sb.append(conteudoLinha.get(i));
                    alim();
                    i++;
                }

                //se o próximo char a ser lido for um parenteses entao salva a bufferização atual
                if (conteudoLinha.get(i + 1).equals('(')) {

                    sb.append(conteudoLinha.get(i));
                    alim();
                    i++;
                }

                //se o conteudo atual for um parenteses, itera
                if (conteudoLinha.get(i).equals('(') || conteudoLinha.get(i).equals(')')) {

                    i++;
                }

            }
            sb.append(conteudoLinha.get(i));

            //separa conteúdo por espaços ou "nova linha" ou chaves
            if (conteudoLinha.get(i).equals(' ') || conteudoLinha.get(i).equals(';') || conteudoLinha.get(i).equals('{')
                    || conteudoLinha.get(i).equals('}')) {

                // deleta o espaço ou o ; para verificar o que vem antes
                sb.deleteCharAt(sb.length() - 1);

                alim();
            }
        }
       // showPalavras(aPalavras);
     //  System.out.println("Pares <token,lexema> \n"+ tokens);
        aPalavras.clear();

    }

    public void alim() {

        Elemento e = new Elemento();

        // adiciona o conteúdo concatenado na string "pa"
        pa = sb.toString();

        boolean x = verPalavraReservada(pa, pr,e);
        if (x == false) {

            if (pa.equals("<") || pa.equals("<=") || pa.equals("==") || pa.equals("!=") || pa.equals(">=") || pa.equals(">")) {
                aPalavras.add("[​Relational_Op, " + pa + "]");
                e.setToken("Relational_Op");
                e.setLexema(pa);
                tokens.add(e);
            } else if (pa.matches("^[0-9]*$") && (!pa.isEmpty())) {

                aPalavras.add("[num, " + pa + "]");
                e.setToken("Num");
                e.setLexema(pa);
                tokens.add(e);
            } else if (pa.equals("=")) {
                aPalavras.add("[​Equal_Op, " + pa + "]");
                e.setToken("Equal_Op");
                e.setLexema(pa);
                tokens.add(e);

            } else if (pa.equals("*") || pa.equals("+") || pa.equals("/") || pa.equals("-")) {
                aPalavras.add("[​Arith_​Op, " + pa + "]");
                e.setToken("Arith_​Op");
                e.setLexema(pa);
                tokens.add(e);
            } else if (!pa.isEmpty()) {

                if (pa.contains(".")) {
                    aPalavras.add("[​num, " + pa + "]");
                    e.setToken("Num");
                    e.setLexema(pa);
                    tokens.add(e);

                } else {
                    id++;
                    aPalavras.add("[​id, " + id + "]");
                    e.setToken("id");
                    e.setLexema((String.valueOf(id)));
                    tokens.add(e);
                }
            }
        }

        //limpa o buffer
        sb.delete(0, sb.length());

    }

    private boolean verPalavraReservada(String palavra, ArrayList<String> pr, Elemento e) {

        //laço de repetição para verificar se a palavra recebida  por parametro é igual a alguma palavra reservada, caso contrário retorna false
        for (int i = 0; i < pr.size(); i++) {

            if (palavra.equals(pr.get(i))) {
                aPalavras.add("[reserved_word, " + palavra + "]");
                  e.setToken("reserved_word");
                    e.setLexema(palavra);
                    tokens.add(e);
                return true;
            }

        }
        return false;
    }

    //MÉTODO DE SAÍDA "toString()"
    public void showPalavras(ArrayList<String> pal) {

        for (int i = 0; i < pal.size(); i++) {

            System.out.print(pal.get(i));

            if (pal.size() - 1 == i) {
                System.out.println();
            }

        }

    }
    
     public List<Elemento> getTokens(){
        return tokens;
    }

}
