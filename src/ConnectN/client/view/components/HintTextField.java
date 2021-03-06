/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import ConnectN.utilities.Util;
import de.javasoft.swing.JYTextField;

/**
 * <b>Description:</b><br/> A test TextField with hint text for what the user
 * should input. <br/> <b>Current version:</b> 1 Build 4<br/> <b>Changes
 * list:</b><br /> <ul> <li><b>1 build 4:</b> Modify the behavior to still
 * appear the hint when the TextField is getting focused.</li> <li><b>1 build
 * 3:</b> Fix the getText method: return an empty String if the text inside is
 * null.</li> <li><b>1 build 2:</b> Fix the bug of setText method: The text set
 * will not be the normal text, although it is not equal with
 * <i>hint</i>.</li></ul>
 *
 * @author Dang Hong Thanh
 * @version 1 Build 4
 * @since 17 Feb 2012
 */
public class HintTextField extends JYTextField {

    /**
     * Creates a HintTextField with filled text, hint and columns size.
     *
     * @param hint
     * @param text
     * @param columns
     */
    public HintTextField(String hint, String text, int columns) {
        super(text, columns);
        init(hint);
    }

    /**
     * Creates an empty HintTextField with specified hint and columns size.
     *
     * @param hint
     * @param columns
     */
    public HintTextField(String hint, int columns) {
        super(columns);
        init(hint);
    }

    /**
     * Creates a HintTextField with filled text and hint.
     *
     * @param hint
     * @param text
     */
    public HintTextField(String hint, String text) {
        super(text);
        init(hint);
    }

    /**
     * Creates an empty HintTextField with hint only.
     *
     * @param hint
     */
    public HintTextField(String hint) {
        super();
        init(hint);
    }

    private void init(String hint) {
        this.setPromptText(hint);
        this.setPromptStrategy(PromptStrategy.NO_TEXT);
        this.addFocusListener(Util.SelectAllFocusAdapter);
    }
}
