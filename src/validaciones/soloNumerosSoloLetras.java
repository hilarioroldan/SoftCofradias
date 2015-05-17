
package validaciones;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class soloNumerosSoloLetras {

    public soloNumerosSoloLetras() {
    }   
    
    public void SLetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (Character.isDigit(c)) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                e.consume();
            }
        }
        });
    }
    
    public void SNumeros(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                e.consume();
            }
        }
        });
    }
    
}
