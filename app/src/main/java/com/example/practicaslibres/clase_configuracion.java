package com.example.practicaslibres;

import org.json.JSONException;
import org.json.JSONObject;

public class clase_configuracion {

    private String codigo;
    private String laboratorio;
    private String fechaInicio;
    private String fechaFin;
    private String horaInicio;
    private String horaFin;
    private String lun;
    private String mar;
    private String mie;
    private String jue;
    private String vie;
    private String sab;
    private String dom;

    public clase_configuracion(String codigo, String laboratorio, String fechaInicio, String fechaFin, String horaInicio, String horaFin, String lun, String mar, String mie, String jue, String vie, String sab, String dom) {
        this.codigo = codigo;
        this.laboratorio = laboratorio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lun = lun;
        this.mar = mar;
        this.mie = mie;
        this.jue = jue;
        this.vie = vie;
        this.sab = sab;
        this.dom = dom;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getLun() {
        return lun;
    }

    public void setLun(String lun) {
        this.lun = lun;
    }

    public String getMar() {
        return mar;
    }

    public void setMar(String mar) {
        this.mar = mar;
    }

    public String getMie() {
        return mie;
    }

    public void setMie(String mie) {
        this.mie = mie;
    }

    public String getJue() {
        return jue;
    }

    public void setJue(String jue) {
        this.jue = jue;
    }

    public String getVie() {
        return vie;
    }

    public void setVie(String vie) {
        this.vie = vie;
    }

    public String getSab() {
        return sab;
    }

    public void setSab(String sab) {
        this.sab = sab;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }
//ede
    public String toJSON(){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("cod", getCodigo());
            jsonObject.put("lab", getLaboratorio());
            jsonObject.put("fini", getFechaInicio());
            jsonObject.put("hini", getHoraInicio());
            jsonObject.put("ffin", getFechaFin());
            jsonObject.put("hfin", getHoraFin());
            jsonObject.put("l", getLun());
            jsonObject.put("m", getMar());
            jsonObject.put("x", getMie());
            jsonObject.put("j", getJue());
            jsonObject.put("v", getVie());
            jsonObject.put("s", getSab());
            jsonObject.put("d", getDom());



            return jsonObject.toString();
        } catch (JSONException e) {
            //generado
            e.printStackTrace();
            return null;
        }
    }
}
