package io.freeze_dolphin.debate_judge;

import com.formdev.flatlaf.FlatLightLaf;
import io.freeze_dolphin.debate_judge.forms.MainForm;

import javax.swing.*;

public class App {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        FlatLightLaf.setup();
        UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        MainForm frame = new MainForm();
        frame.setVisible(true);
    }

}
