package com.avila.cofre.moedas;

import java.text.DecimalFormat;

public class Real extends Moeda {

    public Real(double value) {
        super(value);
    }

    // Método que converte o valor da moeda para o tipo de moeda especificado (Dólar, Euro ou Real)
    @Override public <T extends Moeda> Double converter(Class<T> type) {
        // Verifica se o tipo de moeda é Dólar ou Euro e faz a conversão
        return type.equals(Dolar.class)
                ? getValue() * 0.16 // Converte o valor para Dólar (usando taxa de conversão fictícia)
                : (type.equals(Euro.class))
                ? getValue() * 0.16 // Converte o valor para Euro (usando mesma taxa de conversão fictícia)
                : getValue(); // Se não for Dólar nem Euro, retorna o valor em Real (sem conversão)
    }

    // Método que retorna uma string representando as informações da moeda, incluindo seu ID e valor formatado
    @Override public String info() {
        return "[" + this.ID() + "] Real: R$ " + new DecimalFormat("#,###.00")
                // Formata o valor com separador de milhar e duas casas decimais
                .format(this.getValue())
                .replace(",", "X") // Substitui a vírgula temporariamente para ajustar a formatação
                .replace(".", ",") // Substitui o ponto por vírgula, conforme convenção brasileira
                .replace("X", "."); // Restaura a vírgula para ponto na formatação final
    }


    @Override public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override public int hashCode() {
        return super.hashCode();
    }
}
