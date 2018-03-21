import com.endava.jbehave.AlbumSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetAlbumTest {

    @Steps
    AlbumSteps albumSteps;

    @Test
    public void getAlbumName() {
        albumSteps.an_id_that_belongs_to_un_dia_normal_album_from_juanes("4lekjTG98U3jSYovJJji30");
        albumSteps.user_executes_album_api();
        albumSteps.user_gets_un_dia_normal_europe_version_name();
    }

    @Test
    public void getArtistByAlbum() {
        albumSteps.an_id_that_belongs_to_un_dia_normal_album_from_juanes("4lekjTG98U3jSYovJJji30");
        albumSteps.user_executes_album_api();
        albumSteps.user_gets_juanes_name_from_album();
    }

    @Test
    public void getNumberOfSongsInJuanesAlbum() {
        albumSteps.an_id_that_belongs_to_un_dia_normal_album_from_juanes("4lekjTG98U3jSYovJJji30");
        albumSteps.user_executes_album_track_api();
        albumSteps.user_gets_number_of_songs_who_is_eleven();
    }

    @Test
    public void getNamesOfSongsInJuanesAlbum() {
        albumSteps.an_id_that_belongs_to_un_dia_normal_album_from_juanes("4lekjTG98U3jSYovJJji30");
        albumSteps.user_executes_album_track_api();
        albumSteps.user_gets_names_of_each_song();
    }

}
