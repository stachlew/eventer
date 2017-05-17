package pl.wat.logic.user._model;

public class UserAdministrationSearchForm {

    private String textContent;
    private int siteNo;

    public UserAdministrationSearchForm() {
    }

    public UserAdministrationSearchForm(String textContent, int siteNo) {
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
