package pl.wat.logic.event.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.lecture.Speaker;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.lecture.SpeakerRepository;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;

@Service
public class EventImageService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SpeakerRepository speakerRepository;

    //odczyt obrazu eventu
    public byte[] findImageByIdEvent(int idEvent) {
        Event event = eventRepository.findOne(idEvent);
        if(event!=null){
            if (event.getImage()!=null) {
                try {
                    return event.getImage().getBytes(1, (int) event.getImage().length());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //zapis obrazu eventu
    @Transactional
    public void saveImageToEvent(int idEvent, byte[] image) {
        Event event = eventRepository.findOne(idEvent);
        if(image!=null) {
            try {
                event.setImage(new SerialBlob(image));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            event.setImage(null);
        }
    }


    //usuniecie obrazu eventu
    @Transactional
    public void deleteImageEvent(int idEvent) {
        Event event = eventRepository.findOne(idEvent);
        event.setImage(null);
    }

    //odczyt obrazu speakera
    public byte[] findImageByIdSpeaker(int idSpeaker) {
        Speaker speaker = speakerRepository.findOne(idSpeaker);
        if(speaker!=null){
            if (speaker.getImage()!=null) {
                try {
                    return speaker.getImage().getBytes(1, (int) speaker.getImage().length());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //zapis obrazu speakera
    @Transactional
    public void saveImageToSpeaker(int idSpeaker, byte[] image) {
        Speaker speaker = speakerRepository.findOne(idSpeaker);
        if(image!=null) {
            try {
                speaker.setImage(new SerialBlob(image));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            speaker.setImage(null);
        }
    }

    //usuniecie obrazu speakera
    @Transactional
    public void deleteImageSpeaker(int idSpeaker) {
        Speaker speaker = speakerRepository.findOne(idSpeaker);
        speaker.setImage(null);
    }




}
