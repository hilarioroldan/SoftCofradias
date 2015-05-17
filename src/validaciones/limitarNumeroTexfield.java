
package validaciones;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class limitarNumeroTexfield extends PlainDocument{
    
  private JTextField editor;  
  private int num;

    public limitarNumeroTexfield(JTextField editor, int num) {
        this.editor = editor;
        this.num = num;
    }
  
  public void insertString(int arg0, String arg1, AttributeSet arg2) throws  BadLocationException{
      if ((editor.getText().length()+arg1.length()) > this.num) {
          java.awt.Toolkit.getDefaultToolkit().beep();
          return;
      } super.insertString(arg0, arg1, arg2);
  }
    
}
