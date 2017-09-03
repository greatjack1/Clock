/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author yaakov
 */
public class ZText extends Text {

    public ZText(String text) {
        super();
        setText(text);
        setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    }

}
