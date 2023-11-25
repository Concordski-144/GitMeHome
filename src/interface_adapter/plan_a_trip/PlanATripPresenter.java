package interface_adapter.plan_a_trip;

import interface_adapter.ViewManagerModel;
import use_case.plan_a_trip.PlanATripOutputBoundary;
import use_case.plan_a_trip.PlanATripOutputData;

public class PlanATripPresenter  implements PlanATripOutputBoundary {

    private final PlanATripViewModel planATripViewModel;
    private ViewManagerModel viewManagerModel;

    public PlanATripPresenter(ViewManagerModel viewManagerModel,
                                  PlanATripViewModel planATripViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.planATripViewModel = planATripViewModel;
    }

    @Override
    public void prepareSuccessView(PlanATripOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {
        PlanATripState planATripState = planATripViewModel.getState();
        planATripState.setStationIDError(error);
        planATripViewModel.firePropertyChanged();
    }
}
