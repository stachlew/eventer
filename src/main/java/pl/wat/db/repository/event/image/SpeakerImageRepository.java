package pl.wat.db.repository.event.image;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.image.SpeakerImage;

import java.sql.Blob;

public interface SpeakerImageRepository extends JpaRepository<SpeakerImage,Integer> {
    public SpeakerImage findByIdSpeaker(int id);
}
