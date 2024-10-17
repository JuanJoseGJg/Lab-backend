package culturotecs.repository;

import java.util.List;

import culturoteca.model.Video;

public interface VideoRepository {
    List<Video> findAll();
    Video save(Video save);
    List<Video> find(String title);
    List<Video> find(Double fromDuration, Double toDuration);
}
