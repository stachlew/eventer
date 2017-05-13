package pl.wat.api.documents;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.document.DocumentService;
import pl.wat.logic.event.dashboard.EventDashboardService;
import pl.wat.logic.user._model.SecurityInfo;
import pl.wat.logic.user.account.UserAccountService;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentsController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    EventDashboardService eventDashboardService;

    @Autowired
    DocumentService documentService;

// /api/documents/info
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    List<String> getInfo(Authentication auth){
        if(auth!=null){
            return documentService.getDocumentList();
        }
        return new LinkedList<>();
    }





    // /api/documents/download/{idEvent}/{docNo}
    //Przyklad pobierania dokumentu
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/download/{idEvent}/{docNo}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getDocument(Authentication auth, @PathVariable String docNo, @PathVariable String idEvent){
       if(idEvent != null && docNo != null && auth!=null){
           try{
               int intIdEvent = Integer.valueOf(idEvent);
               int intDocNo = Integer.valueOf(docNo);
               SecurityInfo si = new SecurityInfo(auth,userAccountService);
               if(si.isEventOwner(intIdEvent,eventDashboardService)) {
                   //Pobranie odpowiednio przygotowanego w serwisie dokumentu
                   XWPFDocument document = documentService.getDocument(intIdEvent,intDocNo);

                   //Zwrocenie przygotowanej odpowiedzi
                   if(document!=null)
                    return documentService.createDocumentResponse(document);
               }
           }
           catch (Exception e){
               return null;
           }
       }
       return null;
    }

}
