
package ConnectN.utilities;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

/**
 *
 * @author phuy
 */
public class SegoeFont {

    private static final Font SERIF_FONT = new Font("serif", Font.PLAIN, 11);

    public static Font getSegoeUIFont(String style) {
        Font font;
        try {
            InputStream fontStream = SegoeFont.class.getResourceAsStream(
                    "fonts/segoeui_" + style + ".ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            GraphicsEnvironment ge = GraphicsEnvironment.
                    getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception ex) {
            System.out.println("Cannot find the file");
            font = SERIF_FONT;
        }

        return font.deriveFont(13.0f);
    }
    
    public static Font getSegoeUIFont(String style, float size) {
        return getSegoeUIFont(style).deriveFont(size);
    }
}
