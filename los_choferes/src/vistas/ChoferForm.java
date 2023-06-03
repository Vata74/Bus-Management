package vistas;

import entidades.Chofer;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ChoferForm extends JDialog {

    private JTextField txtNumeroSocio;
    private JTextField txtNombre;
    private JTextField txtApellido;
    
    Integer nroSocio;
    
    Chofer choferGuardar;

    public ChoferForm() {
        initComponents();
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Agregar Chofer");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        Chofer chofer = new Chofer();

        txtNumeroSocio = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();

        panel.add(new JLabel("Número de socio:"));
        panel.add(txtNumeroSocio);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeroSocio = txtNumeroSocio.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                choferGuardar = chofer.validar(numeroSocio, nombre, apellido, nroSocio);
                if (choferGuardar != null) {
                    cerrar();
                } else {
                    JOptionPane.showMessageDialog(null, "Número de socio incorrecto", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        panel.add(new JLabel());
        panel.add(btnAceptar);

        add(panel);
    }
    public Chofer showDialog() {
        setVisible(true);
        return choferGuardar;
    }

    public Chofer showDialog(int nroSocio) {
        setVisible(true);
        this.nroSocio = nroSocio;
        return choferGuardar;
    }
    
    public void cerrar(){
        this.dispose();
    }
}
