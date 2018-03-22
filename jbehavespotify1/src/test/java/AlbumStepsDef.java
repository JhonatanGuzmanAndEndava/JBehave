import com.endava.jbehave.AlbumSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class AlbumStepsDef
{
    @Steps
    AlbumSteps albumSteps;

    @Given("^an id \"([^\"]*)\" that belongs to Un dia normal album from Juanes$")
    public void anIdThatBelongsToUnDiaNormalAlbumFromJuanes(String arg0) {
        albumSteps.an_id_that_belongs_to_un_dia_normal_album_from_juanes(arg0);
    }

    @When("^user executes album api$")
    public void userExecutesAlbumApi() {
        albumSteps.user_executes_album_api();
    }

    @When("^user executes album track api$")
    public void userExecutesAlbumTrackApi() {
        albumSteps.user_executes_album_track_api();
    }

    @Then("^user gets the name of album$")
    public void userGetsTheNameOfAlbum() {
        albumSteps.user_gets_un_dia_normal_name();
    }

    @Then("^user gets the name of artist$")
    public void userGetsTheNameOfArtist() {
        albumSteps.user_gets_juanes_name_from_album();
    }

    @Then("^user gets the number of songs$")
    public void userGetsTheNumberOfSongs() {
        albumSteps.user_gets_number_of_songs();
    }

    @Then("^user gets the names of each song$")
    public void userGetsTheNamesOfEachSong() {
        albumSteps.user_gets_names_of_each_song();
    }
}
