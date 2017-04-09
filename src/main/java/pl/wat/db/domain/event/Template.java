package pl.wat.db.domain.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EVE_Templates")
public class Template {

    @Id
    private int idTemplate;

    @NotNull
    @Column(length = 10, unique = true)
    @Size(min = 1, max = 10)
    private String templateName;

    public Template() {
    }

    public Template(int idTemplate, String templateName) {
        this.idTemplate = idTemplate;
        this.templateName = templateName;
    }

    public int getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(int idTemplate) {
        this.idTemplate = idTemplate;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
