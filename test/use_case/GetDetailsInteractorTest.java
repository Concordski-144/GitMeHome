package use_case;

import use_case.get_details.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetDetailsInteractorTest {
    @org.junit.Test
    public void testGetDetailsInteractor() {
        GetDetailsDataAccessInterface userRepository = new GetDetailsDataAccessInterface() {

            @Override
            public ArrayList<String> getDetails(String id, boolean time) {
                ArrayList<String> details = new ArrayList<String>();
                details.add("1 Yonge-University");
                details.add("Active");
                details.add("1");
                details.add("f8c623");
                details.add("stops listed below:");
                details.add("Finch 2023-12-06T23:28");


                return details;
            }
        };

        GetDetailsOutputBoundary successPresenter = new GetDetailsOutputBoundary() {
            @Override
            public void prepareSuccessView(GetDetailsOutputData response) {
                ArrayList<String> details = response.getDetails();
                assertEquals(details.get(0), "1 Yonge-University");
                assertEquals(details.get(1), "Active");
                assertEquals(details.get(2), "1");
                assertEquals(details.get(3), "f8c623");
                assertEquals(details.get(4), "stops listed below:");
                assertEquals(details.get(5), "Finch 2023-12-06T23:28");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        GetDetailsInputData inputData = new GetDetailsInputData("TTC1ON:112634", true);
        GetDetailsInputBoundary interactor = new GetDetailsInteractor(
                userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
