package io.freeze_dolphin.debate_judge;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lombok.AllArgsConstructor;

import java.awt.*;
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

    public static java.util.Timer startCountingDown(int sec, MainForm form) {
        java.util.Timer tmr = new Timer();
        form.getTimer_progress().setMaximum(1000000);
        tmr.schedule(new CTimerTask(sec, sec, form), 0, 250);
        return tmr;
    }

    @AllArgsConstructor
    private static class CTimerTask extends TimerTask {

        private double sec;
        private final double origSec;
        private final MainForm form;

        @Override
        public void run() {
            form.getLbl_timer().setText(build_time_exp((int) sec));
            if (sec <= 10 && (double) ((int) sec) == sec) {
                playSound(Sound.TIDA);
                form.getLbl_timer().setForeground(new Color(sec % 2 == 0 ? 0 : 255, 0, 0));
            }
            int v = (int) (sec / origSec * 1000000);
            form.getTimer_progress().setValue(v);
            if (sec == 0) {
                System.out.println("Timer Ended.");
                form.getBtn_stop_timer().doClick();
                form.getTimer_progress().setValue(0);
                playSound(Sound.TIME_UP);
                this.cancel();
                return;
            }
            this.sec -= 0.25;
        }
    }

    public static String build_time_exp(int sec) {
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
        try {
            Player plr = new Player(Objects.requireNonNull(App.class.getClassLoader().getResourceAsStream(snd.snd)));
            plrs.add(plr);
            plr.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

}