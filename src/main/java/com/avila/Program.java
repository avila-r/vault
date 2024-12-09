package com.avila;

import com.avila.cofre.Cofrinho;
import com.avila.cofre.moedas.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Program {

    private final Scanner scanner = new Scanner(System.in);

    private final Cofrinho cofrinho = new Cofrinho();

    // Método principal para iniciar o programa
    public static void main(String[] args) {
        new Program().menu(); // Chama o menu ao iniciar
    }

    // Exibe o menu de opções principais
    public void menu(){
        int option = Int("""
            ╭────────────────────────────────╮
            │    \u001B[34mCofrinho - Renato Ávila\u001B[0m     │
            ├────────────────────────────────┤
            │   \u001B[32m1 - Adicionar moeda\u001B[0m          │
            │   \u001B[33m2 - Remover moeda\u001B[0m            │
            │   \u001B[36m3 - Listar moedas\u001B[0m            │
            │   \u001B[35m4 - Total convertido\u001B[0m         │
            │   \u001B[31m5 - Sair\u001B[0m                     │
            ╰────────────────────────────────╯
            >>\s""");

        operate(option); // Chama o método de operação baseado na opção
    }

    // Realiza a operação com base na opção selecionada
    private void operate(int option){
        switch (option) {
            case 1:
                adicionar(); // Adicionar moeda
                break;
            case 2:
                remover(); // Remover moeda
                break;
            case 3:
                cofrinho.listagemMoedas(); // Exibir lista de moedas
                retryToMenu(); // Voltar ao menu
                break;
            case 4:
                total(); // Exibir o total convertido
                break;
            case 5:
                System.exit(0); // Fechar o programa
            default:
                System.out.println("Opção inválida."); // Caso a opção seja inválida
                menu(); // Exibir o menu novamente
                break;
        }
    }

    // Método para adicionar uma moeda ao cofrinho
    private void adicionar(){
        int type = Int("""
        ╭─────────────────────────────────────────╮
        │       \u001B[34mQual moeda deseja adicionar?\u001B[0m      │
        ├─────────────────────────────────────────┤
        │   \u001B[32m1 - Real\u001B[0m                              │
        │   \u001B[33m2 - Euro\u001B[0m                              │
        │   \u001B[36m3 - Dólar\u001B[0m                             │
        │                                         │
        │   \u001B[35m0 - Voltar ao menu\u001B[0m                    │
        ╰─────────────────────────────────────────╯
        >>\s""");

        boolean isValid = Stream.of(0, 1, 2, 3)
                .anyMatch(n -> n == type); // Verifica se a opção é válida

        if (!isValid) {
            System.out.println("Opção inválida!"); // Caso inválido
            retryLabel(this::adicionar); // Retenta adicionar moeda
            return;
        }

        if (type == 0) {
            menu(); // Volta ao menu
            return;
        }

        double value = Double("Qual o valor a ser adicionado?\n>> "); // Solicita o valor da moeda

        // Adiciona a moeda ao cofrinho de acordo com o tipo
        cofrinho.adicionar ((type == 1)
                        ? new Real(value)
                        : ((type == 2)
                        ? new Euro(value)
                        : new Dolar(value)
                )
        );

        // Exibe mensagem de sucesso e retorna ao menu
        retryLabel(
                "\u001B[32mMoeda adicionada com sucesso!",
                this::adicionar
        );
    }

    // Método para remover uma moeda do cofrinho
    private void remover(){
        if (cofrinho.listaMoedas.isEmpty()) { // Verifica se o cofrinho está vazio
            System.out.println("Seu cofrinho está vazio!"); // Caso esteja
            menu(); // Volta ao menu
            return;
        }

        // Exibe lista de moedas no cofrinho
        System.out.println("""
        ╭─────────────────────────────────────────╮
        │       \u001B[34mQual moeda deseja remover?\u001B[0m        │
        ╰─────────────────────────────────────────╯
        """);
        cofrinho.listagemMoedas(); // Mostra as moedas
        System.out.println("---\n[0] Voltar para o menu\n");

        // Solicita o identificador da moeda a ser removida
        int id = Int("Qual o identificador da moeda a ser removida?\n>> ");

        if (id == 0) {
            menu(); // Volta ao menu
            return;
        }

        cofrinho.remover(id); // Remove a moeda com o ID informado

        retryLabel (
                "\u001B[32mMoeda removida com sucesso!",
                this::remover
        );
    }

    // Método para exibir o total convertido em uma moeda específica
    private void total(){
        System.out.print("""
            ╭─────────────────────────────────────────╮
            │ \u001B[34mEm qual moeda deseja ver o valor total?\u001B[0m │
            ├─────────────────────────────────────────┤
            │   \u001B[32m1 - Real\u001B[0m                              │
            │   \u001B[33m2 - Euro\u001B[0m                              │
            │   \u001B[36m3 - Dólar\u001B[0m                             │
            │                                         │
            │   \u001B[35m0 - Voltar ao menu\u001B[0m                    │
            ╰─────────────────────────────────────────╯
            >>\s""");

        switch (scanner.nextInt()) { // Escolhe a moeda
            case 1 -> cofrinho.totalConvertido(Real.class); // Total em Real
            case 2 -> cofrinho.totalConvertido(Euro.class); // Total em Euro
            case 3 -> cofrinho.totalConvertido(Dolar.class); // Total em Dólar
            case 0 -> menu(); // Volta ao menu
            default -> System.out.println("Opção inválida."); // Caso inválido
        }

        retryLabel(this::total); // Pergunta se deseja repetir a operação
    }

    // Exibe a opção de repetir a ação ou voltar ao menu
    private void retryLabel(String message, Runnable method) {
        System.out.println(message); // Exibe a mensagem de sucesso
        retryLabel(method); // Pergunta se deseja repetir a ação
    }

    // Método para perguntar se o usuário deseja repetir a ação
    private void retryLabel(Runnable method) {
        int option = Int("""
        \u001B[0m╭─────────────────────────────────────────╮
        │ \u001B[34m            Quer repetir?\u001B[0m               │
        ├─────────────────────────────────────────┤
        │   \u001B[32m1 - Sim!\u001B[0m                              │
        │   \u001B[33m2 - Volte para o menu\u001B[0m                 │
        ╰─────────────────────────────────────────╯
        >>\s""");

        switch (option) {
            case 1 -> method.run(); // Repete a ação
            case 2 -> menu(); // Volta ao menu
            default -> retryLabel(method); // Caso inválido, repete
        }
    }

    // Método para voltar ao menu
    private void retryToMenu() {
        System.out.println("""
        \u001B[0m╭─────────────────────────────────────────╮
        │   \u001B[33m0 - Volte para o menu\u001B[0m                 │
        ╰─────────────────────────────────────────╯
        >>\s""");

        scanner.next(); // Lê a entrada
        scanner.nextLine(); // Limpa o buffer
        menu(); // Volta ao menu
    }

    // Método para ler um inteiro do usuário
    private int Int(String message) {
        int value;

        try {
            System.out.print(message); // Exibe a mensagem
            value = scanner.nextInt(); // Lê o valor inteiro
            scanner.nextLine(); // Limpa o buffer
        } catch (InputMismatchException e) {
            System.out.println("Valor inválido. Tente novamente."); // Caso erro na leitura
            scanner.nextLine(); // Limpa o buffer
            value = Int("\n>> "); // Tenta ler novamente
        }

        return value;
    }

    // Método para ler um valor decimal (double) do usuário
    private double Double(String message) {
        System.out.print(message); // Exibe a mensagem
        String value = scanner.nextLine()
                .replace(',', '.'); // Substitui vírgula por ponto para compatibilidade com a entrada

        double result;

        try {
            result = Double.parseDouble(value); // Tenta converter para double
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Tente novamente."); // Caso erro na conversão
            result = Double("\n>> "); // Tenta ler novamente
        }

        return result;
    }

}
