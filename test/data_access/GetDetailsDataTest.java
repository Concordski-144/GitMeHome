package data_access;

import use_case.get_details.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetDetailsDataTest {

    @org.junit.Test
    public void testGetDetailsData() {
        GetDetailsDataAccessObject dataAccessObject = new GetDetailsDataAccessObject();

        GetDetailsOutputBoundary successPresenter = new GetDetailsOutputBoundary() {
            @Override
            public void prepareSuccessView(GetDetailsOutputData response) {
                ArrayList<String> details = response.getDetails();
                assertEquals(details.get(0), "1 Yonge–University");
                assertEquals(details.get(1), "Active");
                assertEquals(details.get(2), "1");
                assertEquals(details.get(3), "f8c623");
                assertEquals(details.get(4), "stops listed below:");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        GetDetailsInputData inputData = new GetDetailsInputData("TTC1ON:112634", true);
        GetDetailsInputBoundary interactor = new GetDetailsInteractor(
                dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }
}
