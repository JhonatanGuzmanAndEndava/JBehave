import com.endava.jbehave.ArtistSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class ArtistStepsDef
{

    @Steps
    ArtistSteps artistSteps;

    @Given("^an id \"([^\"]*)\" that belongs to Juanes artist$")
    public void anIdThatBelongsToJuanesArtist(String arg0) {
        artistSteps.an_id_that_belongs_to_artist(arg0);
    }

    @When("^user executes artist api$")
    public void userExecutesArtistApi() {
        artistSteps.user_executes_artist_api();
    }

    @Then("^user gets Juanes name$")
    public void userGetsJuanesName() {
        artistSteps.user_gets_juanes_name();
    }

    @Given("^an id \"([^\"]*)\" that belongs to an artist$")
    public void anIdThatBelongsToAnArtist(String arg0) {
        artistSteps.an_id_that_belongs_to_artist(arg0);
    }

    @Then("^user gets \"([^\"]*)\" name$")
    public void userGetsName(String arg0) {
        artistSteps.user_gets_artist_name(arg0);
    }
}
