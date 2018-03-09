import com.endava.jbehave.AllSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetAlbumTest {

    @Steps
    AllSteps allSteps;

    @Test
    public void getAlbumName() {
        allSteps.an_id_that_belongs_to_un_dia_normal_album_from_juanes("4lekjTG98U3jSYovJJji30");
        allSteps.user_executes_album_api();
        allSteps.user_gets_un_dia_normal_europe_version_name();
    }

    @Test
    public void getArtistByAlbum() {
        allSteps.an_id_that_belongs_to_un_dia_normal_album_from_juanes("4lekjTG98U3jSYovJJji30");
        allSteps.user_executes_album_api();
        allSteps.user_gets_juanes_name_from_album();
    }

}
