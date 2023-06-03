/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import entidades.Chofer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import vistas.ChoferForm;
import vistas.ColectivoForm;
import vistas.PanelChofer;
import vistas.PanelColectivo;

/**
 *
 * @author lucaz
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    ImageIcon logoColectivo1;
    ImageIcon logoColectivo2;

    ImageIcon logoValidacion1;
    ImageIcon logoValidacion2;

    String archivoColectivo = "persistencia/colectivos.csv";
    String archivoChoferes = "persistencia/choferes.csv";

    public VistaPrincipal() {
        initComponents();
        mostrarInfoChofer();
        actualizarIconosMicros();
        actualizarIconosValidaciones(true, false);
        inicioPrimeraVez();

        this.setLocationRelativeTo(null);
    }

    public void inicioPrimeraVez() {
        System.out.println(contarFilas(archivoColectivo));

        if (contarFilas(archivoColectivo) < 2 && contarFilas(archivoChoferes) < 2) {
            ChoferForm dialogChofer = new ChoferForm();
            ColectivoForm dialogColectivo = new ColectivoForm();

            Chofer chofer = dialogChofer.showDialog();
            chofer.setColectivo(dialogColectivo.showDialog());
            System.out.println(chofer.getColectivo());

            Chofer chofer1 = dialogChofer.showDialog(chofer.getNroSocio());
            chofer1.setColectivo(dialogColectivo.showDialog(chofer.getColectivo().getPatente()));
            System.out.println(chofer1);

            guardarDatos(chofer, chofer1);
        }

        if (contarFilas(archivoColectivo) != 2 && contarFilas(archivoChoferes) != 2) {
            System.exit(0);
        }
    }

    public void guardarDatos(Chofer chofer1, Chofer chofer2) {
    ArrayList<String[]> choferesGuardar = new ArrayList<>();
    ArrayList<String[]> colectivosGuardar = new ArrayList<>();

    // Guardar choferes
    String[] lineaChofer1 = {String.valueOf(chofer1.getNroSocio()), chofer1.getNombre(), chofer1.getApellido()};
    String[] lineaChofer2 = {String.valueOf(chofer2.getNroSocio()), chofer2.getNombre(), chofer2.getApellido()};
    choferesGuardar.add(lineaChofer1);
    choferesGuardar.add(lineaChofer2);

    // Guardar colectivos
    String[] lineaColectivo1 = {String.valueOf(chofer1.getColectivo().getKilometraje()), String.valueOf(chofer1.getColectivo().getCantidadPasajeros()), chofer1.getColectivo().getModelo(), chofer1.getColectivo().getPatente()};
    String[] lineaColectivo2 = {String.valueOf(chofer2.getColectivo().getKilometraje()), String.valueOf(chofer2.getColectivo().getCantidadPasajeros()), chofer2.getColectivo().getModelo(), chofer2.getColectivo().getPatente()};
    colectivosGuardar.add(lineaColectivo1);
    colectivosGuardar.add(lineaColectivo2);

    guardarCSV(archivoColectivo, colectivosGuardar);
    guardarCSV(archivoChoferes, choferesGuardar);
}

public static void guardarCSV(String rutaArchivo, ArrayList<String[]> datos) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
        for (String[] fila : datos) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fila.length; i++) {
                sb.append(fila[i]);
                if (i < fila.length - 1) {
                    sb.append(",");
                }
            }
            writer.write(sb.toString());
            writer.newLine();
        }
        System.out.println("Archivo CSV guardado correctamente en: " + rutaArchivo);
    } catch (IOException e) {
        System.err.println("Error al guardar el archivo CSV: " + e.getMessage());
    }
}

    private static int contarFilas(String filename) {
        int rowCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Ignorar la primera línea que generalmente contiene los encabezados
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                rowCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    public void actualizarIconosValidaciones(boolean validacion1, boolean validacion2) {
        if (validacion1) {
            this.logoValidacion1 = new ImageIcon("imagenes/Ok.png");
        } else {
            this.logoValidacion1 = new ImageIcon("imagenes/NotOk.png");
        }
        this.logoValidacion1 = new ImageIcon(this.logoValidacion1.getImage().getScaledInstance(LogoValidacion1.getWidth(), LogoValidacion1.getHeight(), Image.SCALE_DEFAULT));
        LogoValidacion1.setIcon(logoValidacion1);
        if (validacion2) {
            this.logoValidacion2 = new ImageIcon("imagenes/Ok.png");
        } else {
            this.logoValidacion2 = new ImageIcon("imagenes/NotOk.png");
        }
        this.logoValidacion2 = new ImageIcon(this.logoValidacion2.getImage().getScaledInstance(LogoValidacion2.getWidth(), LogoValidacion2.getHeight(), Image.SCALE_DEFAULT));
        LogoValidacion2.setIcon(logoValidacion2);
        this.repaint();
    }

    public void actualizarIconosMicros() {
        //logo Colectivo 1
        this.logoColectivo1 = new ImageIcon("imagenes/Colectivo1.png");
        this.logoColectivo1 = new ImageIcon(this.logoColectivo1.getImage().getScaledInstance(BtnColectivoChofer1.getWidth(), BtnColectivoChofer1.getHeight(), Image.SCALE_DEFAULT));
        BtnColectivoChofer1.setIcon(this.logoColectivo1);
        //logo Colectivo 2
        this.logoColectivo2 = new ImageIcon("imagenes/Colectivo2.png");
        this.logoColectivo2 = new ImageIcon(this.logoColectivo2.getImage().getScaledInstance(BtnColectivoChofer2.getWidth(), BtnColectivoChofer2.getHeight(), Image.SCALE_DEFAULT));
        BtnColectivoChofer2.setIcon(this.logoColectivo2);
        this.repaint();
    }

    public void mostrarInfoChofer() {
        PanelChofer mostrarDataChofer = new PanelChofer();
        mostrarDataChofer.setSize(950, 590);
        mostrarDataChofer.setLocation(0, 0);

        PanelContenido.removeAll();
        PanelContenido.add(mostrarDataChofer, BorderLayout.CENTER);
        PanelContenido.revalidate();
        PanelContenido.repaint();
    }

    public void mostrarInfoColectivo() {
        PanelColectivo mostrarDataColectivo = new PanelColectivo();
        mostrarDataColectivo.setSize(950, 590);
        mostrarDataColectivo.setLocation(0, 0);

        PanelContenido.removeAll();
        PanelContenido.add(mostrarDataColectivo, BorderLayout.CENTER);
        PanelContenido.revalidate();
        PanelContenido.repaint();
    }

    private void mouseEnter(JPanel panel) {
        Color currentColor = panel.getBackground();
        float[] hsb = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null);
        float brightness = hsb[2];
        float newBrightness = Math.min(brightness + 0.1f, 1.0f);
        Color newColor = Color.getHSBColor(hsb[0], hsb[1], newBrightness);
        panel.setBackground(newColor);
    }

    private void mouseExit(JPanel panel) {
        Color currentColor = panel.getBackground();
        float[] hsb = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null);
        float brightness = hsb[2];
        float newBrightness = Math.max(brightness - 0.1f, 0.0f);
        Color newColor = Color.getHSBColor(hsb[0], hsb[1], newBrightness);
        panel.setBackground(newColor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        PanelIzquierdo = new javax.swing.JPanel();
        LblTitulo = new javax.swing.JLabel("Los Choferes", SwingConstants.CENTER);
        PanelChofer2 = new javax.swing.JPanel();
        BtnSelecChofer2 = new javax.swing.JLabel();
        PanelColectivo1 = new javax.swing.JPanel();
        BtnColectivoChofer1 = new javax.swing.JLabel();
        PanelColectivo2 = new javax.swing.JPanel();
        BtnColectivoChofer2 = new javax.swing.JLabel();
        PanelChofer1 = new javax.swing.JPanel();
        BtnSelecChofer1 = new javax.swing.JLabel();
        PanelBtnMaximoKm = new javax.swing.JPanel();
        BtnMaximoKm = new javax.swing.JLabel("Maximo Kilometraje", SwingConstants.CENTER);
        PanelBtnIntercabio = new javax.swing.JPanel();
        BtnIntercambiar = new javax.swing.JLabel("Intercambio", SwingConstants.CENTER);
        PanelSalir = new javax.swing.JPanel();
        BtnSalir = new javax.swing.JLabel("Salir", SwingConstants.CENTER);
        PanelDerechoSuperior = new javax.swing.JPanel();
        LblInformacion = new javax.swing.JLabel("Información", SwingConstants.CENTER);
        PanelContenido = new javax.swing.JPanel();
        PanelDerechoInferior = new javax.swing.JPanel();
        LblCantidadPasajeros = new javax.swing.JLabel();
        LblKilometraje = new javax.swing.JLabel();
        SpinnerCantidadPasajeros = new javax.swing.JSpinner();
        TxtKilometraje = new javax.swing.JTextField();
        LogoValidacion1 = new javax.swing.JLabel();
        LogoValidacion2 = new javax.swing.JLabel();
        PanelBtnRealizarViaje = new javax.swing.JPanel();
        BtnRealizarViaje = new javax.swing.JLabel("Realizar Viaje", SwingConstants.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        PanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelIzquierdo.setBackground(new java.awt.Color(31, 54, 61));
        PanelIzquierdo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        PanelIzquierdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblTitulo.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LblTitulo.setText("Los Choferes");
        PanelIzquierdo.add(LblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 246, 53));

        PanelChofer2.setBackground(new java.awt.Color(112, 169, 161));

        BtnSelecChofer2.setForeground(new java.awt.Color(255, 255, 255));
        BtnSelecChofer2.setText("Chofer 2");
        BtnSelecChofer2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSelecChofer2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnSelecChofer2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnSelecChofer2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelChofer2Layout = new javax.swing.GroupLayout(PanelChofer2);
        PanelChofer2.setLayout(PanelChofer2Layout);
        PanelChofer2Layout.setHorizontalGroup(
            PanelChofer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChofer2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnSelecChofer2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelChofer2Layout.setVerticalGroup(
            PanelChofer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChofer2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnSelecChofer2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelIzquierdo.add(PanelChofer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 170, 50));

        PanelColectivo1.setBackground(new java.awt.Color(255, 255, 255));

        BtnColectivoChofer1.setText("logo1");
        BtnColectivoChofer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnColectivoChofer1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelColectivo1Layout = new javax.swing.GroupLayout(PanelColectivo1);
        PanelColectivo1.setLayout(PanelColectivo1Layout);
        PanelColectivo1Layout.setHorizontalGroup(
            PanelColectivo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelColectivo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnColectivoChofer1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelColectivo1Layout.setVerticalGroup(
            PanelColectivo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelColectivo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnColectivoChofer1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelIzquierdo.add(PanelColectivo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 50, 50));

        PanelColectivo2.setBackground(new java.awt.Color(255, 255, 255));

        BtnColectivoChofer2.setText("logo2");
        BtnColectivoChofer2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnColectivoChofer2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelColectivo2Layout = new javax.swing.GroupLayout(PanelColectivo2);
        PanelColectivo2.setLayout(PanelColectivo2Layout);
        PanelColectivo2Layout.setHorizontalGroup(
            PanelColectivo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelColectivo2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnColectivoChofer2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelColectivo2Layout.setVerticalGroup(
            PanelColectivo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelColectivo2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnColectivoChofer2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelIzquierdo.add(PanelColectivo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 50, -1));

        PanelChofer1.setBackground(new java.awt.Color(112, 169, 161));

        BtnSelecChofer1.setForeground(new java.awt.Color(255, 255, 255));
        BtnSelecChofer1.setText("Chofer 1");
        BtnSelecChofer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSelecChofer1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnSelecChofer1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnSelecChofer1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelChofer1Layout = new javax.swing.GroupLayout(PanelChofer1);
        PanelChofer1.setLayout(PanelChofer1Layout);
        PanelChofer1Layout.setHorizontalGroup(
            PanelChofer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChofer1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnSelecChofer1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelChofer1Layout.setVerticalGroup(
            PanelChofer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChofer1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnSelecChofer1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelIzquierdo.add(PanelChofer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, 50));

        PanelBtnMaximoKm.setBackground(new java.awt.Color(158, 193, 163));

        BtnMaximoKm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnMaximoKm.setForeground(new java.awt.Color(255, 255, 255));
        BtnMaximoKm.setText("Maximo Kilometraje");
        BtnMaximoKm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnMaximoKmMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnMaximoKmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnMaximoKmMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelBtnMaximoKmLayout = new javax.swing.GroupLayout(PanelBtnMaximoKm);
        PanelBtnMaximoKm.setLayout(PanelBtnMaximoKmLayout);
        PanelBtnMaximoKmLayout.setHorizontalGroup(
            PanelBtnMaximoKmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBtnMaximoKmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnMaximoKm, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelBtnMaximoKmLayout.setVerticalGroup(
            PanelBtnMaximoKmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBtnMaximoKmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnMaximoKm, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelIzquierdo.add(PanelBtnMaximoKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 230, 50));

        PanelBtnIntercabio.setBackground(new java.awt.Color(64, 121, 140));

        BtnIntercambiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnIntercambiar.setForeground(new java.awt.Color(255, 255, 255));
        BtnIntercambiar.setText("Intercambio");
        BtnIntercambiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnIntercambiarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnIntercambiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnIntercambiarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelBtnIntercabioLayout = new javax.swing.GroupLayout(PanelBtnIntercabio);
        PanelBtnIntercabio.setLayout(PanelBtnIntercabioLayout);
        PanelBtnIntercabioLayout.setHorizontalGroup(
            PanelBtnIntercabioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBtnIntercabioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnIntercambiar, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelBtnIntercabioLayout.setVerticalGroup(
            PanelBtnIntercabioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBtnIntercabioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnIntercambiar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelIzquierdo.add(PanelBtnIntercabio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 230, 50));

        PanelSalir.setBackground(new java.awt.Color(183, 9, 76));

        BtnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalir.setText("Salir");
        BtnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnSalirMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelSalirLayout = new javax.swing.GroupLayout(PanelSalir);
        PanelSalir.setLayout(PanelSalirLayout);
        PanelSalirLayout.setHorizontalGroup(
            PanelSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSalirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelSalirLayout.setVerticalGroup(
            PanelSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSalirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelIzquierdo.add(PanelSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, 50));

        PanelPrincipal.add(PanelIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 600));

        PanelDerechoSuperior.setBackground(new java.awt.Color(248, 248, 255));
        PanelDerechoSuperior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        LblInformacion.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        LblInformacion.setForeground(new java.awt.Color(0, 0, 0));
        LblInformacion.setText("Información");

        PanelContenido.setBackground(new java.awt.Color(248, 248, 255));

        javax.swing.GroupLayout PanelContenidoLayout = new javax.swing.GroupLayout(PanelContenido);
        PanelContenido.setLayout(PanelContenidoLayout);
        PanelContenidoLayout.setHorizontalGroup(
            PanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelContenidoLayout.setVerticalGroup(
            PanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelDerechoSuperiorLayout = new javax.swing.GroupLayout(PanelDerechoSuperior);
        PanelDerechoSuperior.setLayout(PanelDerechoSuperiorLayout);
        PanelDerechoSuperiorLayout.setHorizontalGroup(
            PanelDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
            .addComponent(PanelContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelDerechoSuperiorLayout.setVerticalGroup(
            PanelDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechoSuperiorLayout.createSequentialGroup()
                .addComponent(LblInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PanelPrincipal.add(PanelDerechoSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 450, 310));

        PanelDerechoInferior.setBackground(new java.awt.Color(248, 248, 255));
        PanelDerechoInferior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        PanelDerechoInferior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblCantidadPasajeros.setForeground(new java.awt.Color(0, 0, 0));
        LblCantidadPasajeros.setText("Cantidad de Pasajeros:");
        PanelDerechoInferior.add(LblCantidadPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 135, 30));

        LblKilometraje.setForeground(new java.awt.Color(0, 0, 0));
        LblKilometraje.setText("Kilometraje:");
        PanelDerechoInferior.add(LblKilometraje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 70, 30));
        PanelDerechoInferior.add(SpinnerCantidadPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 240, 32));
        PanelDerechoInferior.add(TxtKilometraje, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 240, 30));
        PanelDerechoInferior.add(LogoValidacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 30, 30));
        PanelDerechoInferior.add(LogoValidacion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 30, 30));

        PanelBtnRealizarViaje.setBackground(new java.awt.Color(64, 121, 140));

        BtnRealizarViaje.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnRealizarViaje.setForeground(new java.awt.Color(255, 255, 255));
        BtnRealizarViaje.setText("Realizar Viaje");
        BtnRealizarViaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRealizarViajeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnRealizarViajeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnRealizarViajeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelBtnRealizarViajeLayout = new javax.swing.GroupLayout(PanelBtnRealizarViaje);
        PanelBtnRealizarViaje.setLayout(PanelBtnRealizarViajeLayout);
        PanelBtnRealizarViajeLayout.setHorizontalGroup(
            PanelBtnRealizarViajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBtnRealizarViajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnRealizarViaje, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelBtnRealizarViajeLayout.setVerticalGroup(
            PanelBtnRealizarViajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBtnRealizarViajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnRealizarViaje, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelDerechoInferior.add(PanelBtnRealizarViaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 430, 60));

        PanelPrincipal.add(PanelDerechoInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 450, 290));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSelecChofer1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSelecChofer1MouseClicked
        // TODO add your handling code here:
        mostrarInfoChofer();
    }//GEN-LAST:event_BtnSelecChofer1MouseClicked

    private void BtnSelecChofer1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSelecChofer1MouseEntered
        // TODO add your handling code here:
        mouseEnter(PanelChofer1);
    }//GEN-LAST:event_BtnSelecChofer1MouseEntered

    private void BtnSelecChofer1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSelecChofer1MouseExited
        // TODO add your handling code here:
        mouseExit(PanelChofer1);
    }//GEN-LAST:event_BtnSelecChofer1MouseExited

    private void BtnColectivoChofer1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnColectivoChofer1MouseClicked
        // TODO add your handling code here:
        mostrarInfoColectivo();
    }//GEN-LAST:event_BtnColectivoChofer1MouseClicked

    private void BtnSelecChofer2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSelecChofer2MouseClicked
        // TODO add your handling code here:
        mostrarInfoChofer();
    }//GEN-LAST:event_BtnSelecChofer2MouseClicked

    private void BtnSelecChofer2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSelecChofer2MouseEntered
        // TODO add your handling code here:
        mouseEnter(PanelChofer2);
    }//GEN-LAST:event_BtnSelecChofer2MouseEntered

    private void BtnSelecChofer2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSelecChofer2MouseExited
        // TODO add your handling code here:
        mouseExit(PanelChofer2);
    }//GEN-LAST:event_BtnSelecChofer2MouseExited

    private void BtnColectivoChofer2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnColectivoChofer2MouseClicked
        // TODO add your handling code here:
        mostrarInfoColectivo();
    }//GEN-LAST:event_BtnColectivoChofer2MouseClicked

    private void BtnIntercambiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnIntercambiarMouseClicked

    }//GEN-LAST:event_BtnIntercambiarMouseClicked

    private void BtnIntercambiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnIntercambiarMouseEntered
        // TODO add your handling code here:
        mouseEnter(PanelBtnIntercabio);
    }//GEN-LAST:event_BtnIntercambiarMouseEntered

    private void BtnIntercambiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnIntercambiarMouseExited
        // TODO add your handling code here:
        mouseExit(PanelBtnIntercabio);
    }//GEN-LAST:event_BtnIntercambiarMouseExited

    private void BtnMaximoKmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnMaximoKmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnMaximoKmMouseClicked

    private void BtnMaximoKmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnMaximoKmMouseEntered
        // TODO add your handling code here:
        mouseEnter(PanelBtnMaximoKm);
    }//GEN-LAST:event_BtnMaximoKmMouseEntered

    private void BtnMaximoKmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnMaximoKmMouseExited
        // TODO add your handling code here:
        mouseExit(PanelBtnMaximoKm);
    }//GEN-LAST:event_BtnMaximoKmMouseExited

    private void BtnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSalirMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_BtnSalirMouseClicked

    private void BtnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSalirMouseEntered
        // TODO add your handling code here:
        mouseEnter(PanelSalir);
    }//GEN-LAST:event_BtnSalirMouseEntered

    private void BtnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSalirMouseExited
        // TODO add your handling code here:
        mouseExit(PanelSalir);
    }//GEN-LAST:event_BtnSalirMouseExited

    private void BtnRealizarViajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRealizarViajeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRealizarViajeMouseClicked

    private void BtnRealizarViajeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRealizarViajeMouseEntered
        // TODO add your handling code here:
        mouseEnter(PanelBtnRealizarViaje);
    }//GEN-LAST:event_BtnRealizarViajeMouseEntered

    private void BtnRealizarViajeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRealizarViajeMouseExited
        // TODO add your handling code here:
        mouseExit(PanelBtnRealizarViaje);
    }//GEN-LAST:event_BtnRealizarViajeMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnColectivoChofer1;
    private javax.swing.JLabel BtnColectivoChofer2;
    private javax.swing.JLabel BtnIntercambiar;
    private javax.swing.JLabel BtnMaximoKm;
    private javax.swing.JLabel BtnRealizarViaje;
    private javax.swing.JLabel BtnSalir;
    private javax.swing.JLabel BtnSelecChofer1;
    private javax.swing.JLabel BtnSelecChofer2;
    private javax.swing.JLabel LblCantidadPasajeros;
    private javax.swing.JLabel LblInformacion;
    private javax.swing.JLabel LblKilometraje;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JLabel LogoValidacion1;
    private javax.swing.JLabel LogoValidacion2;
    private javax.swing.JPanel PanelBtnIntercabio;
    private javax.swing.JPanel PanelBtnMaximoKm;
    private javax.swing.JPanel PanelBtnRealizarViaje;
    private javax.swing.JPanel PanelChofer1;
    private javax.swing.JPanel PanelChofer2;
    private javax.swing.JPanel PanelColectivo1;
    private javax.swing.JPanel PanelColectivo2;
    private javax.swing.JPanel PanelContenido;
    private javax.swing.JPanel PanelDerechoInferior;
    private javax.swing.JPanel PanelDerechoSuperior;
    private javax.swing.JPanel PanelIzquierdo;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JPanel PanelSalir;
    private javax.swing.JSpinner SpinnerCantidadPasajeros;
    private javax.swing.JTextField TxtKilometraje;
    // End of variables declaration//GEN-END:variables
}
