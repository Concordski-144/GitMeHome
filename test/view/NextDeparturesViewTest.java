package view;

import app.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class NextDeparturesViewTest {

    static String message = "";
    static boolean popUpDiscovered = false;


    public JButton getButton() {
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

        NextDeparturesView sv = (NextDeparturesView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(3);

        return (JButton) buttons.getComponent(0);
    }


    public JButton getMainMenuButton() {
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

        MainMenuView sv = (MainMenuView) jp2.getComponent(5);

        JPanel buttons = (JPanel) sv.getComponent(1);

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

        NextDeparturesView sv = (NextDeparturesView) jp2.getComponent(0);

        LabelTextPanel textPanel = (LabelTextPanel) sv.getComponent(1);

        return (JTextField) textPanel.getComponent(1);
    }



    @org.junit.Test
    public void testNextDeparturesButtonPresent() {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Next Departure"));
    }


    @org.junit.Test
    public void testNextDeparturesPopUpShown() {

        popUpDiscovered = false;

        Main.main(null);
        JFrame app = null;

        JButton mainButton = getMainMenuButton();
        JTextField id = getIdTextField();
        JButton button = getButton();


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
                            NextDeparturesViewTest.message = s;
                            NextDeparturesViewTest.popUpDiscovered = true;

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
