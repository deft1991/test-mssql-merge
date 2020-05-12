package ru.deft.testmssqlmerge.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Optional;

import static ru.deft.testmssqlmerge.model.CommonColumnNames.CREATED_DATE_COL;
import static ru.deft.testmssqlmerge.model.CommonColumnNames.UPDATED_DATE_COL;

/**
 * Created by Mark Cunningham on 11/30/2017.
 */
@Entity
@Table(name = "Outbound", schema = "dbo")
public class OutboundEntity extends AbstractOutboundEntity implements VersionableEntity<OutboundEntity, OutboundStatusHistoryEntity> {

    public final static String OUTBOUND_ID_COL = "OutboundID";
    final static String VENDOR_ID_COL = "VendorID";
    private final static String VENDOR_MESSAGE_ID_COL = "VendorMessageID";
    private final static String VENDOR_MESSAGING_SERVICE_ID_COL = "VendorMessagingServiceID";
    private final static String VENDOR_ACCOUNT_ID_COL = "VendorAccountID";
    private final static String VENDOR_ERROR_CODE_COL = "VendorErrorCode";
    private final static String VENDOR_ERROR_MESSAGE_COL = "VendorErrorMessage";

    private final static int VENDOR_MESSAGE_ID_COL_LENGTH = 34;
    private final static int VENDOR_MESSAGING_SERVICE_ID_COL_LENGTH = VENDOR_MESSAGE_ID_COL_LENGTH;
    private final static int VENDOR_ACCOUNT_ID_COL_LENGTH = VENDOR_MESSAGE_ID_COL_LENGTH;
    private final static int VENDOR_ERROR_CODE_COL_LENGTH = 5;
    private final static int VENDOR_ERROR_MESSAGE_COL_LENGTH = 128;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = OUTBOUND_ID_COL, nullable = false, insertable = false, unique = true)
    private Integer id;

    @Basic
    @Column(name = VENDOR_ID_COL, nullable = false)
    private short vendorId;
    @Basic
    @Column(name = VENDOR_MESSAGE_ID_COL, length = VENDOR_MESSAGE_ID_COL_LENGTH)
    private String vendorMessageId; // nullable
    @Basic
    @Column(name = VENDOR_MESSAGING_SERVICE_ID_COL, nullable = false, length = VENDOR_MESSAGING_SERVICE_ID_COL_LENGTH)
    private String vendorMessagingServiceId;
    @Basic
    @Column(name = VENDOR_ACCOUNT_ID_COL, length = VENDOR_ACCOUNT_ID_COL_LENGTH)
    private String vendorAccountId;
    @Basic
    @Column(name = VENDOR_ERROR_CODE_COL, length = VENDOR_ERROR_CODE_COL_LENGTH)
    private String vendorErrorCode; // nullable
    @Basic
    @Column(name = VENDOR_ERROR_MESSAGE_COL, length = VENDOR_ERROR_MESSAGE_COL_LENGTH)
    private String vendorErrorMessage; // nullable
    @Basic
    @Convert(converter = SqlServerDateTimeOffsetConverter.class)
    @Column(name = CREATED_DATE_COL, nullable = false)
    private OffsetDateTime createdDate;
    @Basic
    @Convert(converter = SqlServerDateTimeOffsetConverter.class)
    @Column(name = UPDATED_DATE_COL)
    private OffsetDateTime updatedDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getVendorId() {
        return vendorId;
    }

    void setVendorId(short vendorId) {
        this.vendorId = vendorId;
    }

    public Optional<String> getVendorMessageId() {
        return Optional.ofNullable(vendorMessageId);
    }

    void setVendorMessageId(String vendorMessageId) {
        this.vendorMessageId = vendorMessageId;
    }

    public String getVendorMessagingServiceId() {
        return vendorMessagingServiceId;
    }

    void setVendorMessagingServiceId(String vendorMessagingServiceId) {
        this.vendorMessagingServiceId = vendorMessagingServiceId;
    }

    public Optional<String> getVendorErrorCode() {
        return Optional.ofNullable(vendorErrorCode);
    }

    void setVendorErrorCode(String vendorErrorCode) {
        this.vendorErrorCode = vendorErrorCode;
    }

    public Optional<String> getVendorErrorMessage() {
        return Optional.ofNullable(vendorErrorMessage);
    }

    public void setVendorErrorMessage(String vendorErrorMessage) {
        this.vendorErrorMessage = vendorErrorMessage;
    }

    public Optional<OffsetDateTime> getUpdatedDate() {
        return Optional.ofNullable(updatedDate);
    }

    void setUpdatedDate(OffsetDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public OutboundStatusHistoryEntity convertToHistoric() {
        return new OutboundStatusHistoryEntity().from(this);
    }

    @Override
    public boolean needsVersioning(OutboundEntity other) {
        return getStatus() != other.getStatus();
    }
}
