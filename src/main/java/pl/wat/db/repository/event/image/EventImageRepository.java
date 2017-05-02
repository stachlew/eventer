package pl.wat.db.repository.event.image;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.image.EventImage;

public interface EventImageRepository extends JpaRepository<EventImage,Integer> {
    public EventImage findByIdEvent(int id);
}
