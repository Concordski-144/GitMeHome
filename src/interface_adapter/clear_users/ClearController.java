package interface_adapter.clear_users;

import use_case.clear_users.ClearInputBoundary;
import use_case.clear_users.ClearInteractor;
public class ClearController {
    final ClearInputBoundary clearUseCaseInteractor;
    public ClearController(ClearInputBoundary clearSignupInteractor) {
        this.clearUseCaseInteractor = clearSignupInteractor;
    }
    public void execute() {
        clearUseCaseInteractor.execute();
    }
}
