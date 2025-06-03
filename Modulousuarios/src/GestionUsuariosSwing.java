import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionUsuariosSwing {
    private ArbolUsuarios arbolUsuarios;
    private JFrame frame;
    private JTextField idField, nombreField, correoField;
    private JComboBox<String> rolComboBox;
    private JTextArea displayArea;

    public GestionUsuariosSwing() {
        arbolUsuarios = new ArbolUsuarios();
        inicializarUI();
    }

    private void inicializarUI() {
        frame = new JFrame("Gestión de Usuarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        idField = new JTextField();
        nombreField = new JTextField();
        correoField = new JTextField();
        rolComboBox = new JComboBox<>(new String[]{"Ciudadano", "Reciclador", "Administrador"});

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel("Correo:"));
        inputPanel.add(correoField);
        inputPanel.add(new JLabel("Rol:"));
        inputPanel.add(rolComboBox);

        JButton agregarButton = new JButton("Agregar Usuario");
        JButton buscarButton = new JButton("Buscar Usuario");
        JButton eliminarButton = new JButton("Eliminar Usuario");

        inputPanel.add(agregarButton);
        inputPanel.add(buscarButton);
        inputPanel.add(eliminarButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String nombre = nombreField.getText();
                String correo = correoField.getText();
                String rol = rolComboBox.getSelectedItem().toString();
                arbolUsuarios.insertar(id, nombre, correo, rol);
                displayArea.append("Usuario agregado: " + id + "\n");
                limpiarCampos();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                Usuario usuario = arbolUsuarios.buscar(id);
                if (usuario != null) {
                    displayArea.append("Usuario encontrado: " + usuario.nombre + " (" + usuario.rol + ")\n");
                } else {
                    displayArea.append("Usuario no encontrado.\n");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                arbolUsuarios.eliminar(id);
                displayArea.append("Usuario eliminado: " + id + "\n");
                limpiarCampos();
            }
        });

        frame.setVisible(true);
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        correoField.setText("");
    }

    public static void main(String[] args) {
        new GestionUsuariosSwing();
    }
}