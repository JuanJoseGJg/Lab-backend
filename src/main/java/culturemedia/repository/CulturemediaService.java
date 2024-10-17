package culturotecs.repository;

import java.util.List;

import culturoteca.model.Video;

public interface culturemediaRepository {
    List<Video> findAll();
    Video save(Video save);
    Views save(Video save);
}
