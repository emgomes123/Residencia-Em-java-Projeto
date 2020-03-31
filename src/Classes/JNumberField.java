package Classes;

import javax.swing.JTextField;

/**
 * @author Gustavo Ferreira - www.gqferreira.com.br
 */
@SuppressWarnings("serial")
public class JNumberField extends JTextField {

    public JNumberField(int maxlenght) {
        this(null, maxlenght);
    }

    public JNumberField() {
        this(null, 0);
    }

    public JNumberField(Integer value, int maxlenght) {
        super();
        setDocument(new NumberPlainDocument(maxlenght));
        if (value != null){
            this.setText(String.valueOf(value.intValue()));
        }
    }
    
    public void setInt(int value){
        this.setText(String.valueOf(value));
    }
    public void setLong(long value){
        this.setText(String.valueOf(value));
    }

    public int getInt() {
        return this.getText().isEmpty() ? 0 : Integer.parseInt(this.getText());
    }

    public long getLong() {
        return this.getText().isEmpty() ? 0 : Long.parseLong(this.getText());
    }
}
