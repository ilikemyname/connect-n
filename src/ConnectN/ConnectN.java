/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Luan Nguyen Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN;

import ConnectN.client.view.components.MetroStyle;
import ConnectN.client.view.forms.Dialog_Login;

/**
 *
 * @author Dang Hong Thanh
 */
public class ConnectN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetroStyle.setLookAndFeel("dark");
        
        new Dialog_Login();
    }
}
