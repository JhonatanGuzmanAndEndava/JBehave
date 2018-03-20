import com.endava.jbehave.ArtistSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetArtistTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Steps
    ArtistSteps artistSteps;

    @Test
    public void getNameArtist() throws Throwable {
        artistSteps.an_id_that_belongs_to_juanes_artist("0UWZUmn7sybxMCqrw9tGa7");
        artistSteps.user_executes_artist_api();
        artistSteps.user_gets_juanes_name();
    }

    @Test
    public void getArtistException() throws Throwable {
        artistSteps.a_fake_artist_id("fakeId123");

        exception.expect(NullPointerException.class);
        artistSteps.user_executes_artist_api_it_will_throw_exception();

        exception.expect(NullPointerException.class);
        artistSteps.user_gets_artist_nullpointerexception();

        throw new NullPointerException();
    }

}
