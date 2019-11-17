package com.cesde.momento2retro.models;

public class Cuenta {
    private String id;
    private String numero;
    private String cliente_id;
    private Double saldo;

    public Cuenta() {
    }

    public Cuenta(String id, String numero, String cliente_id, Double saldo) {
        this.id = id;
        this.numero = numero;
        this.cliente_id = cliente_id;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
