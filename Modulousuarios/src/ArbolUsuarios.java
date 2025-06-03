public class ArbolUsuarios {
    private Usuario raiz;

    // Insertar un nuevo usuario
    public void insertar(String id, String nombre, String correo, String rol) {
        raiz = insertarRecursivo(raiz, id, nombre, correo, rol);
    }

    private Usuario insertarRecursivo(Usuario actual, String id, String nombre, String correo, String rol) {
        if (actual == null) {
            return new Usuario(id, nombre, correo, rol);
        }
        if (id.compareTo(actual.id) < 0) {
            actual.izquierda = insertarRecursivo(actual.izquierda, id, nombre, correo, rol);
        } else if (id.compareTo(actual.id) > 0) {
            actual.derecha = insertarRecursivo(actual.derecha, id, nombre, correo, rol);
        }
        return actual;
    }

    // Buscar un usuario por ID
    public Usuario buscar(String id) {
        return buscarRecursivo(raiz, id);
    }

    private Usuario buscarRecursivo(Usuario actual, String id) {
        if (actual == null || actual.id.equals(id)) {
            return actual;
        }
        if (id.compareTo(actual.id) < 0) {
            return buscarRecursivo(actual.izquierda, id);
        }
        return buscarRecursivo(actual.derecha, id);
    }

    // Eliminar un usuario por ID
    public void eliminar(String id) {
        raiz = eliminarRecursivo(raiz, id);
    }

    private Usuario eliminarRecursivo(Usuario actual, String id) {
        if (actual == null) {
            return null;
        }
        if (id.equals(actual.id)) {
            // Nodo sin hijos
            if (actual.izquierda == null && actual.derecha == null) {
                return null;
            }
            // Nodo con un hijo
            if (actual.izquierda == null) {
                return actual.derecha;
            }
            if (actual.derecha == null) {
                return actual.izquierda;
            }
            // Nodo con dos hijos
            String menorValor = encontrarMenor(actual.derecha);
            actual.id = menorValor;
            actual.derecha = eliminarRecursivo(actual.derecha, menorValor);
            return actual;
        }
        if (id.compareTo(actual.id) < 0) {
            actual.izquierda = eliminarRecursivo(actual.izquierda, id);
            return actual;
        }
        actual.derecha = eliminarRecursivo(actual.derecha, id);
        return actual;
    }

    private String encontrarMenor(Usuario actual) {
        return actual.izquierda == null ? actual.id : encontrarMenor(actual.izquierda);
    }
}
