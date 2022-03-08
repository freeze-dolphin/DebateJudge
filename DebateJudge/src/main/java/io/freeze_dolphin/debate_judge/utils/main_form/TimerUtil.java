package io.freeze_dolphin.debate_judge.utils.main_form;

import io.freeze_dolphin.debate_judge.forms.MainForm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.util.function.Consumer;

public class TimerUtil {

    @RequiredArgsConstructor
    @Getter
    public static class TimerEvaluator {

        private final MainForm form;

        public void set(TimerType type) {
            getRadioBtn(type).setSelected(true);
            getForm().getBtn_timer().setEnabled(true);
            updateText(type, true);
            for (TimerType otr : TimerType.values()) {
                if (otr != type) {
                    getRadioBtn(otr).setSelected(false);
                }
            }
        }

        public void updateText(TimerType type, boolean updateTimerLabel) {
            int sec = getDuration(type);
            if (updateTimerLabel) getForm().getLbl_timer().setText(Util.build_time_exp(sec));
            // getRadioBtn(type).setText(type.getText() + " (" + (int) ((double) sec / 60) + "min)");
            getForm().setCounting_down_from(sec);
        }

        public int getDuration(TimerType type) {
            switch (type) {
                case STATE:
                case CONCLUSION_STATE:
                    return 180;
                case ATTACK:
                    return 120;
                case ATTACK_CONCLUSION:
                    return 90;
                case TEST:
                    return 15;
                default:
                    return 0;
            }
        }

        public JRadioButton getRadioBtn(TimerType type) {
            switch (type) {
                case STATE:
                    return getForm().getRadio_state();
                case ATTACK:
                    return getForm().getRadio_attack();
                case ATTACK_CONCLUSION:
                    return getForm().getRadio_attack_conclusion();
                case CONCLUSION_STATE:
                    return getForm().getRadio_conclusion_state();
                case TEST:
                    return getForm().getRadio_test();
                default:
                    return null;
            }
        }

        public void doForAllRadio(Consumer<JRadioButton> cons) {
            for (TimerType otr : TimerType.values()) {
                cons.accept(getRadioBtn(otr));
            }
        }

    }

    /*
    @RequiredArgsConstructor
    @Getter
    public enum StageType {

        FIRST(1), SECOND(2), THIRD(3), FINAL(4);

        private final int stage;

    }
     */

    @RequiredArgsConstructor
    @Getter
    public enum TimerType {
        STATE("陈词"), ATTACK("攻辩"), ATTACK_CONCLUSION("攻辩总结"), CONCLUSION_STATE("总结陈词"), TEST("测试");

        private final String text;
    }

}
