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
            configuration = objectMapper.readValue(inputStream, GameConfiguration.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
