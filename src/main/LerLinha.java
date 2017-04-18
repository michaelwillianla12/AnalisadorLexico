/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael M
 */
public class LerLinha {

    private static String linha;
    private static ArrayList pr = new ArrayList();

    private final ArrayList conteudoLinha = new ArrayList();

    BufferedReader br, original;

    public void lerLinha() throws FileNotFoundException, IOException {

        pr = carregaListaReservada();
        Main main = new Main(pr);
        RemoveBlank rb = new RemoveBlank();

        //Lê arquivo original e chama método "prepara" para eliminar os comentários, em seguida começa o processo de análise 
        original = new BufferedReader(new FileReader("C:\\Users\\Michael M\\Documents\\NetBeansProjects\\AnalisadorLexico\\src\\main\\original.txt"));
        prepara(original);

        //Este arquivo já está sem os comentários
        br = new BufferedReader(new FileReader("C:\\Users\\Michael M\\Documents\\NetBeansProjects\\AnalisadorLexico\\src\\main\\semcoment.txt"));

        Scanner scan = new Scanner(System.in);
        System.out.println("Digite 1 para analizador léximo ou digite 2 para visualizar formatação sem comentários e espaços: ");
        int ent = scan.nextInt();

        switch (ent) {
            case 1:

                while (br.ready()) {

                    linha = br.readLine();

                    for (int i = 0; i < linha.length(); i++) {

                        conteudoLinha.add(linha.charAt(i));

                    }

                    main.exec(conteudoLinha);
                    conteudoLinha.clear();
                }

                break;

            case 2:

                rb.exec(br);
                
                break;

            default:
                System.out.println("Entrada inválida");
        }

    }

    //REMOVE COMENTÁRIOS APENAS PARA PREPARAR ARQUIVO P/ ANÁLISE LÉXICA
    public void prepara(BufferedReader br) {

        String code = "";

        try {
            while (br.ready()) {

                code += br.readLine() + "\n";

                int offset = code.indexOf("//");

                if (-1 != offset) {
                    code = code.substring(0, offset);
                }

            }

            //nao aceita "*" dentro do comentário
            code = code.replaceAll("/\\*([^*]|[\\r\\n])*\\*/", "");

            FileWriter fw = new FileWriter("C:\\Users\\Michael M\\Documents\\NetBeansProjects\\AnalisadorLexico\\src\\main\\semcoment.txt");
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(code);
                bw.flush();
                bw.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(RemoveBlank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //CARREGA PALAVRAS RESERVADAS EM UM ARRAY
    public static <String> ArrayList carregaListaReservada() {

        try {
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Michael M\\Documents\\NetBeansProjects\\AnalisadorLexico\\src\\main\\reservadas.txt"))) {
                while (br.ready()) {
                    String palavraReservada = (String) br.readLine();
                    pr.add(palavraReservada);

                }
            }

        } catch (IOException ioe) {
        }
        return pr;
    }
}
