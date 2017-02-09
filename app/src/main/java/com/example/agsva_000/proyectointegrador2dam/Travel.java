package com.example.agsva_000.proyectointegrador2dam;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by agsva_000 on 17/01/2017.
 */
@IgnoreExtraProperties
public class Travel {

    private String origen;
    private String destino;
    private String fecha;
    private String hora;
    private String precio;
    private String userId;

    public Travel(){}

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
