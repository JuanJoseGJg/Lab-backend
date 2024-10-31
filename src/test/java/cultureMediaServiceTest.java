import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
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

class CulturemediaServiceTest {

    private CulturemediaService culturemediaService;
    private VideoRepository videoRepository;

    @BeforeEach
    void setUp() {
        videoRepository = mock(VideoRepository.class);  // Usamos Mockito para simular el repositorio
        culturemediaService = new CulturemediaServiceImpl(videoRepository);  // Inyectamos el mock
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() {
        List<Video> videos = List.of(
                new Video("01", "Video 1", "Descripción 1", 4.5),
                new Video("02", "Video 2", "Descripción 2", 5.0),
                new Video("03", "Video 3", "Descripción 3", 3.0)
        );

        when(videoRepository.findAll()).thenReturn(videos);  // Asegúrate de que se simule el comportamiento correcto

        List<Video> result = culturemediaService.findAll();  // Asegúrate de que el método está en la implementación
        assertEquals(3, result.size());  // Verifica que el tamaño sea correcto
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        when(videoRepository.findAll()).thenReturn(Collections.emptyList());

        Executable executable = () -> culturemediaService.findAll();  // Verifica que el método sea el correcto
        assertThrows(VideoNotFoundException.class, executable, "No videos found.");  // Verifica el mensaje de excepción
    }

    @Test
    void when_FindByTitle_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
        when(videoRepository.find("Nonexistent")).thenReturn(Collections.emptyList());

        List<Video> result = culturemediaService.findByTitle("Nonexistent");  // Cambiar a una lista vacía
        assertTrue(result.isEmpty());  // Verifica que la lista esté vacía
    }

    @Test
    void when_FindByDuration_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
        when(videoRepository.find(10.0, 15.0)).thenReturn(Collections.emptyList());

        List<Video> result = culturemediaService.findByDuration(10.0, 15.0);  // Cambiar a una lista vacía
        assertTrue(result.isEmpty());  // Verifica que la lista esté vacía
    }
}