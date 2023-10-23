package interface_adapter.clear_users;

import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearOutputData;
import interface_adapter.ViewManagerModel;

public class ClearPresenter implements ClearOutputBoundary {
    private final ClearViewModel clearViewModel;
    private ViewManagerModel viewManagerModel;

    public ClearPresenter(ClearViewModel clearViewModel, ViewManagerModel viewManagerModel) {
        this.clearViewModel = clearViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(ClearOutputData clearOutputData) {
        ClearState clearState = clearViewModel.getState();
        clearViewModel.setState(clearState);
        clearViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(clearViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        String displayStr = "";
        for (String x:clearOutputData.usernames) {
            displayStr += x+"\n";
        }
        clearViewModel.setClearedUsers(displayStr);
    }

    @Override
    public void prepareFailView(String error) {
        clearViewModel.setClearedUsers("Failed to delete." + error);
    }
}
