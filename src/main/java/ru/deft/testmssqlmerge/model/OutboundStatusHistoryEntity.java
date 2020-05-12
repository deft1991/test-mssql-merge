package ru.deft.testmssqlmerge.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Optional;

import static ru.deft.testmssqlmerge.model.CommonColumnNames.VERSION_END_DATE_COL;
import static ru.deft.testmssqlmerge.model.CommonColumnNames.VERSION_START_DATE_COL;

/**
 * Created by Mark Cunningham on 12/6/2017.
 */
@Entity
@Table(name = "OutboundStatusHistory", schema = "dbo")
public class OutboundStatusHistoryEntity extends AbstractOutboundEntity implements VersionedHistoricEntity<OutboundEntity, OutboundStatusHistoryEntity> {

    private final static String OUTBOUNDS_HISTORY_ID_COL = "OutboundStatusHistoryID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = OUTBOUNDS_HISTORY_ID_COL, nullable = false, updatable = false, insertable = false, unique = true)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = OutboundEntity.OUTBOUND_ID_COL, nullable = false, referencedColumnName = OutboundEntity.OUTBOUND_ID_COL)
    private OutboundEntity outbound;
    @Basic
    @Convert(converter = SqlServerDateTimeOffsetConverter.class)
    @Column(name = VERSION_START_DATE_COL, nullable = false)
    private OffsetDateTime versionStartDate;
    @Basic
    @Convert(converter = SqlServerDateTimeOffsetConverter.class)
    @Column(name = VERSION_END_DATE_COL, nullable = false)
    private OffsetDateTime versionEndDate;

    /**
     * @deprecated - Don't use this directly - it is only made public to support JPA + Hibernate + Javaassist
     * Upgrading the Hibernate libraries may help in later releases and then this can be made private again
     */
    @Deprecated
    public OutboundStatusHistoryEntity() { }

    private OutboundStatusHistoryEntity(OutboundEntity outbound) {
        super(outbound.getStatus().getId());
    }

    @Override
    public OutboundStatusHistoryEntity from(OutboundEntity entity) {
        return new OutboundStatusHistoryEntity(entity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OutboundEntity getOutbound() {
        return outbound;
    }

    void setOutbound(OutboundEntity outbound) {
        this.outbound = outbound;
    }

    public OffsetDateTime getVersionStartDate() {
        return versionStartDate;
    }

    @Override
    public void setParent(OutboundEntity parent) {
        this.outbound = parent;
    }

    @Override
    public void setVersionStartDate(OffsetDateTime versionStartDate) {
        this.versionStartDate = versionStartDate;
    }

    public Optional<OffsetDateTime> getVersionEndDate() {
        return Optional.ofNullable(versionEndDate);
    }

    @Override
    public void setVersionEndDate(OffsetDateTime versionEndDate) {
        this.versionEndDate = versionEndDate;
    }

}
