package ru.deft.testmssqlmerge.model;


import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Mark Cunningham on 12/13/2017.
 */
@Entity
@Table(name = "OutboundStatusType", schema = "dbo")
@AttributeOverride(name="id", column = @Column(name = OutboundStatusTypeEntity.OUTBOUND_STATUS_TYPE_ID))
public class OutboundStatusTypeEntity extends AbstractLookupEntity<OutboundStatusTypeEntity> {

    static final String OUTBOUND_STATUS_TYPE_ID = "OutboundStatusTypeID";
    private static final String IS_TERMINAL_STATE = "IsTerminalState";

    @Basic
    @Column(name = IS_TERMINAL_STATE, nullable = false)
    private boolean isTerminalState;

    public OutboundStatusTypeEntity() {}

    private OutboundStatusTypeEntity(short id, String name, String description, LocalDate createdDate, boolean isTerminalState, boolean isActive) {
        super(id, name, description, createdDate, isActive);
        this.isTerminalState = isTerminalState;
    }

    public static OutboundStatusTypeEntity fromLookup(OutboundStatus lookup) {
        return new OutboundStatusTypeEntity(lookup.getId(), lookup.getLookupName(), lookup.getDescription(), lookup.getCreatedDate(), lookup.isATerminalState(), lookup.isActive());
    }

    @Override
    public String toInfoString() {
        return toString();
    }

    @Override
    public void mergeOtherEntity(OutboundStatusTypeEntity otherEntity) {
        super.mergeBaseEntity(otherEntity);
        isTerminalState = otherEntity.isTerminalState;
    }

    @Override
    public String toString() {
        return "OutboundStatusTypeEntity{} " + super.toString();
    }

    public boolean getIsTerminalState() {
        return isTerminalState;
    }

    void setIsTerminalState(boolean terminalState) {
        isTerminalState = terminalState;
    }
}
