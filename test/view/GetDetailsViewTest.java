package view;


import app.GetDetailsUseCaseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.get_details.GetDetailsPresenter;
import interface_adapter.get_details.GetDetailsViewModel;
import org.junit.Test;
import use_case.get_details.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetDetailsViewTest {
    private final GetDetailsViewModel getDetailsViewModel = new GetDetailsViewModel();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final GetDetailsDataAccessInterface getDetailsDataAccessObject = new GetDetailsDataAccessInterface() {
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

    @Test
    public void testGetDetailsView() {
        GetDetailsView getDetailsView = GetDetailsUseCaseFactory.create(
                viewManagerModel,
                getDetailsViewModel,
                getDetailsDataAccessObject);

        GetDetailsOutputBoundary getDetailsPresenter = new GetDetailsPresenter(viewManagerModel, getDetailsViewModel);
        GetDetailsInputData getDetailsInputData = new GetDetailsInputData("TTC1ON:112634", true);
        GetDetailsInputBoundary getDetailsInteractor = new GetDetailsInteractor(getDetailsDataAccessObject, getDetailsPresenter);
        getDetailsInteractor.execute(getDetailsInputData);

        assert getDetailsView != null;
        assertEquals(getDetailsView.viewName, "get details");
        assertEquals(viewManagerModel.getActiveView(), "get details");
        assertFalse(getDetailsView.isWithDepartureTime());
    }
}
