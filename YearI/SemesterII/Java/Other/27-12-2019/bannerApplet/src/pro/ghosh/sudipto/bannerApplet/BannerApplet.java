package pro.ghosh.sudipto.bannerApplet;

import java.awt.*;
import java.applet.Applet;

/**
 * Write a program that creates a Banner and then creates a thread to scrolls the
 * message in the banner from right to left across the applet’s window.
 *
 * @author sudipto@ghosh.pro University of Delhi
 */
public class BannerApplet extends Applet implements Runnable {
    char character;
    Thread thread = null;
    boolean stopFlag = true;
    String message = " THIS IS A BANNER MESSAGE ";

    @Override
    public void init() {
        super.init();
        this.setForeground(Color.red);
        this.setBackground(Color.yellow);
    }

    @Override
    public void start() {
        this.thread = new Thread(this);
        this.stopFlag = false;
        this.thread.start();
    }

    @Override
    public void run() {
        while (!this.stopFlag) {
            try {
                this.repaint();
                this.thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void stop() {
        super.stop();
        this.stopFlag = true;
        this.thread = null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.character = this.message.charAt(0);
        this.message = this.message.substring(1);
        this.message += this.character;
        g.drawString(this.message, 20, 30);
    }
}
