package data_access;

import use_case.longitude_latitude.LonLatInputData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LonLatDataAccessObject {
    private String path;
    public LonLatDataAccessObject(String path) {
        this.path = path;
    }

    public HashMap<String, LonLatInputData> getCoordinates() {
        HashMap<String, LonLatInputData> coordinatesMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                // Assuming the first column is a key, and the rest are coordinates
                String key = values[0].trim();
                double longitude = Double.parseDouble(values[1].trim());
                double latitude = Double.parseDouble(values[2].trim());

                LonLatInputData coordinates = new LonLatInputData(key);

                coordinatesMap.put(key, coordinates);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coordinatesMap;
    }

}
