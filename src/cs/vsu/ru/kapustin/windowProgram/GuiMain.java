package cs.vsu.ru.kapustin.windowProgram;

import cs.vsu.ru.kapustin.utils.SwingUtils;

import java.util.Locale;

public class GuiMain {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 16);

        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }
}
