package pl.wat.logic.event._model;

public class EventAdministrationSearchForm {

    private String textContent;
    private int siteNo;

    public EventAdministrationSearchForm() {}

    public EventAdministrationSearchForm(String textContent, int siteNo) {
        this.textContent = textContent;
        this.siteNo = siteNo;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public int getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(int siteNo) {
        this.siteNo = siteNo;
    }
}
