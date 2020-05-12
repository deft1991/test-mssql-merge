package ru.deft.testmssqlmerge.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Objects;

import static ru.deft.testmssqlmerge.model.CommonColumnNames.CREATED_DATE_COL;
import static ru.deft.testmssqlmerge.model.CommonColumnNames.DESCRIPTION_COL;
import static ru.deft.testmssqlmerge.model.CommonColumnNames.IS_ACTIVE_COL;
import static ru.deft.testmssqlmerge.model.CommonColumnNames.NAME_COL;

/**
 * Abstract class that denotes an entity with standard set of attributes for a lookup
 * Created by Mark Cunningham on 12/7/2017.
 */
@MappedSuperclass
public abstract class AbstractLookupEntity<E extends AbstractLookupEntity> {

    private final static int NAME_COL_WIDTH = 64;
    private final static int DESCRIPTION_COL_WIDTH = 256;

    @Id
    @Column(nullable = false, unique = true)
    private Short id;
    @Basic
    @Column(name = NAME_COL, nullable = false, length = NAME_COL_WIDTH)
    private String name;
    @Basic
    @Column(name = DESCRIPTION_COL, nullable = false, length = DESCRIPTION_COL_WIDTH)
    private String description;
    @Basic
    @Column(name = CREATED_DATE_COL, nullable = false)
    private LocalDate createdDate;
    @Basic
    @Column(name = IS_ACTIVE_COL, nullable = false)
    private boolean active;

    public abstract String toInfoString();

    /**
     * Merge the given entity into the current entity - overwriting any changes as necessary
     */
    public abstract void mergeOtherEntity(E otherEntity);

    AbstractLookupEntity() {
    }

    AbstractLookupEntity(Short id, String name, String description, LocalDate createdDate, boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.active = isActive;
    }

    public Short getId() {
        return id;
    }

    void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractLookupEntity<?> that = (AbstractLookupEntity<?>) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdDate, active);
    }

    void mergeBaseEntity(AbstractLookupEntity otherEntity) {
        this.name = otherEntity.name;
        this.description = otherEntity.description;
        this.createdDate = otherEntity.createdDate;
        this.active = otherEntity.active;
    }

    @Override
    public String toString() {
        return "AbstractLookupEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", active=" + active +
                '}';
    }
}
