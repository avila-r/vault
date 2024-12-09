package com.avila.cofre.moedas;

import java.text.DecimalFormat;

public class Dolar extends Moeda {

    public Dolar(double value) {
        super(value);
    }

    // Método que converte o valor da moeda para o tipo de moeda especificado (Euro, Real ou Dólar)
    @Override public <T extends Moeda> Double converter(Class<T> type) {
        // Verifica se o tipo de moeda é Euro e faz a conversão usando uma taxa fictícia de 0.95
        return type.equals(Euro.class)
                ? getValue() * 0.95 // Converte o valor para Euro (usando a taxa fictícia)
                : (type.equals(Real.class))
                ? getValue() * 6.09 // Converte o valor para Real (usando a taxa fictícia)
                : getValue(); // Se não for Euro nem Real, retorna o valor original (assumindo que seja Dólar)
    }

    // Método que retorna uma string representando as informações da moeda, incluindo seu ID e valor formatado
    @Override public String info() {
        return "[" + this.ID() + "] Dólar: $ " + new DecimalFormat("#,###.00")
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
