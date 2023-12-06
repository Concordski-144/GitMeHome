package interface_adapter.get_details;

import interface_adapter.ViewManagerModel;
import interface_adapter.next_departures.NextDeparturesState;
import use_case.get_details.GetDetailsOutputBoundary;
import use_case.get_details.GetDetailsOutputData;

public class GetDetailsPresenter implements GetDetailsOutputBoundary {
    private final GetDetailsViewModel getDetailsViewModel;
    private ViewManagerModel viewManagerModel;

    public GetDetailsPresenter(ViewManagerModel viewManagerModel,
                                  GetDetailsViewModel getDetailsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getDetailsViewModel = getDetailsViewModel;
    }

    public void prepareSuccessView(GetDetailsOutputData response){
        GetDetailsState getDetailsState = getDetailsViewModel.getState();
        getDetailsState.setDetails(response.getDetails());
        this.getDetailsViewModel.setState(getDetailsState);
        getDetailsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(getDetailsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


    public void prepareFailView(String error) {
        GetDetailsState getDetailsState = getDetailsViewModel.getState();
        getDetailsState.setRouteIDError(error);
        getDetailsViewModel.firePropertyChanged();
    }
}
