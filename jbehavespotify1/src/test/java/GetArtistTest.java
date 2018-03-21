import com.endava.jbehave.ArtistSteps;
import com.wrapper.spotify.exceptions.detailed.BadRequestException;
import junit.framework.TestCase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

    /*@Test(expected = BadRequestException.class)
    public void getArtistException() throws Throwable{
        artistSteps.a_fake_artist_id("fakeId123");
        artistSteps.user_executes_artist_api_it_will_throw_exception();
        artistSteps.user_gets_artist_nullpointerexception();
    }*/

    /*@Test
    public void getArtistException() {
        artistSteps.a_fake_artist_id("fakeId123");

        try {
            artistSteps.user_executes_artist_api_it_will_throw_exception();
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertThat(e.getMessage(), is("invalid id"));
        }

        try {
            artistSteps.user_gets_artist_nullpointerexception();
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), is("NullPointerException"));
        }
    }*/

    @Test
    public void getArtistException() throws Throwable {
        artistSteps.a_fake_artist_id("fakeId123");

        exception.expect(BadRequestException.class);
        exception.expectMessage("invalid id");
        artistSteps.user_executes_artist_api_it_will_throw_exception();

        TestCase.assertNull(artistSteps.user_gets_artist_nullpointerexception());
    }

}
