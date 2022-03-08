package io.freeze_dolphin.debate_judge.utils.free_debate;

import io.freeze_dolphin.debate_judge.forms.FreeDebate;
import io.freeze_dolphin.debate_judge.utils.main_form.Util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.TimerTask;

public class TimerUtil {

    @RequiredArgsConstructor
    @Getter
    public static class Timer {

        private final FreeDebate form;

        public void start() {
            java.util.Timer tmr = new java.util.Timer();
            tmr.schedule(new FDTimerTask(getForm()), 0, 1000);
        }

    }

    @AllArgsConstructor
    @Getter
    private static class FDTimerTask extends TimerTask {

        private final FreeDebate form;

        @Override
        public void run() {
            if (getForm().getTgb_pros().isSelected()) {
                getForm().getLbl_timer_pros().setText(
                        Util.build_time_exp(
                                Util.get_time_from_time_exp(
                                        getForm().getLbl_timer_pros().getText()
                                ) - 1
                        )
                );
                getForm().getPgb_timer_pros().setValue(getForm().getPgb_timer_pros().getValue() - 1);
            }
            if (getForm().getTgb_anti().isSelected()) {
                getForm().getLbl_timer_anti().setText(
                        Util.build_time_exp(
                                Util.get_time_from_time_exp(
                                        getForm().getLbl_timer_anti().getText()
                                ) - 1
                        )
                );
                getForm().getPgb_timer_anti().setValue(getForm().getPgb_timer_anti().getValue() - 1);
            }
        }

    }

}
