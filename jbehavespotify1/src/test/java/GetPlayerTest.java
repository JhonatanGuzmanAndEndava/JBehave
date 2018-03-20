import com.endava.jbehave.PlayerSteps;
import com.endava.jbehave.TrackSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetPlayerTest {

    @Steps
    PlayerSteps playerSteps;

    @Test
    public void startTrack() {
        playerSteps.an_uri_that_belongs_to_a_dios_le_pido_track("spotify:track:7K2E4MMK5wT4RAUhDhbF28");
        playerSteps.user_executes_player_api();
        playerSteps.user_listens_a_dios_le_pido_track();
    }
}
