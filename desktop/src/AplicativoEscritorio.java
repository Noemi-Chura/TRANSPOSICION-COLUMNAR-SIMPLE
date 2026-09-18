import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AplicativoEscritorio extends JFrame {

    private final JTextArea txtTexto;
    private final JTextField txtClave;
    private final JTextArea txtResultado;
    private final JLabel lblEstado;

    public AplicativoEscritorio() {
        super("Transposicion Columnar Simple");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTexto = new JLabel("Texto a cifrar/descifrar:");
        lblTexto.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtTexto = new JTextArea(5, 40);
        txtTexto.setLineWrap(true);
        txtTexto.setWrapStyleWord(true);
        JScrollPane scrollTexto = new JScrollPane(txtTexto);
        scrollTexto.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblClave = new JLabel("Clave (solo letras):");
        lblClave.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtClave = new JTextField();
        txtClave.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        txtClave.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnCifrar = new JButton("Cifrar");
        JButton btnDescifrar = new JButton("Descifrar");
        JButton btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.add(btnCifrar);
        panelBotones.add(btnDescifrar);
        panelBotones.add(btnLimpiar);
        panelBotones.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtResultado = new JTextArea(6, 40);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(245, 245, 245));
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblEstado = new JLabel(" ");
        lblEstado.setForeground(new Color(160, 0, 0));
        lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelPrincipal.add(lblTexto);
        panelPrincipal.add(Box.createVerticalStrut(5));
        panelPrincipal.add(scrollTexto);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(lblClave);
        panelPrincipal.add(Box.createVerticalStrut(5));
        panelPrincipal.add(txtClave);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(lblResultado);
        panelPrincipal.add(Box.createVerticalStrut(5));
        panelPrincipal.add(scrollResultado);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(lblEstado);

        add(panelPrincipal, BorderLayout.CENTER);

        btnCifrar.addActionListener(this::accionCifrar);
        btnDescifrar.addActionListener(this::accionDescifrar);
        btnLimpiar.addActionListener(e -> {
            txtTexto.setText("");
            txtClave.setText("");
            txtResultado.setText("");
            lblEstado.setText(" ");
        });
    }

    private void accionCifrar(ActionEvent e) {
        procesar(true);
    }

    private void accionDescifrar(ActionEvent e) {
        procesar(false);
    }

    private void procesar(boolean cifrando) {
        String texto = txtTexto.getText();
        String clave = txtClave.getText();

        try {
            String resultado = cifrando
                    ? CifradoTransposicion.cifrar(texto, clave)
                    : CifradoTransposicion.descifrar(texto, clave);

            txtResultado.setText(resultado);
            lblEstado.setForeground(new Color(0, 120, 0));
            lblEstado.setText(cifrando ? "Texto cifrado correctamente." : "Texto descifrado correctamente.");
        } catch (IllegalArgumentException ex) {
            txtResultado.setText("");
            lblEstado.setForeground(new Color(180, 0, 0));
            lblEstado.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            AplicativoEscritorio app = new AplicativoEscritorio();
            app.setVisible(true);
        });
    }
}
