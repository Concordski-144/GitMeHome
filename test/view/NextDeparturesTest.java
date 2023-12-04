package view;

import app.Main;
import entity.Route;
import entity.Station;
import entity.SubwayRouteFactory;
import use_case.next_departures.*;
import use_case.plan_a_trip.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class NextDeparturesTest {

    static String message = "";
    static boolean popUpDiscovered = false;

    @org.junit.Test
    public void testNextDeparturesInteractor() {
        NextDeparturesDataAccessInterface userRepository = new NextDeparturesDataAccessInterface() {

            @Override
            public java.util.List<Route> getNextDeparturesByRoute(String id, int time) {
                ArrayList<Route> routes = new ArrayList<Route>();
                SubwayRouteFactory subwayRouteFactory = new SubwayRouteFactory();
                Station[] stations = {};
                Route route = subwayRouteFactory.create("13 Avenue Road", id, stations);

                return null;
            }
        };

        NextDeparturesOutputBoundary successPresenter = new NextDeparturesOutputBoundary() {
            @Override
            public void prepareSuccessView(NextDeparturesOutputData user) {
                assertEquals("", user);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        NextDeparturesInputData inputData = new NextDeparturesInputData("TTC:138961", 1701711649);
        NextDeparturesInputBoundary interactor = new NextDeparturesInteractor(
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

        NextDeparturesView sv = (NextDeparturesView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(3);

        return (JButton) buttons.getComponent(0);
    }


    @org.junit.Test
    public void testNextDeparturesButtonPresent() {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Next Departure"));
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
