package interface_adapter.get_details;

import interface_adapter.ViewManagerModel;
import use_case.get_details.GetDetailsOutputData;

public class GetDetailsPresenter {
    private final GetDetailsViewModel getDetailsViewModel;
    private ViewManagerModel viewManagerModel;

    public GetDetailsPresenter(ViewManagerModel viewManagerModel,
                                  GetDetailsViewModel getDetailsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getDetailsViewModel = getDetailsViewModel;
    }

    public void prepareSuccessView(GetDetailsOutputData response){

    }
}
