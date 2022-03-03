import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintProgram implements ActionListener {
    JFrame frame;
    DrawingPanel dPanel;
    JPanel buttonPanel, colorPanel, pickPanel;
    JButton pencilButton, MarkerButton,sprayButton, pickButton,eraserButton,blueButton,greenButton,yellowButton,redButton,clearButton;
    JTextField markerSize, redColor, greenColor, blueColor;
    // This is the PaintProgram constructor which sets up the JFrame
    // and all other components and containers
    // ** Code to be edited in Part C **
    public PaintProgram() {
        // Set up JFrame using BorderLayout
        frame = new JFrame("Paint Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add DrawingPanel to CENTER
        dPanel = new DrawingPanel();
        frame.add(dPanel);

        // Create buttonPanel and buttons
        buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        pencilButton = new JButton("Pencil");
        pencilButton.addActionListener(this);
        buttonPanel.add(pencilButton);

        sprayButton = new JButton("Spray");
        sprayButton.addActionListener(this);
        buttonPanel.add(sprayButton);

        MarkerButton = new JButton("Marker");
        MarkerButton.addActionListener(this);
        buttonPanel.add(MarkerButton);

        markerSize= new JTextField();
        markerSize.setColumns(3);
        buttonPanel.add(markerSize);
        markerSize.setText("1");

        eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(this);
        buttonPanel.add(eraserButton);


        colorPanel = new JPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        frame.add(colorPanel, BorderLayout.EAST);

        redColor= new JTextField();
        redColor.setColumns(3);
        colorPanel.add(redColor);

        greenColor= new JTextField();
        greenColor.setColumns(3);
        colorPanel.add(greenColor);

        blueColor= new JTextField();
        blueColor.setColumns(3);
        colorPanel.add(blueColor);

        pickButton = new JButton("Pick Color");
        pickButton.addActionListener(this);
        colorPanel.add(pickButton);

        blueButton = new JButton("Blue");
        blueButton.addActionListener(this);
        colorPanel.add(blueButton);

        greenButton = new JButton("Green");
        greenButton.addActionListener(this);
        colorPanel.add(greenButton);

        yellowButton = new JButton("Yellow");
        yellowButton.addActionListener(this);
        colorPanel.add(yellowButton);

        redButton = new JButton("Red");
        redButton.addActionListener(this);
        colorPanel.add(redButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        frame.pack();
        frame.setVisible(true);
    }

    // This the code that is called when any button is pressed
    // We should have a separate case for each button
    // ** Code to be edited in Part B **
    public void actionPerformed(ActionEvent ae) {
        // If pencilButton is pressed, set drawingPanel mode to "Pencil"
        if (ae.getActionCommand().equals("Pencil")) {
            dPanel.setMode("Pencil");
        }
        else if (ae.getActionCommand().equals("Spray")) {
            dPanel.setMode("Spray");
        }
        else if (ae.getActionCommand().equals("Marker")) {
            dPanel.setMode("Marker");
        }
        else if (ae.getActionCommand().equals("Clear")) {
            dPanel.clear();
        }
        else if (ae.getActionCommand().equals("Blue")) {
            dPanel.setColor(Color.BLUE);
        }
        else if (ae.getActionCommand().equals("Green")) {
            dPanel.setColor(Color.GREEN);
        }
        else if (ae.getActionCommand().equals("Yellow")) {
            dPanel.setColor(Color.YELLOW);
        }
        else if (ae.getActionCommand().equals("Red")) {
            dPanel.setColor(Color.RED);
        }
        else if (ae.getActionCommand().equals("Eraser")){
            dPanel.setMode("Eraser");
        }
        if(ae.getActionCommand().equals("Spray")) {
            dPanel.setMode("Spray");
        }
        if(ae.getActionCommand().equals("Pick Color")){
            Color chosen = new Color(Integer.parseInt(redColor.getText()),Integer.parseInt(greenColor.getText()), Integer.parseInt(blueColor.getText()));
            dPanel.setColor(chosen);
        }
    }

    // Main method just creates a PaintProgram object
    public static void main(String[] args) {
        PaintProgram x = new PaintProgram();
    }

    class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
        // DrawingPanel has the following instance variables:

        // A 2D array which stores whether or not
        // each pixel should be painted
        // ** To be used in Part B **
        private boolean[][] isPainted;

        // A 2D array which stores the Java Colors
        // of each pixel
        // ** To be used in Part C **
        private Color[][] colors;

        // The mode is a String that we can use to keep track of
        // what should happen if the user presses the mouse
        // ** To be used in Part B **
        private String mode;

        // This keeps track of the current selected color
        // ** To be used in Part C **
        private Color color;

        // These are constant values
        private static final int WIDTH = 500;
        private static final int HEIGHT = 500;

        public DrawingPanel() {
            // Set background color
            setBackground(Color.WHITE);

            // Add mouse listeners
            addMouseListener(this);
            addMouseMotionListener(this);

            // Initialize instance variables
            isPainted = new boolean[WIDTH][HEIGHT];
            colors = new Color[WIDTH][HEIGHT];
            mode = "Pencil";
            color = Color.BLACK;

        }
        public void clear(){
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    isPainted[i][j] = false;
                }
            }
            repaint();
        }
        // Can be called to change the current mode
        // of the drawing panel
        // ** You should never need to edit this code **
        public void setMode(String mode) {
            this.mode = mode;
        }

        // Can be called to change the current color
        // of the drawing panel
        // ** You should never need to edit this code **
        public void setColor(Color color) {
            this.color = color;
        }

        // Sets the size of the DrawingPanel, so frame.pack() considers
        // its preferred size when sizing the JFrame
        // ** You should never need to edit this code **
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }

        // This is the method that draws everything
        // ** Code to be edited in Part C **
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (isPainted[x][y] == true) {
                        g.setColor(colors[x][y]);
                        g.drawRect(x, y, 1, 1);
                    }
                }
            }
        }

        // MouseListener methods
        // This is the method that is called when the mouse
        // is pressed. This is where most of your code will go
        // ** Code to be edited in Part B **
        public void mousePressed(MouseEvent e) {
            // Check the current mode
            // * If "pencil" mode, we should mark the current
            //   pixel as painted
            if (mode.equals("Pencil")) {
                // Check that mouse is in bounds of panel
                if (e.getX() >= 0 && e.getX() < WIDTH &&
                        e.getY() >= 0 && e.getY() < HEIGHT) {
                    colors[e.getX()][e.getY()] = color;
                    isPainted[e.getX()][e.getY()] = true;
                }
            }
            if (mode.equals("Marker")) {
                int s = Integer.parseInt(markerSize.getText());
                if(s+e.getX()<WIDTH&&s+e.getY()<HEIGHT&e.getX()-s<WIDTH&&e.getY()-s<HEIGHT) {
                    for (int i = e.getX() - s; i < e.getX() + s; i++) {
                        for (int j = e.getY() - s; j < e.getY() + s; j++) {
                            colors[i][j] = color;
                            isPainted[i][j] = true;
                        }
                    }
                }
            }
            if (mode.equals("Spray")) {
                if(10+e.getX()<WIDTH&&10+e.getY()<HEIGHT&&e.getX()-10>0&&e.getY()-10>0) {
                    for (int i = e.getX() - 10; i < e.getX() + 10; i++) {
                        for (int j = e.getY() - 10; j < e.getY() + 10; j++) {
                            if (Math.random()>0.9){
                            colors[i][j] = color;
                            isPainted[i][j] = true;
                            }
                        }
                    }
                }
            }

            if (mode.equals("Eraser")) {
                // Check that mouse is in bounds of panel
                if (e.getX()-10 >= 0 && e.getX()+10 <= WIDTH && e.getY()-10 >= 0 && e.getY()+10 < HEIGHT) {
                    for (int i = e.getX() - 10; i < e.getX() + 10; i++) {
                        for (int j = e.getY() - 10; j < e.getY() + 10; j++) {
                            isPainted[i][j] = false;
                        }
                    }
                }
            }

            if (mode.equals("Clear")) {
                // Check that mouse is in bounds of panel

                clear();

            }




            // We need to manually tell the panel to repaint
            // and call the paintComponent method
            repaint();
        }

        // This is a MouseMotionListener method
        // We have this method so that we don't need to click each
        // pixel that we want to draw
        // ** You should never need to edit this code **
        public void mouseDragged(MouseEvent e) {
            mousePressed(e);
        }

        // The remaining MouseListener and MouseMotionLister
        // methods are left blank
        // ** You should never need to edit this code **
        public void mouseReleased(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseEntered(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseExited(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseClicked(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseMoved(MouseEvent e) {
            // This method is intentionally blank
        }
    }
}