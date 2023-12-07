package view;

import app.Main;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class CheckDelaysViewTest {

    static String message = "";
    static boolean popUpDiscovered = false;


    public JButton getButton(int componentIndex) {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        CheckDelaysView sv = (CheckDelaysView) jp2.getComponent(2);

        JPanel buttons = (JPanel) sv.getComponent(2);

        return (JButton) buttons.getComponent(componentIndex);
    }


    public JButton getCheckDelaysButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        CheckDelaysView sv = (CheckDelaysView) jp2.getComponent(2);

        JPanel buttons = (JPanel) sv.getComponent(2);

        return (JButton) buttons.getComponent(0);
    }


    public JTextField getIdTextField() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        CheckDelaysView sv = (CheckDelaysView) jp2.getComponent(0);

        LabelTextPanel textPanel = (LabelTextPanel) sv.getComponent(1);

        return (JTextField) textPanel.getComponent(1);
    }



    @Test
    public void testCheckDelaysButton1Present() {
        Main.main(null);
        JButton button = getButton(0);
        assert(button.getText().equals("Check Delays by StationID"));
    }

    @Test
    public void testCheckDelaysButton2Present() {
        Main.main(null);
        JButton button = getButton(1);
        assert(button.getText().equals("Check Delays by RouteID"));
    }

    @Test
    public void testCheckDelaysButton3Present() {
        Main.main(null);
        JButton button = getButton(2);
        assert(button.getText().equals("Cancel"));
    }


    // @Test disabled temporarily
    public void testCheckDelaysPopUpShown() {

        popUpDiscovered = false;

        Main.main(null);
        JFrame app = null;

        JButton mainButton = getCheckDelaysButton();
        JTextField id = getIdTextField();
        JButton button = getButton(0);


        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        //click the button
        mainButton.doClick();
        id.setText("TTC:138961");
        KeyEvent key = new KeyEvent(id, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0,  KeyEvent.VK_UNDEFINED);
        id.getKeyListeners()[0].keyTyped(key);
        button.doClick();

        // will continue execution here after the JDialog is closed

        // confirm a popUp was discovered
        assert(popUpDiscovered);
        System.out.println("popup was detected successfully.");

    }


    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog)window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            CheckDelaysViewTest.message = s;
                            CheckDelaysViewTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(5000, close);
        t.setRepeats(false);
        return t;
    }
}
