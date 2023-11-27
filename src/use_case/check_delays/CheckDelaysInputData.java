package use_case.check_delays;

public class CheckDelaysInputData {

    final private String id;
    final private String query_type; // by line or by station

    public CheckDelaysInputData(String id, String query_type) {
        this.id = id;
        this.query_type = query_type;
    }

    String getId() {
        return id;
    }

    String getType() {
        return query_type;
    }
}
