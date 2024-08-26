import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{

    private int x0, y0, x1, y1;
    private JLabel txt_x0, txt_y0, txt_x1, txt_y1;
    private JTextField jtf_x0, jtf_y0, jtf_x1, jtf_y1;
    private Dibujar drawingPanel;

    public Main() {
        setTitle("Algoritmo DDA");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(222, 255, 245));

        txt_x0 = new JLabel("x0");
        txt_x0.setBounds(0, 0, 50, 25);
        add(txt_x0);
        jtf_x0 = new JTextField("");
        jtf_x0.setBounds(25,0,50,25);
        add(jtf_x0);

        txt_y0 = new JLabel("y0");
        txt_y0.setBounds(0, 30, 50, 25);
        add(txt_y0);
        jtf_y0 = new JTextField("");
        jtf_y0.setBounds(25,30,50,25);
        add(jtf_y0);

        txt_x1 = new JLabel("x1");
        txt_x1.setBounds(125, 0, 50, 25);
        add(txt_x1);
        jtf_x1 = new JTextField("");
        jtf_x1.setBounds(150,0,50,25);
        add(jtf_x1);

        txt_y1 = new JLabel("y1");
        txt_y1.setBounds(125, 30, 50, 25);
        add(txt_y1);
        jtf_y1 = new JTextField("");
        jtf_y1.setBounds(150,30,50,25);
        add(jtf_y1);

        JButton drawButton = new JButton("Dibujar Línea");
        drawButton.setBounds(350,0,150,25);
        add(drawButton);

        drawingPanel = new Dibujar();
        drawingPanel.setBounds(20, 100, 740, 400);
        add(drawingPanel);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    x0 = Integer.parseInt(jtf_x0.getText());
                    y0 = Integer.parseInt(jtf_y0.getText());
                    x1 = Integer.parseInt(jtf_x1.getText());
                    y1 = Integer.parseInt(jtf_y1.getText());
                    drawingPanel.actualizarLinea(x0, y0, x1, y1);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores válidos.");
                }
            }
        });
    }
    private class Dibujar extends JPanel {
        private int x0, y0, x1, y1;

        public void actualizarLinea(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            LineaDDA(g, x0, y0, x1, y1);
        }

        private void LineaDDA(Graphics g, int x0, int y0, int x1, int y1) {
            int dx = x1 - x0;
            int dy = y1 - y0;

            int steps = Math.max(Math.abs(dx), Math.abs(dy));

            // incrementos
            float Xinc = dx / (float) steps;
            float Yinc = dy / (float) steps;

            // Coordenadas actuales
            float x = x0;
            float y = y0;

            // Dibuja los puntos de la línea
            for (int i = 0; i <= steps; i++) {
                //Punto en las coordenadas actuales
                g.drawRect(Math.round(x), Math.round(y), 1, 1);
                //Aumenta la coordenada
                x += Xinc;
                y += Yinc;
            }
        }
    }
    public static void main(String[] args) {
        Main ventana = new Main();
        ventana.setVisible(true);
    }
}