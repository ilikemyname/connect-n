
package ConnectN.utilities;

import ConnectN.client.view.ConnectNView;
import java.awt.Window;
import java.awt.event.*;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;


/**
 * Provides some global methods to format String, number, data or some GUI
 * related methods.
 *
 * @author phuy
 * 
 */
public class Util {

    public static FocusAdapter SelectAllFocusAdapter = new SelectAllFocusAdapter(),
            IDFocusAdapter = new IDFocusAdapter(),
            NameFocusAdapter = new NameFocusAdapter(),
            ProperNameFocusAdapter = new ProperNameFocusAdapter();

    /**
     * Formats the input long and return the formatted String.
     *
     * @param money The input in
     * <code>long</code> and cannot be null.
     * @return the correct format of number.
     */
    public static String formatNum(long money) {
        return NumberFormat.getInstance().format(money);
    }

    public static String upperCaseFirst(String input) {
        return input.isEmpty()
                ? ""
                : input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String upperCaseBlock(String input) {
        String delimeter = " ";
        String[] temp = input.split(delimeter);

        String result = "";
        for (String string : temp) {
            result += upperCaseFirst(string) + " ";
        }

        return result.trim();
    }

    public static String getCurrentShortTime() {
        Calendar c = new GregorianCalendar();
        return formatShortTime(c);
    }

    public static String getCurrentShortDate() {
        Calendar c = new GregorianCalendar();
        return formatShortDate(c);
    }

    public static String getCurrentDateAndTime() {
        return getCurrentShortDate() + " - " + getCurrentShortTime();
    }

    public static String getCurrentFullDate() {
        Calendar c = new GregorianCalendar();
        return formatFullDate(c);
    }

    public static String formatShortDate(Calendar c) {
        int date = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        String m = (month > 9) ? month + "" : "0" + month;

        return date + "/" + m + "/" + year;
    }

    public static String formatFullDate(Calendar c) {
        int date = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        return "Ngày " + date + " tháng " + month + " năm " + year;
    }

    public static String formatShortTime(Calendar c) {
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String m = (minute > 9) ? minute + "" : "0" + minute;
        int second = c.get(Calendar.SECOND);
        return hour + ":" + m + ":" + second;
    }

    /**
     * Returns an ImageIcon based on the name of that icon.
     *
     * @param iconName The name of the icon. Cannot be null or empty. Must be
     * exactly like the name of the PNG file stored in client\view\img.
     * @return The ImageIcon based on the name.
     */
    public static ImageIcon makeIcon(String iconName) {
        URL imgURL = ConnectNView.class.getResource("img/" + iconName + ".png");
        return new ImageIcon(imgURL);
    }

    /**
     * Returns a JLabel with the specified text, icon and vertical, horizontal
     * text position.
     *
     * @param text The specified text of the JLabel. Can be null or empty.
     * @param icon The icon of the JLabel.
     * @param vert Must be a JLabel constant of vertical position.
     * @param horiz Must be a JLabel constant of horizontal position.
     * @see Util.makeIcon()
     * @return The created JLabel.
     */
    public static JLabel makeLabel(String text, String icon,
            int vert, int horiz) {
        JLabel label = new JLabel(text, makeIcon(icon), SwingConstants.CENTER);
        label.setVerticalTextPosition(vert);
        label.setHorizontalTextPosition(horiz);
        return label;
    }

    /**
     * Creates and returns a simple image only button (no hovered and pressed
     * effects). If the image does not exist, this will return a standard button
     * with text specified by altTxt.
     *
     * @param iconName The name of the icon file.
     * @param command The ActionCommand of this button.
     * @param tooltip The tool tip text for this button.
     * @param altTxt The alternative text in case the icon does not exist.
     * @return The created button.
     */
    public static JButton makeSimpleImgButton(String iconName, String command,
            String tooltip, String altTxt) {
        ImageIcon icon = makeIcon(iconName);
        JButton button = new JButton();

        if (icon != null) {
            button.setIcon(icon);
            button.setActionCommand(command);
            button.setToolTipText(tooltip);
            button.setBorder(null);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
        } else {
            button.setText(altTxt);
            button.setActionCommand(command);
        }

        return button;
    }

    /**
     * Creates and returns an image only button (with hovered and pressed
     * effects). If the image does not exist, this will return a standard button
     * with text specified by altTxt.
     *
     * @param iconName The name of the icon file.
     * @param command The ActionCommand of this button.
     * @param tooltip The tool tip text for this button.
     * @param altTxt The alternative text in case the icon does not exist.
     * @return The created button.
     */
    public static JButton makeImgButton(String iconName, String command,
            String tooltip, String altTxt) {
        ImageIcon ic = makeIcon(iconName),
                ic_over = makeIcon(iconName + "_Over"),
                ic_press = makeIcon(iconName + "_Press");

        JButton button = new JButton();

        if (ic != null && ic_over != null && ic_press != null) {
            button.setIcon(ic);
            button.setRolloverIcon(ic_over);
            button.setPressedIcon(ic_press);
            button.setActionCommand(command);
            button.setToolTipText(tooltip);
            button.setBorder(null);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
        } else {
            button.setText(altTxt);
            button.setActionCommand(command);
        }

        return button;
    }

    public static KeyListener makeEscapeKeyAdapter(final Window parent) {
        return new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                Object o = e.getSource();
                if (o instanceof JTextField
                        && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    parent.dispose();
                }
            }
        };
    }
}
class SelectAllFocusAdapter extends FocusAdapter {

    @Override
    public void focusGained(FocusEvent e) {
        Object o = e.getSource();
        if (o instanceof JTextField) {
            ((JTextField) o).selectAll();
        } else if (o instanceof JTextArea) {
            ((JTextArea) o).selectAll();
        }
    }
}

class IDFocusAdapter extends SelectAllFocusAdapter {

    @Override
    public void focusLost(FocusEvent e) {
        Object o = e.getSource();
        if (o instanceof JTextField) {
            String text = ((JTextField) o).getText();
            ((JTextField) o).setText(text.toUpperCase());
        }
    }
}

class NameFocusAdapter extends SelectAllFocusAdapter {

    @Override
    public void focusLost(FocusEvent e) {
        Object o = e.getSource();
        if (o instanceof JTextField) {
            String text = ((JTextField) o).getText();
            ((JTextField) o).setText(Util.upperCaseFirst(text));
        }
    }
}

class ProperNameFocusAdapter extends NameFocusAdapter {

    @Override
    public void focusLost(FocusEvent e) {
        Object o = e.getSource();
        if (o instanceof JTextField) {
            String text = ((JTextField) o).getText();
            ((JTextField) o).setText(Util.upperCaseBlock(text));
        }
    }
}
