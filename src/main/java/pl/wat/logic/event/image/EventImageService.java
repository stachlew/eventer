package pl.wat.logic.event.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;

@Service
public class EventImageService {

    @Autowired
    EventRepository eventRepository;

    //zapis obrazu
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

    //odczyt obrazu
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
}
