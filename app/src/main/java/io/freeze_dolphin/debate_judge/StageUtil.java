package io.freeze_dolphin.debate_judge;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;

public class StageUtil {

    @RequiredArgsConstructor
    @Getter
    public enum Stage {
        FIRST("一辩"),
        SECOND("二辩"),
        THIRD("三辩"),
        FREE("自由辩论"),
        FINAL("四辩"),

        NULL("");

        private final String name;

    }

    @RequiredArgsConstructor
    @Getter
    public static class StageEvaluator {

        private final MainForm form;

        public void proceedStage(Stage type) {
            getForm().getLbl_stage().setText(type.getName());
            getForm().getLbl_timer().setText("-- : --");
            switch (type) {
                case FIRST:
                    autoSetEnable(new JComponent[]{
                            getForm().getBtn_next_stage(),
                            getForm().getRadio_attack(),
                            getForm().getRadio_state(),
                            getForm().getRadio_test()
                    }, true);

                    getForm().getBtn_stage().setEnabled(false);
                    break;
                case SECOND:
                    getForm().getLbl_blammer().setFont(new Font("Dialog", Font.ITALIC, 100));
                    getForm().getLbl_blammer().setForeground(Color.lightGray);

                    autoSetEnable(new JComponent[]{
                            getForm().getBtn_next_stage(),
                            getForm().getRadio_attack(),
                            getForm().getRadio_test()
                    }, true);

                    getForm().getRadio_state().setEnabled(false);
                    break;
                case THIRD:
                    getForm().getRadio_attack_conclusion().setEnabled(true);
                    getForm().getRadio_attack().setEnabled(true);
                    break;
                case FREE:
                    getForm().getRadio_free_debate().setEnabled(true);
                    autoSetEnable(new JComponent[]{
                            getForm().getRadio_attack(),
                            getForm().getRadio_attack_conclusion()
                    }, false);
                    break;
                case FINAL:
                    getForm().getRadio_free_debate().setEnabled(false);
                    getForm().getRadio_conclusion_state().setEnabled(true);
                    getForm().getBtn_next_stage().setEnabled(false);
                default:
            }
        }

        private void autoSetEnable(JComponent[] jcs, boolean bool) {
            for (JComponent jc : jcs) {
                jc.setEnabled(bool);
            }
        }

    }

}