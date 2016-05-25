import controllers.VivariumController;
import java.awt.Color;
import model.CircularTurtle;
import model.PolygonalTurtle;
import model.SquareTurtle;
import model.TriangularTurtle;
import views.VivariumView;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public abstract class Main
{
    /**
     * @param args Command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(VivariumView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            // Initialize turtle
            // PolygonalTurtle theTurtle = new PolygonalTurtle(100., 100., Color.RED, 8, 20);
            // SquareTurtle theTurtle = new SquareTurtle(50., 50., Color.BLUE, 20);
            // TriangularTurtle theTurtle = new TriangularTurtle(50., 50., Color.ORANGE, 20);
            CircularTurtle theTurtle = new CircularTurtle(50., 50., Color.GREEN, 50);
            
            // Initialize view and controller
            VivariumView view = new VivariumView();
            VivariumController controller = new VivariumController(view, theTurtle);
            
            view.setController(controller);
            view.setVisible(true);
        });
    }
}
