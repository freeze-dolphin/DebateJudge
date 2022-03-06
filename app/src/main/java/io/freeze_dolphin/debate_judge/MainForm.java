/*
 * Created by JFormDesigner on Sat Mar 05 15:25:36 CST 2022
 */

package io.freeze_dolphin.debate_judge;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Freeze_Dolphin
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Getter
public class MainForm extends JFrame {

    private final TimerUtil.TimerEvaluator tmrEva;
    private final StageUtil.StageEvaluator stgEva;

    public MainForm() {
        initComponents();
        this.tmrEva = new TimerUtil.TimerEvaluator(this);
        this.stgEva = new StageUtil.StageEvaluator(this);

        for (TimerUtil.TimerType otr : TimerUtil.TimerType.values()) {
            getTmrEva().updateText(otr, false);
        }
    }

    private Timer tmr;

    @Setter
    private int counting_down_from = 0;

    private int get_current_sec() {
        return counting_down_from;
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
        getTmrEva().set(TimerUtil.TimerType.TEST);
    }

    private void radio_state(ActionEvent e) {
        getTmrEva().set(TimerUtil.TimerType.STATE);
    }

    private void radio_attack(ActionEvent e) {
        getTmrEva().set(TimerUtil.TimerType.ATTACK);
    }

    private void radio_attack_conclusion(ActionEvent e) {
        getTmrEva().set(TimerUtil.TimerType.ATTACK_CONCLUSION);
    }

    private void radio_final_state(ActionEvent e) {
        getTmrEva().set(TimerUtil.TimerType.CONCLUSION_STATE);
    }

    private void radio_free_debate(ActionEvent e) {
        getTmrEva().set(TimerUtil.TimerType.FREE_DEBATE);
    }

    private void btn_timer(ActionEvent e) {
        btn_mute.doClick();
        btn_stop_timer.setEnabled(true);
        btn_timer.setEnabled(false);
        radio_state.setEnabled(false);
        radio_attack.setEnabled(false);
        radio_test.setEnabled(false);
        timer_progress.setEnabled(true);
        btn_next_stage.setEnabled(false);
        this.tmr = Util.startCountingDown(get_current_sec(), this);
    }

    private void btn_stop_timer(ActionEvent e) {
        timer_progress.setValue(0);

        getStgEva().proceedStage(getCurrentStage());

        btn_stop_timer.setEnabled(false);
        btn_timer.setEnabled(true);
        if (getCurrentStage() != StageUtil.Stage.FINAL) btn_next_stage.setEnabled(true);
        lbl_timer.setText(Util.build_time_exp(get_current_sec()));
        this.tmr.cancel();
    }

    private void radio_disable_self_unselect(MouseEvent e) {
        JRadioButton btn = (JRadioButton) e.getSource();

        updateBorder(false);

        if (!btn.isSelected() && btn.isEnabled()) {
            btn.setSelected(true);
        }
    }

