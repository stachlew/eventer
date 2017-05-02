package pl.wat.logic.event.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.image.EventImage;
import pl.wat.db.domain.event.image.SpeakerImage;
import pl.wat.db.domain.event.lecture.Speaker;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.image.EventImageRepository;
import pl.wat.db.repository.event.image.SpeakerImageRepository;
import pl.wat.db.repository.event.lecture.SpeakerRepository;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;

@Service
public class EventImageService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SpeakerRepository speakerRepository;

    @Autowired
    SpeakerImageRepository speakerImageRepository;

    @Autowired
    EventImageRepository eventImageRepository;

    //odczyt obrazu eventu
    @Transactional
    public byte[] findImageByIdEvent(int idEvent) {
        EventImage image = eventImageRepository.findByIdEvent(idEvent);
        if(image!=null && image.getImage()!=null){
            try {
                return image.getImage().getBytes(1, (int) image.getImage().length());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //zapis obrazu eventu
    @Transactional
    public void saveImageToEvent(int idEvent, byte[] image) {
        if(image!=null) {
            EventImage imageRepo = eventImageRepository.findByIdEvent(idEvent);
            if(imageRepo==null){//zdjecie juz istnieje
                imageRepo = new EventImage();
                imageRepo.setIdEvent(idEvent);
            }
            try {
                imageRepo.setImage(new SerialBlob(image));
                eventImageRepository.save(imageRepo);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    //usuniecie obrazu eventu
    @Transactional
    public void deleteImageEvent(int idEvent) {
        EventImage imageRepo = eventImageRepository.findByIdEvent(idEvent);
        if(imageRepo!=null){
            imageRepo.setImage(null);
            eventImageRepository.save(imageRepo);
        }
    }

    //odczyt obrazu speakera
    @Transactional
    public byte[] findImageByIdSpeaker(int idSpeaker) {
        SpeakerImage image = speakerImageRepository.findByIdSpeaker(idSpeaker);
        if(image!=null && image.getImage()!=null){
            try {
                return image.getImage().getBytes(1, (int) image.getImage().length());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //zapis obrazu speakera
    @Transactional
    public void saveImageToSpeaker(int idSpeaker, byte[] image) {
        if(image!=null) {
            SpeakerImage imageRepo = speakerImageRepository.findByIdSpeaker(idSpeaker);
            if(imageRepo==null){//zdjecie juz istnieje
                imageRepo = new SpeakerImage();
                imageRepo.setIdSpeaker(idSpeaker);
            }
            try {
                imageRepo.setImage(new SerialBlob(image));
                speakerImageRepository.save(imageRepo);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //usuniecie obrazu speakera
    @Transactional
    public void deleteImageSpeaker(int idSpeaker) {
        SpeakerImage imageRepo = speakerImageRepository.findByIdSpeaker(idSpeaker);
        if(imageRepo!=null){
            imageRepo.setImage(null);
            speakerImageRepository.save(imageRepo);
        }
    }




}
