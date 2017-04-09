package pl.wat.db.domain.document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EVE_Documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_DOCUMENT_SEQ")
    @SequenceGenerator(sequenceName = "EVE_DOCUMENT_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_DOCUMENT_SEQ")
    private int idDocument;

    @Column(length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Column(length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String path;

    @Column(length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String type;

    public Document() {
    }

    public Document(String name, String path, String type) {
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