    private void updateBorder(boolean clear) {
        getTmrEva().doForAllRadio((r) -> {
            r.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.lightGray, Color.lightGray));
            if (r.isSelected() && !clear) r.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.RED, Color.RED));
        });
    }

    private void btn_stage(ActionEvent e) {
        getStgEva().proceedStage(getCurrentStage());
        updateBorder(true);
    }

    private void btn_mute(ActionEvent e) {
        Util.mute(this);
    }

    private int stage = 1;

    private StageUtil.Stage getCurrentStage() {
        switch (stage) {
            case 1: return StageUtil.Stage.FIRST;
            case 2: return StageUtil.Stage.SECOND;
            case 3: return StageUtil.Stage.THIRD;
            case 4: return StageUtil.Stage.FREE;
            case 5: return StageUtil.Stage.FINAL;
            default: return StageUtil.Stage.NULL;
        }
    }

    private void btn_next_stage(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(null, "是否进入下一环节?", "下一环节: ", JOptionPane.OK_CANCEL_OPTION) == 0) {
            stage += 1;
        } else return;


        updateBorder(true);
        getStgEva().proceedStage(getCurrentStage());
        getTmrEva().doForAllRadio((r) -> r.setSelected(false));
        getBtn_timer().setEnabled(false);
    }

    @SuppressWarnings("Convert2MethodRef")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - tajagi5778
        lbl_timer = new JLabel();
        sep = new JSeparator();
        lbl_stage = new JLabel();
        timer_progress = new JProgressBar();
        btn_get_blammer = new JButton();
        lbl_blammer = new JLabel();
        btn_stage = new JButton();
        btn_timer = new JButton();
        radio_state = new JRadioButton();
        radio_attack = new JRadioButton();
        btn_stop_timer = new JButton();
        radio_test = new JRadioButton();
        btn_next_stage = new JButton();
        btn_mute = new JButton();
        radio_attack_conclusion = new JRadioButton();
        radio_free_debate = new JRadioButton();
        radio_conclusion_state = new JRadioButton();

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
        lbl_timer.setBounds(0, 0, 1200, 155);
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
        lbl_stage.setBounds(5, 210, 840, 198);

        //---- timer_progress ----
        timer_progress.setMaximum(10000);
        timer_progress.setEnabled(false);
        contentPane.add(timer_progress);
        timer_progress.setBounds(7, 162, 1191, 45);

        //---- btn_get_blammer ----
        btn_get_blammer.setText("\u751f\u6210\u5148\u624b");
        btn_get_blammer.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
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
        lbl_blammer.setBounds(845, 210, 355, 198);

        //---- btn_stage ----
        btn_stage.setText("\u5f00\u59cb");
        btn_stage.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
        btn_stage.setEnabled(false);
        btn_stage.addActionListener(e -> btn_stage(e));
        contentPane.add(btn_stage);
        btn_stage.setBounds(215, 425, 200, 50);

        //---- btn_timer ----
        btn_timer.setText("\u8ba1\u65f6");
        btn_timer.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
        btn_timer.setEnabled(false);
        btn_timer.addActionListener(e -> btn_timer(e));
        contentPane.add(btn_timer);
        btn_timer.setBounds(10, 480, 200, 50);

        //---- radio_state ----
        radio_state.setText("\u9648\u8bcd (3 min)");
        radio_state.setBorder(new EtchedBorder(Color.lightGray, Color.lightGray));
        radio_state.setBorderPainted(true);
        radio_state.setFont(new Font(Font.DIALOG, Font.PLAIN, 26));
        radio_state.setEnabled(false);
        radio_state.addActionListener(e -> radio_state(e));
        radio_state.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radio_disable_self_unselect(e);
            }
        });
        contentPane.add(radio_state);
        radio_state.setBounds(425, 485, 180, 40);

        //---- radio_attack ----
        radio_attack.setText("\u653b\u8fa9 (2 min)");
        radio_attack.setBorder(new EtchedBorder(Color.lightGray, Color.lightGray));
        radio_attack.setBorderPainted(true);
        radio_attack.setFont(new Font(Font.DIALOG, Font.PLAIN, 26));
        radio_attack.setEnabled(false);
        radio_attack.addActionListener(e -> radio_attack(e));
        radio_attack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radio_disable_self_unselect(e);
            }
        });
        contentPane.add(radio_attack);
        radio_attack.setBounds(610, 485, 180, 40);

        //---- btn_stop_timer ----
        btn_stop_timer.setText("\u7ec8\u6b62\u8ba1\u65f6");
        btn_stop_timer.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
        btn_stop_timer.setEnabled(false);
        btn_stop_timer.addActionListener(e -> btn_stop_timer(e));
        contentPane.add(btn_stop_timer);
        btn_stop_timer.setBounds(215, 480, 200, 50);

        //---- radio_test ----
        radio_test.setText("\u6d4b\u8bd5 (15 sec)");
        radio_test.setBorder(new EtchedBorder(Color.lightGray, Color.lightGray));
        radio_test.setBorderPainted(true);
        radio_test.setFont(new Font(Font.DIALOG, Font.PLAIN, 26));
        radio_test.setEnabled(false);
        radio_test.addActionListener(e -> radio_test(e));
        radio_test.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radio_disable_self_unselect(e);
            }
        });
        contentPane.add(radio_test);
        radio_test.setBounds(1020, 530, 180, 40);

        //---- btn_next_stage ----
        btn_next_stage.setText("\u4e0b\u4e00\u73af\u8282");
        btn_next_stage.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
        btn_next_stage.setEnabled(false);
        btn_next_stage.addActionListener(e -> btn_next_stage(e));
        contentPane.add(btn_next_stage);
        btn_next_stage.setBounds(420, 425, 200, 50);

        //---- btn_mute ----
        btn_mute.setText("\u7981\u58f0");
        btn_mute.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
        btn_mute.addActionListener(e -> btn_mute(e));
        contentPane.add(btn_mute);
        btn_mute.setBounds(830, 425, 200, 50);

        //---- radio_attack_conclusion ----
        radio_attack_conclusion.setText("\u653b\u8fa9\u5c0f\u7ed3 (1.5 min)");
        radio_attack_conclusion.setBorder(new EtchedBorder(Color.lightGray, Color.lightGray));
        radio_attack_conclusion.setBorderPainted(true);
        radio_attack_conclusion.setFont(new Font(Font.DIALOG, Font.PLAIN, 26));
        radio_attack_conclusion.setEnabled(false);
        radio_attack_conclusion.addActionListener(e -> radio_attack_conclusion(e));
        radio_attack_conclusion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radio_disable_self_unselect(e);
            }
        });
        contentPane.add(radio_attack_conclusion);
        radio_attack_conclusion.setBounds(795, 485, 245, 40);

        //---- radio_free_debate ----
        radio_free_debate.setText("\u81ea\u7531\u8fa9\u8bba (8 min)");
        radio_free_debate.setBorder(new EtchedBorder(Color.lightGray, Color.lightGray));
        radio_free_debate.setBorderPainted(true);
        radio_free_debate.setFont(new Font(Font.DIALOG, Font.PLAIN, 26));
        radio_free_debate.setEnabled(false);
        radio_free_debate.addActionListener(e -> radio_free_debate(e));
        radio_free_debate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radio_disable_self_unselect(e);
            }
        });
        contentPane.add(radio_free_debate);
        radio_free_debate.setBounds(425, 530, 220, 40);

        //---- radio_conclusion_state ----
        radio_conclusion_state.setText("\u603b\u7ed3\u9648\u8bcd (3 min)");
        radio_conclusion_state.setBorder(new EtchedBorder(Color.lightGray, Color.lightGray));
        radio_conclusion_state.setBorderPainted(true);
        radio_conclusion_state.setFont(new Font(Font.DIALOG, Font.PLAIN, 26));
        radio_conclusion_state.setEnabled(false);
        radio_conclusion_state.setActionCommand("\u603b\u7ed3\u9648\u8bcd (3 min)");
        radio_conclusion_state.addActionListener(e -> radio_final_state(e));
        radio_conclusion_state.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radio_disable_self_unselect(e);
            }
        });
        contentPane.add(radio_conclusion_state);
        radio_conclusion_state.setBounds(650, 530, 220, 40);

        contentPane.setPreferredSize(new Dimension(1210, 605));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaradion - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - tajagi5778
    private JLabel lbl_timer;
    private JSeparator sep;
    private JLabel lbl_stage;
    private JProgressBar timer_progress;
    private JButton btn_get_blammer;
    private JLabel lbl_blammer;
    private JButton btn_stage;
    private JButton btn_timer;
    private JRadioButton radio_state;
    private JRadioButton radio_attack;
    private JButton btn_stop_timer;
    private JRadioButton radio_test;
    private JButton btn_next_stage;
    private JButton btn_mute;
    private JRadioButton radio_attack_conclusion;
    private JRadioButton radio_free_debate;
    private JRadioButton radio_conclusion_state;
    // JFormDesigner - End of variables declaradion  //GEN-END:variables
}
