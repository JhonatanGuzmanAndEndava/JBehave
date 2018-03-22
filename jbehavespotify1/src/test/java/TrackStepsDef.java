import com.endava.jbehave.TrackSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class TrackStepsDef
{
    @Steps
    TrackSteps trackSteps;

    @Given("^an id \"([^\"]*)\" that belongs to A Dios Le Pido track$")
    public void anIdThatBelongsToADiosLePidoTrack(String arg0) {
        trackSteps.an_id_that_belongs_to_a_track(arg0);
    }

    @When("^user executes track api$")
    public void userExecutesTrackApi() {
        trackSteps.user_executes_track_api();
    }

    @Then("^user gets A Dios Le Pido track$")
    public void userGetsADiosLePidoTrack() {
        trackSteps.user_gets_a_dios_le_pido_track();
    }

    @Then("^user gets Un Dia Normal album$")
    public void userGetsUnDiaNormalAlbum() {
        trackSteps.user_gets_un_dia_normal_album();
    }

    @Then("^user gets Juanes name from track$")
    public void userGetsJuanesNameFromTrack() {
        trackSteps.user_gets_juanes_name_from_track();
    }

    @Given("^an id \"([^\"]*)\" that belongs to a track$")
    public void anIdThatBelongsToATrack(String arg0) throws Throwable {
        trackSteps.an_id_that_belongs_to_a_track(arg0);
    }

    @Then("^user gets track \"([^\"]*)\"$")
    public void userGetsTrack(String arg0) throws Throwable {
        trackSteps.user_gets_name_from_track(arg0);
    }

    @Then("^user gets artist \"([^\"]*)\"$")
    public void userGetsArtist(String arg0) throws Throwable {
        trackSteps.user_gets_artist_name_from_track(arg0);
    }
}
