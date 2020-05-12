package ru.deft.testmssqlmerge.model;


import java.time.OffsetDateTime;

public interface VersionedHistoricEntity<E extends VersionableEntity<E, H>, H extends VersionedHistoricEntity<E, H>> {

    /**
     * Set the parent for this historic entity
     */
    void setParent(E parent);

    /**
     * Sets the version-start date (which is generally the original {@link VersionableEntity#getCreatedDate()})
     */
    void setVersionStartDate(OffsetDateTime date);

    /**
     * Sets the version-end date (which is generally the replacement {@link VersionableEntity#getCreatedDate()})
     */
    void setVersionEndDate(OffsetDateTime date);

    /**
     * Given a versionable entity, create a historic version from it
     */
    H from(E entity);

}
