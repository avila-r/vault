package com.avila.cofre;

import com.avila.cofre.moedas.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cofrinho {
    // Lista para armazenar todas as moedas no cofrinho
    public final List<Moeda> listaMoedas = new ArrayList<>();

    // Método genérico para adicionar uma moeda do tipo T (que é uma subclasse de Moeda)
    public <T extends Moeda> void adicionar(T coin) {
        listaMoedas.add(coin); // Adiciona a moeda à lista
    }

    // Método para remover uma moeda pela ID
    public void remover(int id) {
        listaMoedas.removeIf(coin -> coin.ID() == id); // Remove a moeda com a ID correspondente
    }

    // Método para listar todas as moedas no cofrinho
    public void listagemMoedas() {
        // Verifica se o cofrinho está vazio
        if (listaMoedas.isEmpty())
            System.out.println("Seu cofrinho está vazio!");

        // Exibe todas as moedas
        System.out.println("Moedas no cofrinho:");
        listaMoedas.forEach(coin -> System.out.println(coin.info()));
    }

    // Método para calcular e exibir o total convertido para o tipo de moeda fornecido
    public void totalConvertido(Class<? extends Moeda> type) {
        // Soma o valor de todas as moedas convertidas para o tipo desejado (Real, Dólar, Euro)
        double total = listaMoedas.stream()
                .mapToDouble(coin -> coin.converter(type)) // Converte cada moeda para o tipo especificado
                .sum(); // Soma os valores

        // Exibe o total convertido no formato correto (R$, $, €)
        System.out.println("Total convertido: " +
                ((type.equals(Real.class)) ? "R$ " : (type.equals(Dolar.class)) ? "$ " : "€ ") +
                new DecimalFormat("#,###.00")
                        .format(total)
                        .replace(",", "X")
                        .replace(".", ",")
                        .replace("X", ".")
        );
    }
}
