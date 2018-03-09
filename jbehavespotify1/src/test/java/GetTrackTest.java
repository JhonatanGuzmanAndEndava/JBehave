import com.endava.jbehave.AllSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetTrackTest {

    @Steps
    AllSteps allSteps;

    @Test
    public void getTrackName() {
        allSteps.an_id_that_belongs_to_a_dios_le_pido_track("7HJKzIgaNgDgo3p3a2ToR6");
        allSteps.user_executes_track_api();
        allSteps.user_gets_a_dios_le_pido_track();
    }

    @Test
    public void getAlbumByTrack() {
        allSteps.an_id_that_belongs_to_a_dios_le_pido_track("2TFgo98tlwViHIkTalkFvy");
        allSteps.user_executes_track_api();
        allSteps.user_gets_un_dia_normal_europe_version_album();
    }

    @Test
    public void getArtistByTrack() {
        allSteps.an_id_that_belongs_to_a_dios_le_pido_track("2TFgo98tlwViHIkTalkFvy");
        allSteps.user_executes_track_api();
        allSteps.user_gets_juanes_name_from_track();
    }

}
