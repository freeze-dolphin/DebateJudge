/*
 * Created by JFormDesigner on Sat Mar 05 15:25:36 CST 2022
 */

package io.freeze_dolphin.debate_judge;

import javazoom.jl.player.Player;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

/**
 * @author Freeze_Dolphin
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Getter
public class MainForm extends JFrame {

    public MainForm() {
        initComponents();
    }

    private Timer tmr;

    private int get_current_sec() {
        return radio_think.isSelected() ? 180 + 60 * (stage - 1) :
                radio_speak.isSelected() ? 300 + 60 * (stage - 1) : radio_test.isSelected() ? 15 : 0;
    }

    private void btn_get_blammer(ActionEvent e) {
        String blr = Util.getRandomBlammer();
        btn_stage.setEnabled(true);
        btn_get_blammer.setEnabled(false);
        lbl_blammer.setText(blr);
        lbl_blammer.setForeground(new Color(255, 0, 0));
        JOptionPane.showMessageDialog(null, new JLabel("<html><h1><font color='red'>" + blr + "</font></h1></html>"), "随机先手: ", JOptionPane.WARNING_MESSAGE);
    }

    private void radio_test(ActionEvent e) {
        radio_speak.setSelected(false);
        radio_think.setSelected(false);
        btn_timer.setEnabled(true);
        lbl_timer.setText("00 : 15");
    }

    private void radio_think(ActionEvent e) {
        radio_speak.setSelected(false);
        radio_test.setSelected(false);
        btn_timer.setEnabled(true);
        lbl_timer.setText(Util.build_time_exp(180 + 60 * (stage - 1)));
    }

    private void radio_speak(ActionEvent e) {
        radio_think.setSelected(false);
        radio_test.setSelected(false);
        btn_timer.setEnabled(true);
        lbl_timer.setText(Util.build_time_exp(300 + 60 * (stage - 1)));
    }

    private void btn_timer(ActionEvent e) {
        btn_mute.doClick();
        btn_stop_timer.setEnabled(true);
        btn_timer.setEnabled(false);
        radio_think.setEnabled(false);
        radio_speak.setEnabled(false);
        radio_test.setEnabled(false);
        timer_progress.setEnabled(true);
        btn_next_stage.setEnabled(false);
        this.tmr = Util.startCountingDown(get_current_sec(), this);
        updateStatus();
    }

    private void updateStatus() {
        if (radio_think.isSelected()) {
            lbl_status.setText("思考");
            lbl_status.setForeground(new Color(250, 65, 100));
        } else if (radio_speak.isSelected()) {
            lbl_status.setText("发言");
            lbl_status.setForeground(new Color(0, 180, 15));
        } else {
            lbl_status.setText("空闲");
            lbl_status.setForeground(new Color(250, 160, 65));
        }
    }

    private void btn_stop_timer(ActionEvent e) {
        timer_progress.setValue(0);
        radio_test.setEnabled(true);
        radio_think.setEnabled(true);
        radio_speak.setEnabled(true);
        btn_stop_timer.setEnabled(false);
        btn_timer.setEnabled(true);
        btn_next_stage.setEnabled(true);
        lbl_timer.setText(Util.build_time_exp(get_current_sec()));
        this.tmr.cancel();
        lbl_status.setText("空闲");
        lbl_status.setForeground(new Color(250, 160, 65));
    }

    private void radio_disable_self_unselect(MouseEvent e) {
        JRadioButton btn = (JRadioButton) e.getSource();
        if (!btn.isSelected() && btn.isEnabled()) {
            btn.setSelected(true);
        }
    }

    private void btn_stage(ActionEvent e) {
        btn_stage.setEnabled(false);
        btn_next_stage.setEnabled(true);
        radio_speak.setEnabled(true);
        radio_test.setEnabled(true);
        radio_think.setEnabled(true);

        lbl_stage.setText("一辩");
        lbl_status.setText("空闲");
        lbl_status.setForeground(new Color(250, 160, 65));
    }

    private void btn_mute(ActionEvent e) {
        for (Player p : Util.plrs) {
            p.close();
        }
    }

    private int stage = 1;

    private void btn_next_stage(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(null, "是否进入下一环节?", "下一环节: ", JOptionPane.OK_CANCEL_OPTION) == 0) {
            btn_stage.setEnabled(false);
            stage += 1;
        }

        radio_think.setText("思考讨论 (" + (stage + 3 - 1) + " min)");
        radio_speak.setText("辩者发言 (" + (stage + 5 - 1) + " min)");

        lbl_timer.setText("-- : --");
        radio_speak.setSelected(false);
        radio_think.setSelected(false);
        radio_test.setSelected(false);

        switch (stage) {
            case 2:
                lbl_stage.setText("二辩");
                break;
            case 3:
                lbl_stage.setText("三辩");
                break;
            case 4:
                lbl_stage.setText("四辩");
                btn_next_stage.setEnabled(false);
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - tajagi5778
        lbl_timer = new JLabel();
        lbl_status = new JLabel();
        sep = new JSeparator();
        lbl_stage = new JLabel();
        timer_progress = new JProgressBar();
        btn_get_blammer = new JButton();
        lbl_blammer = new JLabel();
        btn_stage = new JButton();
        btn_timer = new JButton();
        radio_think = new JRadioButton();
        radio_speak = new JRadioButton();
        btn_stop_timer = new JButton();
        radio_test = new JRadioButton();
        btn_next_stage = new JButton();
        btn_mute = new JButton();

        //======== this ========
        setTitle("Debate Judge");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- lbl_timer ----
        lbl_timer.setText("-- : --");
        lbl_timer.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 130));
        lbl_timer.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_timer.setBorder(new TitledBorder(null, "\u8ba1\u65f6\u5668", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", Font.BOLD, 20), Color.black));
        lbl_timer.setName("Timer");
        contentPane.add(lbl_timer);
        lbl_timer.setBounds(0, 0, 840, 155);

        //---- lbl_status ----
        lbl_status.setText("-");
        lbl_status.setBorder(new TitledBorder(null, "\u72b6\u6001", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", Font.BOLD, 20), Color.black));
        lbl_status.setFont(lbl_status.getFont().deriveFont(lbl_status.getFont().getSize() + 100f));
        lbl_status.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lbl_status);
        lbl_status.setBounds(845, 10, 355, 200);
        contentPane.add(sep);
        sep.setBounds(0, 415, 1330, 15);

        //---- lbl_stage ----
        lbl_stage.setText("-");
        lbl_stage.setAutoscrolls(true);
        lbl_stage.setBorder(new TitledBorder(null, "\u73af\u8282", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", Font.BOLD, 20), Color.black));
        lbl_stage.setFont(lbl_stage.getFont().deriveFont(lbl_stage.getFont().getSize() + 100f));
        lbl_stage.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lbl_stage);
        lbl_stage.setBounds(5, 210, 710, 198);

        //---- timer_progress ----
        timer_progress.setMaximum(10000);
        timer_progress.setEnabled(false);
        contentPane.add(timer_progress);
        timer_progress.setBounds(7, 162, 836, 45);

        //---- btn_get_blammer ----
        btn_get_blammer.setText("\u751f\u6210\u5148\u624b");
        btn_get_blammer.setFont(btn_get_blammer.getFont().deriveFont(btn_get_blammer.getFont().getStyle() | Font.BOLD, btn_get_blammer.getFont().getSize() + 16f));
        btn_get_blammer.addActionListener(e -> btn_get_blammer(e));
        contentPane.add(btn_get_blammer);
        btn_get_blammer.setBounds(625, 425, 200, 50);

        //---- lbl_blammer ----
        lbl_blammer.setText("-");
        lbl_blammer.setBorder(new TitledBorder(null, "\u5148\u624b", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", Font.BOLD, 20), Color.black));
        lbl_blammer.setFont(lbl_blammer.getFont().deriveFont(lbl_blammer.getFont().getStyle() & ~Font.ITALIC, lbl_blammer.getFont().getSize() + 100f));
        lbl_blammer.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lbl_blammer);
        lbl_blammer.setBounds(715, 210, 485, 198);

        //---- btn_stage ----
        btn_stage.setText("\u5f00\u59cb");
        btn_stage.setFont(btn_stage.getFont().deriveFont(btn_stage.getFont().getSize() + 16f));
        btn_stage.setEnabled(false);
        btn_stage.addActionListener(e -> btn_stage(e));
        contentPane.add(btn_stage);
        btn_stage.setBounds(215, 425, 200, 50);

        //---- btn_timer ----
        btn_timer.setText("\u8ba1\u65f6");
        btn_timer.setFont(btn_timer.getFont().deriveFont(btn_timer.getFont().getSize() + 16f));
        btn_timer.setEnabled(false);
        btn_timer.addActionListener(e -> btn_timer(e));
        contentPane.add(btn_timer);
        btn_timer.setBounds(10, 480, 200, 50);

        //---- radio_think ----
        radio_think.setText("\u601d\u8003\u8ba8\u8bba (3 min)");
        radio_think.setBorder(new EtchedBorder());
        radio_think.setBorderPainted(true);
        radio_think.setFont(radio_think.getFont().deriveFont(radio_think.getFont().getSize() + 14f));
        radio_think.setEnabled(false);
        radio_think.addActionListener(e -> radio_think(e));
        radio_think.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radio_disable_self_unselect(e);
            }
        });
        contentPane.add(radio_think);
        radio_think.setBounds(425, 485, 255, 40);

        //---- radio_speak ----
        radio_speak.setText("\u8fa9\u8005\u53d1\u8a00 (5 min)");
        radio_speak.setBorder(new EtchedBorder());
        radio_speak.setBorderPainted(true);
        radio_speak.setFont(radio_speak.getFont().deriveFont(radio_speak.getFont().getSize() + 14f));
        radio_speak.setEnabled(false);
        radio_speak.addActionListener(e -> radio_speak(e));
        radio_speak.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radio_disable_self_unselect(e);
            }
        });
        contentPane.add(radio_speak);
        radio_speak.setBounds(685, 485, 255, 40);

        //---- btn_stop_timer ----
        btn_stop_timer.setText("\u7ec8\u6b62\u8ba1\u65f6");
        btn_stop_timer.setFont(btn_stop_timer.getFont().deriveFont(btn_stop_timer.getFont().getStyle() | Font.BOLD, btn_stop_timer.getFont().getSize() + 16f));
        btn_stop_timer.setEnabled(false);
        btn_stop_timer.addActionListener(e -> btn_stop_timer(e));
        contentPane.add(btn_stop_timer);
        btn_stop_timer.setBounds(215, 480, 200, 50);

        //---- radio_test ----
        radio_test.setText("\u6d4b\u8bd5\u9009\u9879 (5 sec)");
        radio_test.setBorder(new EtchedBorder());
        radio_test.setBorderPainted(true);
        radio_test.setFont(radio_test.getFont().deriveFont(radio_test.getFont().getSize() + 14f));
        radio_test.setEnabled(false);
        radio_test.addActionListener(e -> radio_test(e));
        radio_test.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radio_disable_self_unselect(e);
            }
        });
        contentPane.add(radio_test);
        radio_test.setBounds(945, 485, 255, 40);

        //---- btn_next_stage ----
        btn_next_stage.setText("\u4e0b\u4e00\u73af\u8282");
        btn_next_stage.setFont(btn_next_stage.getFont().deriveFont(btn_next_stage.getFont().getSize() + 16f));
        btn_next_stage.setEnabled(false);
        btn_next_stage.addActionListener(e -> btn_next_stage(e));
        contentPane.add(btn_next_stage);
        btn_next_stage.setBounds(420, 425, 200, 50);

        //---- btn_mute ----
        btn_mute.setText("\u7981\u58f0");
        btn_mute.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
        btn_mute.addActionListener(e -> btn_mute(e));
        contentPane.add(btn_mute);
        btn_mute.setBounds(830, 425, 200, 50);

        contentPane.setPreferredSize(new Dimension(1210, 565));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaradion - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - tajagi5778
    private JLabel lbl_timer;
    private JLabel lbl_status;
    private JSeparator sep;
    private JLabel lbl_stage;
    private JProgressBar timer_progress;
    private JButton btn_get_blammer;
    private JLabel lbl_blammer;
    private JButton btn_stage;
    private JButton btn_timer;
    private JRadioButton radio_think;
    private JRadioButton radio_speak;
    private JButton btn_stop_timer;
    private JRadioButton radio_test;
    private JButton btn_next_stage;
    private JButton btn_mute;
    // JFormDesigner - End of variables declaradion  //GEN-END:variables
}
