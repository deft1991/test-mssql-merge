package ru.deft.testmssqlmerge.model;

import java.time.LocalDate;

/**
 * An interface used for lookups (enums) - these enums are sync-able to the database; hence will always be up-to-date
 * Created by Mark Cunningham on 12/7/2017.
 */
public interface Lookup {

    /**
     * @return The unique identifier of this lookup
     */
    short getId();

    /**
     * @return The unique name of this lookup
     */
    String getLookupName();

    /**
     * @return Description of this lookup
     */
    String getDescription();

    /**
     * @return When was this created (started to be used)
     */
    LocalDate getCreatedDate();

    /**
     * @return Is this an active lookup still in use
     */
    boolean isActive();
}
