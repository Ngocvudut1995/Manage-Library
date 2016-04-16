/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

import javafx.scene.control.TextField;

/**
 *
 * @author Vu Dang
 */
public class NumberTextField extends TextField{
     private int maxlength;
    public NumberTextField() {
        this.maxlength = 15;
    }
    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }
    @Override
      public void replaceText(int start, int end, String text) {
        if (!text.matches("[a-zA-Z]")) {
          super.replaceText(start, end, text);
        }
      }

      @Override
      public void replaceSelection(String text) {
        if (!text.matches("[a-zA-Z]")) {
          super.replaceSelection(text);
        }
      }
}
