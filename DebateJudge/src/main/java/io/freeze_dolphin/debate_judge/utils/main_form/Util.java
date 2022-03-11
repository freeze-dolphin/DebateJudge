package io.freeze_dolphin.debate_judge.utils.main_form;

import io.freeze_dolphin.debate_judge.App;
import io.freeze_dolphin.debate_judge.forms.MainForm;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class Util {

    public static String getRandomBlammer() {
        String t = "正方";
        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            t = "反方";
        }
        return t;
    }

    public static void mute(MainForm form) {
        java.util.Timer tmr = new Timer();
        form.getBtn_mute().setEnabled(false);
        for (Player p : Util.plrs) {
            p.close();
        }
        tmr.schedule(new MTimerTask(form), 0, 2000);
    }

    @RequiredArgsConstructor
    @Getter
    private static class MTimerTask extends TimerTask {

        private final MainForm form;

        @Override
        public void run() {
            getForm().getBtn_mute().setEnabled(true);
            this.cancel();
        }
    }

    public static void reduce_timer_lbl(JLabel lbl) {
        lbl.setText(
                build_time_exp(
                        get_time_from_time_exp(
                                lbl.getText()
                        ) - 1
                )
        );
    }

    public static void reduce_timer_prog(JProgressBar jpb) {
        if (jpb.isIndeterminate()) jpb.setIndeterminate(false);

        jpb.setValue(jpb.getValue() - 1);
    }

    public static void reduce_timer(JLabel lbl, JProgressBar jpb) {
        reduce_timer(lbl, jpb, -1, false);
    }

    public static boolean reduce_timer(JLabel lbl, JProgressBar jpb, int warnSec, boolean flash) {
        reduce_timer_lbl(lbl);
        reduce_timer_prog(jpb);

        if (warnSec > 0) {
            int remain = get_time_from_time_exp(lbl.getText());

            if (remain == 0) {
                playSound(Sound.TIME_UP);
                return true;
            } else if (remain <= warnSec) {
                playSound(Sound.TIDA);
                lbl.setForeground(remain % 2 == 0 ? Color.BLACK : Color.RED);
            }

        }

        return false;
    }

    public static java.util.Timer startCountingDown(int sec, JLabel lbl, JProgressBar jpb) {
        java.util.Timer tmr = new Timer();
        System.out.println(sec);
        jpb.setMaximum(sec);
        jpb.setValue(sec);
        lbl.setText(build_time_exp(sec));
        tmr.schedule(new CTimerTask(lbl, jpb), 0, 1000);
        return tmr;
    }

    @AllArgsConstructor
    @Getter
    private static class CTimerTask extends TimerTask {

        private final JLabel lbl;
        private final JProgressBar jpb;

        @Override
        public void run() {

            if (reduce_timer(getLbl(), getJpb(), 30, true)) this.cancel();

        }
    }

    public static int get_time_from_time_exp(String exp) {
        String[] spd = exp.split(" : ");
        String left = spd[0];
        String right = spd[1];
        return Integer.parseInt(left) * 60 + Integer.parseInt(right);
    }

    public static String build_time_exp(int sec) {
        if (sec <= 0) return "00 : 00";
        int mm = sec / 60 % 60;
        int ss = sec % 60;
        String stm = String.valueOf(mm);
        String sts = String.valueOf(ss);
        if (stm.length() != 2) {
            stm = "0" + stm;
        }
        if (sts.length() != 2) {
            sts = "0" + sts;
        }
        return stm + " : " + sts;
    }

    public enum Sound {

        TIME_UP("alarm.mp3"),
        TIDA("tida.mp3");

        private final String snd;

        Sound(String snd) {
            this.snd = snd;
        }

    }

    public static java.util.List<Player> plrs = new ArrayList<>();

    public static void playSound(Sound snd) {

        if (System.getenv().containsKey("DISABLE_SOUND")) {
            System.out.println("Played sound: " + snd.name());
            return;
        }

        try {
            Player plr = new Player(Objects.requireNonNull(App.class.getClassLoader().getResourceAsStream(snd.snd)));
            plrs.add(plr);
            plr.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

}
