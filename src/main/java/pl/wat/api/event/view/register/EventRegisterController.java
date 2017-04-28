package pl.wat.api.event.view.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.logic.event._model.view.ParticipantForm;
import pl.wat.logic.event.view.EventRegisterService;

@RestController
@RequestMapping("/api/event/view/register")
public class EventRegisterController {

    @Autowired
    EventRegisterService eventRegisterService;


    @RequestMapping(value = "/postNewParticipant",method = RequestMethod.POST)
    public boolean registerParticipant(@RequestBody ParticipantForm participantForm){
        if(participantForm!=null){
            return eventRegisterService.registeredParticipant(participantForm);
        }
        return false;
    }

}
