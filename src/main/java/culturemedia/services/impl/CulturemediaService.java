package culturemedia.service;

import culturemedia.model.Video;
import culturemedia.model.View;
import java.util.List;

public interface CulturemediaService {
    List<Video> findAll();  // Encuentra todos los videos
    Video save(Video save); // Guarda un video
    View save(View save);   // Guarda una vista
}