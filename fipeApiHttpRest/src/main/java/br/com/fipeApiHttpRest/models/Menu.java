package br.com.fipeApiHttpRest.models;

import java.util.Scanner;

public class Menu {

    public static int opt = 0;
    public static Scanner leitura = new Scanner(System.in);

    public static void mostraMenu(){
        System.out.println("\u001B[36m--------------------------------------------------\nTABELA FIPE VERSAO LINHA DE COMANDO\n--------------------------------------------------\u001B[0m");
        System.out.println("**** Opções ****");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        System.out.println("3 - Caminhão");
        System.out.println("4 - Sair");
    }

    public static void opcaoMenu() {

        if (opt != 4){
            mostraMenu();
            System.out.print("\nDigite uma opção --> ");
        }

        while (opt != 4){
            opt = leitura.nextInt();
            switch (opt) {
                case 1:
                    System.out.println("Opção 1 selecionada: Carro.");
                    break;
                case 2:
                    System.out.println("Opção 2 selecionada: Moto.");
                    break;
                case 3:
                    System.out.println("Opção 3 selecionada: Caminhão.");
                    break;
                case 4:
                    System.out.println("Opção 4 selecionada: Sair.");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, selecione 1, 2, 3 ou 4.");
                    break;
            }
            opcaoMenu();
        }
    }
}
