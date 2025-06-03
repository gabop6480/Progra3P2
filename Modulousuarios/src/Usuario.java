public class Usuario {
    String id;
    String nombre;
    String correo;
    String rol;
    Usuario izquierda, derecha;

    public Usuario(String id, String nombre, String correo, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }
}
