import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.Impl.VideoRepositoryImpl;
import culturemedia.service.CulturemediaService;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.service.Impl.CulturemediaServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VideoRepositoryTest {

    private CulturemediaService culturemediaService;
    private VideoRepository videoRepository;

    @BeforeEach
    void setUp() {
        videoRepository = mock(VideoRepository.class);  // Usamos Mockito para simular el repositorio
        culturemediaService = new CulturemediaServiceImpl(videoRepository);  // Inyectamos el mock
    }
    @Test
    void when_FindByTitle_all_videos_with_matching_title_should_be_returned_successfully() {
        List<Video> videos = List.of(
                new Video("01", "Clic en Título", "Descripción 1", 4.5),
                new Video("02", "Clic en Título", "Descripción 2", 5.0)
        );

        when(videoRepository.find("Clic")).thenReturn(videos);

        List<Video> result = culturemediaService.findByTitle("Clic");
        assertEquals(2, result.size());
        assertEquals("Clic en Título", result.get(0).title());
    }

    @Test
    void when_FindByTitle_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        when(videoRepository.find("Inexistente")).thenReturn(Collections.emptyList());

        Executable executable = () -> culturemediaService.findByTitle("Inexistente");
        assertThrows(VideoNotFoundException.class, executable, "No videos found with title: Inexistente");
    }

    @Test
    void when_FindByDuration_only_videos_between_the_range_should_be_returned_successfully() {
        List<Video> videos = List.of(
                new Video("01", "Título 1", "Descripción 1", 4.5),
                new Video("02", "Título 2", "Descripción 2", 5.0)
        );

        when(videoRepository.find(4.0, 5.0)).thenReturn(videos);

        List<Video> result = culturemediaService.findByDuration(4.0, 5.0);
        assertEquals(2, result.size());
    }

    @Test
    void when_FindByDuration_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        when(videoRepository.find(6.0, 7.0)).thenReturn(Collections.emptyList());

        Executable executable = () -> culturemediaService.findByDuration(6.0, 7.0);
        assertThrows(VideoNotFoundException.class, executable, "No videos found with duration between 6.0 and 7.0");
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() {
        List<Video> videos = videoRepository.findAll( );
        assertEquals(6, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        when(videoRepository.findAll()).thenReturn(Collections.emptyList());

        Executable executable = () -> culturemediaService.findAll();
        assertThrows(VideoNotFoundException.class, executable, "No videos found.");
    }
    @Test
    void when_FindByTitle_only_videos_which_contains_the_word_in_the_title_should_be_returned_successfully() {
        List<Video> videos = videoRepository.find( "Clic" );
        assertEquals(2, videos.size());
    }

    @Test
    void when_FindByTitle_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
        assert(false);
    }

    @Test
    void when_FindByDuration_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
        assert(false);
    }

}