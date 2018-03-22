import com.endava.jbehave.PlayerSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class PlayerStepsDef
{
    @Steps
    PlayerSteps playerSteps;

    @Given("^an uri \"([^\"]*)\" that belongs to A Dios Le Pido track$")
    public void anUriThatBelongsToADiosLePidoTrack(String arg0)  {
        playerSteps.an_uri_that_belongs_to_a_dios_le_pido_track(arg0);
    }

    @When("^user executes Player api$")
    public void userExecutesPlayerApi() {
        playerSteps.user_executes_player_api();
    }

    @Then("^user listens A Dios Le Pido track$")
    public void userListensADiosLePidoTrack() {
        playerSteps.user_listens_a_dios_le_pido_track();
    }
}
