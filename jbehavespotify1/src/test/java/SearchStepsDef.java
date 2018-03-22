import com.endava.jbehave.SearchSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class SearchStepsDef
{
    @Steps
    SearchSteps searchSteps;

    @Given("^a song keyword \"([^\"]*)\"$")
    public void aSongKeyword(String arg0) {
        searchSteps.search_song_by_keyword(arg0);
    }

    @When("^user executes search song api$")
    public void userExecutesSearchSongApi() {
        searchSteps.execute_search_song_api();
    }

    @Then("^system returns a list of songs$")
    public void systemReturnsAListOfSongs() {
        searchSteps.returns_a_list_of_songs();
    }

    @Given("^an artist keyword \"([^\"]*)\"$")
    public void anArtistKeyword(String arg0) {
        searchSteps.search_artist_by_keyword(arg0);
    }

    @Given("^an album keyword \"([^\"]*)\"$")
    public void anAlbumKeyword(String arg0) {
        searchSteps.search_album_by_keyword(arg0);
    }

    @When("^user executes search album api$")
    public void userExecutesSearchAlbumApi() {
        searchSteps.execute_search_album_api();
    }

    @Then("^system returns a list of album$")
    public void systemReturnsAListOfAlbum() {
        searchSteps.returns_a_list_of_albums();
    }

    @When("^user executes search artist api$")
    public void userExecutesSearchArtistApi() {
        searchSteps.execute_search_artist_api();
    }

    @Then("^system returns a list of artist$")
    public void systemReturnsAListOfArtist() {
        searchSteps.returns_a_list_of_artist();
    }
}
