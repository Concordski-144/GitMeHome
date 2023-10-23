package use_case.clear_users;

public class ClearInteractor implements ClearInputBoundary {
    final ClearUserDataAccessInterface clearUserDataAccessObject;
    final ClearOutputBoundary clearPresenter;

    public ClearInteractor(ClearUserDataAccessInterface clearUserDataAccessInterface, ClearOutputBoundary clearOutputBoundary) {
        this.clearUserDataAccessObject = clearUserDataAccessInterface;
        this.clearPresenter = clearOutputBoundary;
    }
    @Override
    public void execute() {
        try{
            String[] clearedUsernames = clearUserDataAccessObject.clear();
            ClearOutputData clearOutputData = new ClearOutputData(clearedUsernames);
            clearPresenter.prepareSuccessView(clearOutputData);
        } catch (Exception e) {
            clearPresenter.prepareFailView(e.getMessage());
        }
    }
}
