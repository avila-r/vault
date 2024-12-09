package com.avila.cofre.moedas;

import java.util.Objects;

public abstract class Moeda {

    // Incrementador para gerar um ID único para cada instância de Moeda.
    private static int next = 1;

    // O ID da moeda, útil para removê-las posteriormente.
    private final int id;

    private final double value;

    public Moeda(double value) {
        this.id = next++; // Atribui o ID e incrementa a variável 'next', tornando todos os IDs únicos.
        this.value = value;
    }

    public int ID() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public abstract String info();

    public abstract <T extends Moeda> Double converter(Class<T> type);

    @Override public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override public boolean equals(Object object) {
        return object.equals(this) || (
                object instanceof Moeda && 0 == Double.compare (
                        ((Moeda) object).getValue(), getValue()
                )
        );
    }

    @Override public String toString() {
        return "Moeda{id=" + id + ", value=" + value + "}";
    }
}
