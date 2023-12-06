package use_case.get_details;

public class GetDetailsOutputData {
    private final String name;
    private final String type;

    private boolean useCaseFailed;

    public GetDetailsOutputData(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
