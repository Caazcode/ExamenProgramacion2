package examenprogra2thomasycarlos;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class RentaMultimedia {

    private static ArrayList<RentItem> items = new ArrayList<>();

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        JFrame frame = new JFrame("🎬 Sistema de Renta Multimedia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(33, 47, 60));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBackground(new Color(33, 47, 60));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        String[] opciones = {"➕ Agregar Ítem", "💰 Rentar", "⚙️ Submenú", "📋 Imprimir Todo", "❌ Salir"};

        for (int i = 0; i < opciones.length; i++) {
            JButton btn = new JButton(opciones[i]);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(new Color(41, 128, 185));
            btn.setForeground(Color.WHITE);
            int finalI = i;
            btn.addActionListener(e -> {
                frame.dispose(); // cerrar menú actual
                switch (finalI) {
                    case 0 -> agregarItem();
                    case 1 -> rentarItem();
                    case 2 -> ejecutarSubmenu();
                    case 3 -> imprimirTodo();
                    case 4 -> System.exit(0);
                }
            });
            panel.add(btn);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void agregarItem() {
        String[] opciones = {"Película", "Videojuego"};
        int tipo = JOptionPane.showOptionDialog(null, "Seleccione tipo de ítem:", "Agregar",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (tipo == -1) return;

        String codigo = JOptionPane.showInputDialog("Ingrese código:");
        if (buscarItem(codigo) != null) {
            JOptionPane.showMessageDialog(null, "❌ Código ya existe.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese nombre:");

        // Fecha de publicación usando calendario visual
        JPanel fechaPanel = new JPanel();
        fechaPanel.add(new JLabel("Seleccione fecha de publicación:"));
        JDateChooser dateChooser = new JDateChooser();
        fechaPanel.add(dateChooser);
        int result = JOptionPane.showConfirmDialog(null, fechaPanel, "Fecha de publicación", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION || dateChooser.getDate() == null) return;
        Date fechaPublicacion = dateChooser.getDate();

        // Imagen
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar imagen");
        chooser.setFileFilter(new FileNameExtensionFilter("Imágenes (.jpg, .png)", "jpg", "jpeg", "png"));
        int resultado = chooser.showOpenDialog(null);
        if (resultado != JFileChooser.APPROVE_OPTION) return;

        Image img = new ImageIcon(chooser.getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon icono = new ImageIcon(img);

        if (tipo == 0) { // Movie
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio base:"));
            Movie movie = new Movie(codigo, nombre, precio);
            movie.setImagen(icono);
            movie.setFechaPublicacion(fechaPublicacion);
            items.add(movie);
        } else {
            Game game = new Game(codigo, nombre, 20);
            game.setImagen(icono);
            game.setFechaPublicacion(fechaPublicacion);
            items.add(game);
        }

        JOptionPane.showMessageDialog(null, "✔️ Ítem agregado exitosamente.");
        mostrarMenuPrincipal();
    }

    private static void rentarItem() {
        String codigo = JOptionPane.showInputDialog("Ingrese código:");
        RentItem item = buscarItem(codigo);

        if (item == null) {
            JOptionPane.showMessageDialog(null, "❌ Ítem no encontrado.");
            mostrarMenuPrincipal();
            return;
        }

        int dias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese días de renta:"));
        double monto = item.pagoRenta(dias);

        JOptionPane.showMessageDialog(null, item.toString() + "\n💸 Total a pagar: Lps. " + monto,
                "Renta", JOptionPane.INFORMATION_MESSAGE, item.getImagen());

        mostrarMenuPrincipal();
    }

    private static void ejecutarSubmenu() {
        String codigo = JOptionPane.showInputDialog("Ingrese código:");
        RentItem item = buscarItem(codigo);

        if (item instanceof MenuActions) {
            ((MenuActions) item).submenu();
        } else {
            JOptionPane.showMessageDialog(null, "⚠️ Este ítem no tiene submenú.");
        }

        mostrarMenuPrincipal();
    }

    private static void imprimirTodo() {
        JFrame frame = new JFrame("🎥 Ítems Registrados");
        frame.setSize(850, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(236, 240, 241));

        for (RentItem item : items) {
            JPanel tarjeta = new JPanel(new BorderLayout(10, 10));
            tarjeta.setBackground(Color.WHITE);
            tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 2),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            JLabel img = new JLabel(item.getImagen());
            JTextArea texto = new JTextArea(item.toString());
            texto.setEditable(false);
            texto.setBackground(Color.WHITE);
            texto.setFont(new Font("Segoe UI", Font.PLAIN, 13));

            tarjeta.add(img, BorderLayout.WEST);
            tarjeta.add(texto, BorderLayout.CENTER);

            panel.add(Box.createVerticalStrut(10));
            panel.add(tarjeta);
        }

        JScrollPane scroll = new JScrollPane(panel);
        frame.add(scroll);
        frame.setVisible(true);
    }

    private static RentItem buscarItem(String codigo) {
        for (RentItem item : items) {
            if (item.getCodigo().equalsIgnoreCase(codigo)) {
                return item;
            }
        }
        return null;
    }
}

