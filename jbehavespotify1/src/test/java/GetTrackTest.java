import com.endava.jbehave.TrackSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class GetTrackTest {

    @Steps
    TrackSteps trackSteps;

    @Test
    public void getTrackName() {
        trackSteps.an_id_that_belongs_to_a_dios_le_pido_track("7HJKzIgaNgDgo3p3a2ToR6");
        trackSteps.user_executes_track_api();
        trackSteps.user_gets_a_dios_le_pido_track();
    }

    @Test
    public void getAlbumByTrack() {
        trackSteps.an_id_that_belongs_to_a_dios_le_pido_track("2TFgo98tlwViHIkTalkFvy");
        trackSteps.user_executes_track_api();
        trackSteps.user_gets_un_dia_normal_europe_version_album();
    }

    @Test
    public void getArtistByTrack() {
        trackSteps.an_id_that_belongs_to_a_dios_le_pido_track("2TFgo98tlwViHIkTalkFvy");
        trackSteps.user_executes_track_api();
        trackSteps.user_gets_juanes_name_from_track();
    }

}
