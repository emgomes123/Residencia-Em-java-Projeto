package Classes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @author Gustavo Ferreira - www.gqferreira.com.br
 */
@SuppressWarnings("serial")
class NumberPlainDocument extends PlainDocument {

    int maxSize;

    public NumberPlainDocument(int limit) {
        maxSize = limit;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException, java.lang.NumberFormatException {
        if (((getLength() + str.length()) <= maxSize) || maxSize == 0) {
            try {
                Long.parseLong(str);
                super.insertString(offs, str, a);
            } catch (Exception ex) {
            }

        }
    }
}
