package ru.deft.testmssqlmerge.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import static ru.deft.testmssqlmerge.model.OutboundStatusTypeEntity.OUTBOUND_STATUS_TYPE_ID;

/**
 * Created by Mark Cunningham on 12/6/2017.
 */
@MappedSuperclass
abstract class AbstractOutboundEntity {

    @Basic
    @Column(name = OUTBOUND_STATUS_TYPE_ID, nullable = false)
    private short statusId;

    @Deprecated
        // Needed for reflection on the JPA
    AbstractOutboundEntity() { }

    AbstractOutboundEntity(short statusId) {
        this.statusId = statusId;
    }

    public short getStatusId() {
        return statusId;
    }

    public OutboundStatus getStatus() {
        // cannot be null ...
        return OutboundStatus.fromId(statusId).get();
    }

    void setStatusId(short statusId) {
        this.statusId = statusId;
    }

    void setStatus(OutboundStatus outboundsStatus) {
        this.statusId = outboundsStatus.getId();
    }

}
