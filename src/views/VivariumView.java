package views;

import controllers.VivariumController;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class VivariumView extends javax.swing.JFrame
{
    /**
     * Creates a VivariumView.
     */
    public VivariumView()
    {
        // Initialize compoments
        this.initComponents();
        
        // Initialize properties
        this.controller = null;
    }
    
    /**
     * Sets the view's controller.
     * 
     * @param controller The controller.
     */
    public void setController(VivariumController controller)
    {
        // Detach the old controller if there is one
        if(null != this.controller)
        {
            this.turnLeftButton.removeActionListener(this.controller);
            this.moveForwardButton.removeActionListener(this.controller);
            this.turnRightButton.removeActionListener(this.controller);
        }
        
        // Attach the new controller
        this.controller = controller;
        this.turnLeftButton.addActionListener(this.controller);
        this.moveForwardButton.addActionListener(this.controller);
        this.turnRightButton.addActionListener(this.controller);
    }
    
    /**
     * Gets a reference to the vivarium in this view.
     * 
     * @return A reference to the vivarium.
     */
    public VivariumPanel getVivarium()
    {
        return this.vivarium;
    }
    
    /**
     * Gets the user's input as a double.
     * 
     * @return The user's input as a double.
     */
    public double getUserInput()
    {
        return Double.parseDouble(this.userInput.getText());
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        toolBar = new javax.swing.JToolBar();
        turnLeftButton = new javax.swing.JButton();
        moveForwardButton = new javax.swing.JButton();
        turnRightButton = new javax.swing.JButton();
        userInput = new javax.swing.JFormattedTextField();
        vivarium = new views.VivariumPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0};
        layout.rowHeights = new int[] {0, 5, 0};
        getContentPane().setLayout(layout);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        turnLeftButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/resources/arrow-circle-225-left.png"))); // NOI18N
        turnLeftButton.setText("Tourner à gauche");
        turnLeftButton.setActionCommand("turn_left");
        turnLeftButton.setFocusable(false);
        turnLeftButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        turnLeftButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(turnLeftButton);

        moveForwardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/resources/arrow-090.png"))); // NOI18N
        moveForwardButton.setText("Avancer");
        moveForwardButton.setActionCommand("move_forward");
        moveForwardButton.setFocusable(false);
        moveForwardButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        moveForwardButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(moveForwardButton);

        turnRightButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/resources/arrow-circle-315.png"))); // NOI18N
        turnRightButton.setText("Tourner à droite");
        turnRightButton.setActionCommand("turn_right");
        turnRightButton.setFocusable(false);
        turnRightButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        turnRightButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(turnRightButton);

        userInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        userInput.setText("10");
        toolBar.add(userInput);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        getContentPane().add(toolBar, gridBagConstraints);

        vivarium.setMinimumSize(new java.awt.Dimension(800, 600));
        vivarium.setPreferredSize(new java.awt.Dimension(800, 600));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(vivarium, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton moveForwardButton;
    protected javax.swing.JToolBar toolBar;
    protected javax.swing.JButton turnLeftButton;
    protected javax.swing.JButton turnRightButton;
    protected javax.swing.JFormattedTextField userInput;
    protected views.VivariumPanel vivarium;
    // End of variables declaration//GEN-END:variables
    protected VivariumController controller;
}
