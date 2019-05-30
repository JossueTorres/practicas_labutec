package com.example.practicaslibres;

public class clase_Edificio {


    private int codigoEdf;
    private String nombre;
    private String acronimo;
    private String estado;
    private String estado_reg;


    public clase_Edificio(){}

    public clase_Edificio(int codigo, String nombre, String acronimo, String estado, String estado_reg){

        this.codigoEdf = codigo;
        this.nombre = nombre;
        this.acronimo = acronimo;
        this.estado = estado;
        this.estado_reg = estado_reg;
    }

    public int getCodigoEdf() {
        return codigoEdf;
    }

    public void setCodigoEdf(int codigoEdf) {
        this.codigoEdf = codigoEdf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado_reg() {
        return estado_reg;
    }

    public void setEstado_reg(String estado_reg) {
        this.estado_reg = estado_reg;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
