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
    public void userExecutesSearchSongApi() throws Throwable {
        searchSteps.execute_search_song_api();
    }

    @Then("^system returns a list of songs$")
    public void systemReturnsAListOfSongs() {
        searchSteps.returns_a_list_of_songs();
    }
}
