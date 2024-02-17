package dominion.core.initialisation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JSONLoadedConfigurationManager extends GameConfigurationManager {
    public JSONLoadedConfigurationManager(GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void initialiseGame() {
        try (InputStream inputStream = JSONLoadedConfigurationManager.class.getResourceAsStream("/configuration/LoadedConfiguration.json")) {
            // Step 1: Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            // Step 2: Read JSON file and map to Java object
            configuration = objectMapper.readValue(inputStream, GameConfiguration.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
