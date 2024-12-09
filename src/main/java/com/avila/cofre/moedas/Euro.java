package com.avila.cofre.moedas;

import java.text.DecimalFormat;

public class Euro extends Moeda {

    public Euro(double value) {
        super(value);
    }

    // Método que converte o valor da moeda para o tipo de moeda especificado (Dólar, Real ou Euro)
    @Override public <T extends Moeda> Double converter(Class<T> type) {
        // Verifica se o tipo de moeda é Dólar e faz a conversão usando uma taxa fictícia de 1.06
        return type.equals(Dolar.class)
                ? getValue() * 1.06 // Converte o valor para Dólar (usando a taxa fictícia)
                : (type.equals(Real.class))
                ? getValue() * 6.36 // Converte o valor para Real (usando a taxa fictícia)
                : getValue(); // Se não for Dólar nem Real, retorna o valor original (assumindo que seja Euro)
    }

    // Método que retorna uma string representando as informações da moeda, incluindo seu ID e valor formatado
    @Override public String info() {
        return "[" + this.ID() + "] Euro: € " + new DecimalFormat("#,###.00")
                // Formata o valor com separador de milhar e duas casas decimais
                .format(this.getValue())
                .replace(",", "X") // Substitui a vírgula temporariamente para ajustar a formatação
                .replace(".", ",") // Substitui o ponto por vírgula, conforme convenção europeia para valores monetários
                .replace("X", "."); // Restaura a vírgula para ponto na formatação final
    }


    @Override public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override public int hashCode() {
        return super.hashCode();
    }
}
