import javax.swing.*;
import java.util.ArrayList;

public class GestorResiduosForm {
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JComboBox<String> cmbPuntoEntrega;
    private JComboBox<String> cmbTipoDispositivo;
    private JSpinner spnCantidad;
    private JComboBox<String> cmbEstado;
    private JButton btnAgregar;
    private JButton btnConsultar;

    private JButton btnEliminarDuplicados;
    private JTextArea txtAreaHistorial;

    private final ArrayList<ResiduoElectronico> listaResiduos = new ArrayList<>();

    public GestorResiduosForm() {
        // Opciones predefinidas para combos
        cmbEstado.setModel(new DefaultComboBoxModel<>(new String[]{"Reciclado", "Reacondicionado", "Destruido"}));
        cmbPuntoEntrega.setModel(new DefaultComboBoxModel<>(new String[]{"La Carolina", "El Ejido", "Quitumbe", "El Recreo"}));
        cmbTipoDispositivo.setModel(new DefaultComboBoxModel<>(new String[]{"Celular", "Tablet", "Laptop"}));

        btnAgregar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String punto = cmbPuntoEntrega.getSelectedItem().toString();
            String tipo = cmbTipoDispositivo.getSelectedItem().toString();
            int cantidad = (Integer) spnCantidad.getValue();
            String estado = cmbEstado.getSelectedItem().toString();

            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Completa todos los campos.");
                return;
            }

            ResiduoElectronico residuo = new ResiduoElectronico(usuario, punto, tipo, cantidad, estado);
            listaResiduos.add(residuo);
            txtAreaHistorial.append("Registrado: " + tipo + " x" + cantidad + " - " + estado + System.lineSeparator());

        });

        btnConsultar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            txtAreaHistorial.setText("");

            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un nombre de usuario para consultar.");
                return;
            }

            boolean encontrado = false;
            for (ResiduoElectronico r : listaResiduos) {
                if (r.getUsuario().equalsIgnoreCase(usuario)) {
                    String linea = r.getUsuario() + " - " + r.getTipoDispositivo() + " (" +
                            r.getCantidad() + ") - " + r.getEstado() + " - Punto: " + r.getPuntoEntrega();
                    txtAreaHistorial.append(linea + "\n");
                    encontrado = true;
                }
            }

            if (!encontrado) {
                txtAreaHistorial.setText("No se encontraron residuos registrados para el usuario: " + usuario);
            }
        });





        btnEliminarDuplicados.addActionListener(e -> {
            ArrayList<ResiduoElectronico> sinDuplicados = new ArrayList<>();
            for (ResiduoElectronico r : listaResiduos) {
                if (!sinDuplicados.contains(r)) {
                    sinDuplicados.add(r);
                }
            }
            listaResiduos.clear();
            listaResiduos.addAll(sinDuplicados);
            JOptionPane.showMessageDialog(null, "Registros duplicados eliminados.");
        });
    }

    public JPanel getPanel() {
        return panelPrincipal;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Residuos Electrónicos");
        frame.setContentPane(new GestorResiduosForm().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }
}
