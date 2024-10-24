package culturemedia.service.Impl;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.service.CulturemediaService;
import culturemedia.exception.VideoNotFoundException;
import java.util.List;

public class CulturemediaServiceImpl implements CulturemediaService {


    private final VideoRepository videoRepository;

    public CulturemediaServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> findAll() {
        return List.of();  // Devuelve una lista vacía de videos
    }

    @Override
    public Video save(Video video) {
        System.out.println("Saving video: " + video);
        return video;  // Aquí se implementa la lógica real de guardado
    }

    @Override
    public View save(View view) {
        System.out.println("Saving view: " + view);
        return view;  // Implementa la lógica real de guardado para vista
        @Override
        public List<Video> findByTitle(String title) {
            List<Video> videos = videoRepository.find(title);
            if (videos.isEmpty()) {
                throw new VideoNotFoundException("No videos found with title: " + title);
            }
            return videos;
        }

        @Override
        public List<Video> findByDuration(double minDuration, double maxDuration) {
            List<Video> videos = videoRepository.find(minDuration, maxDuration);
            if (videos.isEmpty()) {
                throw new VideoNotFoundException("No videos found with duration between " + minDuration + " and " + maxDuration);
            }
            return videos;
        }
    }
}