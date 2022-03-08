/*
 * Created by JFormDesigner on Tue Mar 08 16:20:27 CST 2022
 */

package io.freeze_dolphin.debate_judge.forms;

import java.awt.event.*;

import io.freeze_dolphin.debate_judge.utils.free_debate.TimerUtil;
import lombok.Getter;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author gagalin299
 */

@Getter
public class FreeDebate extends JFrame {

    private final TimerUtil.Timer tmr = new TimerUtil.Timer(this);

    public FreeDebate() {
        initComponents();
        getTmr().start();
    }

    private void tgb_toggle(ActionEvent e) {

    }

    @SuppressWarnings("Convert2MethodRef")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - gagalin299
        lbl_timer_anti = new JLabel();
        lbl_timer_pros = new JLabel();
        pgb_timer_pros = new JProgressBar();
        pgb_timer_anti = new JProgressBar();
        sep = new JSeparator();
        tgb_pros = new JToggleButton();
        vsep = new JSeparator();
        tgb_anti = new JToggleButton();

        //======== this ========
        setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DebateJudge - \u81ea\u7531\u8fa9\u8bba");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- lbl_timer_anti ----
        lbl_timer_anti.setText("04 : 00");
        lbl_timer_anti.setFont(new Font("Lucida Console", Font.PLAIN, 105));
        lbl_timer_anti.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_timer_anti.setBorder(new TitledBorder(null, "\u53cd\u65b9\u8ba1\u65f6", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
            new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 24), Color.black));
        contentPane.add(lbl_timer_anti);
        lbl_timer_anti.setBounds(533, 10, 512, 240);

        //---- lbl_timer_pros ----
        lbl_timer_pros.setText("04 : 00");
        lbl_timer_pros.setFont(new Font("Lucida Console", Font.PLAIN, 105));
        lbl_timer_pros.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_timer_pros.setBorder(new TitledBorder(null, "\u6b63\u65b9\u8ba1\u65f6", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
            new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 24), Color.black));
        contentPane.add(lbl_timer_pros);
        lbl_timer_pros.setBounds(15, 10, 512, 240);

        //---- pgb_timer_pros ----
        pgb_timer_pros.setMaximum(240);
        pgb_timer_pros.setValue(240);
        contentPane.add(pgb_timer_pros);
        pgb_timer_pros.setBounds(20, 255, 505, 40);

        //---- pgb_timer_anti ----
        pgb_timer_anti.setMaximum(240);
        pgb_timer_anti.setValue(240);
        contentPane.add(pgb_timer_anti);
        pgb_timer_anti.setBounds(535, 255, 505, 40);
        contentPane.add(sep);
        sep.setBounds(0, 300, 1070, 2);

        //---- tgb_pros ----
        tgb_pros.setText("\u8ba1\u65f6");
        tgb_pros.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 32));
        tgb_pros.addActionListener(e -> tgb_toggle(e));
        contentPane.add(tgb_pros);
        tgb_pros.setBounds(25, 308, 480, 80);

        //---- vsep ----
        vsep.setOrientation(SwingConstants.VERTICAL);
        contentPane.add(vsep);
        vsep.setBounds(529, 0, vsep.getPreferredSize().width, 500);

        //---- tgb_anti ----
        tgb_anti.setText("\u8ba1\u65f6");
        tgb_anti.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 32));
        tgb_anti.addActionListener(e -> tgb_toggle(e));
        contentPane.add(tgb_anti);
        tgb_anti.setBounds(555, 308, 480, 80);

        contentPane.setPreferredSize(new Dimension(1065, 395));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - gagalin299
    private JLabel lbl_timer_anti;
    private JLabel lbl_timer_pros;
    private JProgressBar pgb_timer_pros;
    private JProgressBar pgb_timer_anti;
    private JSeparator sep;
    private JToggleButton tgb_pros;
    private JSeparator vsep;
    private JToggleButton tgb_anti;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
