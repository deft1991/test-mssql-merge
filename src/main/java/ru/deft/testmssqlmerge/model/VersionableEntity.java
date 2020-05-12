package ru.deft.testmssqlmerge.model;


import java.time.OffsetDateTime;

public interface VersionableEntity<E extends VersionableEntity<E, H>, H extends VersionedHistoricEntity<E, H>> {

    /**
     * Gets the ID for this entity
     */
    Integer getId();

    /**
     * Sets the ID for this entity - necessary for "replacement" entities
     */
    void setId(Integer id);

    /**
     * Gets the created date of this entity
     */
    OffsetDateTime getCreatedDate();

    /**
     * Convert the current entity into a historic one - note, it's not expected that all the version info be set in this method.
     */
    H convertToHistoric();

    /**
     * Using this current entity, and provided with a potential replacement; return T/F is this entity has changed
     * compared to the to-be-replacement - i.e. does this entity need to be versioned-off
     *
     * @return True if the entity is to be versioned off; false, if it does not need versioning
     */
    boolean needsVersioning(E other);

    /**
     * To perform "in-place-replacement", we copy the identity of the existing entity.
     * <br/> Default method here can perform the most common copying since the it's just an identity copy
     */
    default void copyIdentityFromExisting(E existingEntity) {
        this.setId(existingEntity.getId());
    }

}
