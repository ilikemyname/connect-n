package ConnectN.client;

import ConnectN.client.view.components.MetroStyle;
import ConnectN.client.view.forms.Dialog_Login;

/**
 *
 * @author phuy
 */
public class Client {

    public static void main(String args[]) {
        MetroStyle.setLookAndFeel("dark");
        new Dialog_Login();
    }
}
