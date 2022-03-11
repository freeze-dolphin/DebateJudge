package io.freeze_dolphin.debate_judge.utils.free_debate;

import io.freeze_dolphin.debate_judge.forms.FreeDebate;
import io.freeze_dolphin.debate_judge.utils.main_form.Util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
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
                Util.reduce_timer(getForm().getLbl_timer_pros(), getForm().getPgb_timer_pros());
            }
            if (getForm().getTgb_anti().isSelected()) {
                Util.reduce_timer(getForm().getLbl_timer_anti(), getForm().getPgb_timer_anti());
            }

            if (getForm().getLbl_timer_pros().getText().equals("00 : 00")) {
                getForm().getLbl_timer_pros().setForeground(Color.RED);
            }
            if (getForm().getLbl_timer_anti().getText().equals("00 : 00")) {
                getForm().getLbl_timer_anti().setForeground(Color.RED);
            }
        }

    }

}
