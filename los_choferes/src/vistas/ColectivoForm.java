package vistas;

import entidades.Colectivo;
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

public class ColectivoForm extends JDialog {

    private JTextField txtModelo;
    private JTextField txtPatente;
    private JTextField txtCapPasajeros;
    
    String patenteVal;
    
    Colectivo colectivoGuardar;

    public ColectivoForm() {
        initComponents();
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Agregar colectivo");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        Colectivo colectivo = new Colectivo();

        txtModelo = new JTextField();
        txtPatente = new JTextField();
        txtCapPasajeros = new JTextField();

        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Patente:"));
        panel.add(txtPatente);
        panel.add(new JLabel("Capacidad pasajeros:"));
        panel.add(txtCapPasajeros);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String modelo = txtModelo.getText();
                String patente = txtPatente.getText();
                String capacidadPasajeros = txtCapPasajeros.getText();
                colectivoGuardar = colectivo.validar(modelo, patente, capacidadPasajeros, patenteVal);
                if (colectivoGuardar != null) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de pasajeros incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        panel.add(new JLabel());
        panel.add(btnAceptar);

        add(panel);
    }
    public Colectivo showDialog() {
        setVisible(true);
        return colectivoGuardar;
    }
    
    public Colectivo showDialog(String patente) {
        setVisible(true);
        patenteVal = patente;
        return colectivoGuardar;
    }
}
