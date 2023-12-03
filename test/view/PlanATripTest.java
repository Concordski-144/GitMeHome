package view;

import app.Main;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.plan_a_trip.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PlanATripTest {

    static String message = "";
    static boolean popUpDiscovered = false;

    @org.junit.Test
    public void testPlanATripInteractor() {
        PlanATripDataAccessInterface userRepository = new PlanATripDataAccessInterface() {
            @Override
            public HashMap<String, Object> getPlanForTrip(String fromPlace, String toPlace) {
                HashMap<String, Object> output = new HashMap<>();
                output.put("fromLat", 43.66829753052834);
                output.put("fromLon", -79.3998952498581);
                output.put("fromName", "");
                output.put("toLat", 43.66199329497653);
                output.put("toLon", -79.3998952498581);
                output.put("toName", "");
                output.put("duration", 773);
                output.put("transitTime", 240);
                output.put("walkTime", 533);
                HashMap<String, Object> temp = new HashMap<>();
                temp.put("duration", 377);
                temp.put("mode", "WALK");
                temp.put("legsFromLat", 43.66824357130821);
                temp.put("legsFromLon", -79.39987726345139);
                temp.put("legsFromName", "");
                temp.put("legsToLat", 43.66718237331233);
                temp.put("legsToLon", -79.40360944284369);
                temp.put("legsToName", "");
                return output;
            }
        };

        PlanATripOutputBoundary successPresenter = new PlanATripOutputBoundary() {
            @Override
            public void prepareSuccessView(PlanATripOutputData user) {
                assertEquals(773, user.getPlanMap().get("duration"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        PlanATripInputData inputData = new PlanATripInputData("43.6683, -79.3999", "43.6620, -79.3999");
        PlanATripInputBoundary interactor = new PlanATripInteractor(
                userRepository, successPresenter);
        interactor.execute(inputData);
    }


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

        PlanATripView sv = (PlanATripView) jp2.getComponent(1);

        JPanel buttons = (JPanel) sv.getComponent(3);

        return (JButton) buttons.getComponent(0);
    }


    @org.junit.Test
    public void testPlanATripButtonPresent() {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Plan your trip"));
    }


    @org.junit.Test
    public void testClearUsersPopUpShown() {

        popUpDiscovered = false;

        Main.main(null);
        JFrame app = null;

        JButton button = getButton();


        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        //click the button
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
                            PlanATripTest.message = s;
                            PlanATripTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }
}
