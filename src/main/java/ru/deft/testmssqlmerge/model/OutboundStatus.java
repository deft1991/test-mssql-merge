package ru.deft.testmssqlmerge.model;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Enum that denotes the various statues of outbound requests
 * Created by Mark Cunningham on 12/4/2017.
 */
public enum OutboundStatus implements Lookup {

    UNKNOWN(1, "Unknown or un-mapped status from vendor", LocalDate.of(2018, 3, 12), true, true),
    VENDOR_QUEUED(2, "In the vendors' queues", LocalDate.of(2018, 3, 12), false, true),
    CARRIER_SENDING(3, "The carrier is currently sending the message", LocalDate.of(2018, 3, 12), false, true),
    CARRIER_RECEIVING(4, "The carrier is currently receiving a message", LocalDate.of(2018, 3, 12), false, true),
    VENDOR_RECEIVED(5, "The vendor received a response from the carrier", LocalDate.of(2018, 3, 12), false, true),
    CARRIER_DELIVERED(6, "The carrier delivered the message", LocalDate.of(2018, 3, 12), true, true),
    CARRIER_UNDELIVERED(7, "The carrier could not deliver the message", LocalDate.of(2018, 3, 12), true, true),
    FAILED(8, "Something went wrong", LocalDate.of(2018, 3, 12), true, true), // means anything really - failed somewhere - need to look at error codes
    SMS_SENDING_DISABLED(9, "The sms sending function is disabled and sms's cannot be sent", LocalDate.of(2018, 3, 12), true, true),
    VENDOR_REJECTED_REQUEST(10, "The vendor rejected the request", LocalDate.of(2018, 3, 12), true, true),
    VENDOR_STATUS_NOT_PROVIDED(11, "Occurs when the vendor has not provided a status that we can map from", LocalDate.of(2018, 3, 12), true, true);

    private final short id;
    private final String lookupName;
    private final String description;
    private final LocalDate createdDate;
    private final boolean terminalState;
    private final boolean active;

    OutboundStatus(int id, String description, LocalDate createdDate, boolean terminalState, boolean active) {
        this.id = (short) id;
        this.lookupName = this.name();
        this.description = description;
        this.createdDate = createdDate;
        this.terminalState = terminalState;
        this.active = active;
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public String getLookupName() {
        return lookupName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public boolean isATerminalState() {
        return terminalState;
    }

    public static Optional<OutboundStatus> fromId(Short id) {
        if ( id != null) {
            return Stream.of(values()).filter(r -> r.id == id).findFirst();
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "OutboundStatus{" +
                "id=" + id +
                ", lookupName='" + lookupName + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", terminalState=" + terminalState +
                ", active=" + active +
                "} " + super.toString();
    }
}
