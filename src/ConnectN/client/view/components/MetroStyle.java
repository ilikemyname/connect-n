/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import ConnectN.utilities.SegoeFont;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Dang Hong Thanh 
 */
public class MetroStyle {
    
    public static final Color ORANGE        = new Color(213, 77, 52),
                              RED           = new Color(229, 20, 0),
                              LIGHT_BLUE    = new Color(27, 161, 226),
                              GRAY       = new Color(46, 46, 46);

    public static void setLookAndFeel(String style) {
        try {
            // Customizes the L&F
            UIManager.put("Synthetica.window.arcW", 0);
            UIManager.put("Synthetica.window.arcH", 0);
            UIManager.put("Synthetica.rootPane.titlePane.iconifyButton.gap", 10);
            UIManager.put("Synthetica.rootPane.titlePane.toggleButton.gap", 10);
            UIManager.put("Synthetica.rootPane.titlePane.title.visible", false);
            UIManager.put("Synthetica.rootPane.titlePane.iconifyButton.insets",
                    new Insets(10, 0, 3, 0));
            UIManager.put("Synthetica.rootPane.titlePane.toggleButton.insets",
                    new Insets(10, 0, 3, 0));
            UIManager.put("Synthetica.rootPane.titlePane.closeButton.insets",
                    new Insets(10, 0, 3, 5));
            UIManager.put("Synthetica.rootPane.titlePane.menuBar.insets",
                    new Insets(10, 3, 3, 3));

            setLnF("dark");

            // Sets custom font
            Font font = SegoeFont.getSegoeUIFont("normal", 13.0f);
            SyntheticaLookAndFeel.setFont(font);

            // Sets license for Synthetica pack
            String[] syntheticaLicense = {
                "Licensee=Nguyen Thanh Luan",
                "LicenseRegistrationNumber=NCNL120529",
                "Product=Synthetica",
                "LicenseType=Non Commercial",
                "ExpireDate=--.--.----",
                "MaxVersion=2.999.999"
            };
            UIManager.put("Synthetica.license.info", syntheticaLicense);
            UIManager.put("Synthetica.license.key",
                    "3328F49F-31D2DE58-63F25088-5D69B566-1039A8FD");

            // Sets license for Synthetica Addons pack
            String[] syntheticaAddonsLicense = {
                "Licensee=Nguyen Thanh Luan",
                "LicenseRegistrationNumber=NCNL120529",
                "Product=SyntheticaAddons",
                "LicenseType=Non Commercial",
                "ExpireDate=--.--.----",
                "MaxVersion=1.999.999"};
            UIManager.put("SyntheticaAddons.license.info",
                    syntheticaAddonsLicense);
            UIManager.put("SyntheticaAddons.license.key",
                    "8BF822DF-DCC143A8-BB6B46BF-DE468F33-35AF01FE");

        } catch (ParseException | UnsupportedLookAndFeelException ex) {
            // Sets system looks and feel. If there's exception, leave the L&F
            // be Java Default (Metal L&F).
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException |
                    ClassNotFoundException |
                    InstantiationException |
                    IllegalAccessException e) {
            }
        }
    }

    private static void setLnF(String style) throws ParseException,
            UnsupportedLookAndFeelException {
        if (style.equalsIgnoreCase("light")) {
            UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
        } else if (style.equalsIgnoreCase("dark")) {
            UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
        }
    }
}
