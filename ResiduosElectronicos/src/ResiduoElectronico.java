public class ResiduoElectronico {
    private String usuario;
    private String puntoEntrega;
    private String tipoDispositivo;
    private int cantidad;
    private String estado;

    public ResiduoElectronico(String usuario, String puntoEntrega, String tipoDispositivo, int cantidad, String estado) {
        this.usuario = usuario;
        this.puntoEntrega = puntoEntrega;
        this.tipoDispositivo = tipoDispositivo;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public String getUsuario() { return usuario; }
    public String getPuntoEntrega() { return puntoEntrega; }
    public String getTipoDispositivo() { return tipoDispositivo; }
    public int getCantidad() { return cantidad; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ResiduoElectronico)) return false;
        ResiduoElectronico otro = (ResiduoElectronico) obj;
        return this.usuario.equals(otro.usuario) &&
                this.puntoEntrega.equals(otro.puntoEntrega) &&
                this.tipoDispositivo.equals(otro.tipoDispositivo) &&
                this.estado.equals(otro.estado);
    }
}
