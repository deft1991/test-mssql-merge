package ru.deft.testmssqlmerge.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Optional;

/**
 * Created by Mark Cunningham on 2/28/2018
 **/
@MappedSuperclass
abstract class AbstractPhoneNumberEntity {

    private final static String PHONE_NUMBER_COL = "PhoneNumber";
    private final static String VENDOR_ID_COL = "VendorID";
    private final static String NATIONAL_FORMAT_COL = "NationalFormat";
    private final static String COUNTRY_CODE_COL = "CountryCode";
    private final static String CNAM_NAME_COL = "CNAMName";
    private final static String CNAM_TYPE_COL = "CNAMType";
    private final static String CNAM_ERROR_CODE_COL = "CNAMErrorCode";
    private final static String CARRIER_NAME_COL = "CarrierName";
    private final static String CARRIER_TYPE_COL = "CarrierType";
    private final static String CARRIER_ERROR_CODE_COL = "CarrierErrorCode";
    private final static String CARRIER_MOBILE_COUNTRY_CODE_COL = "CarrierMobileCountryCode";
    private final static String CARRIER_MOBILE_NETWORK_CODE_COL = "CarrierMobileNetworkCode";

    private final static int PHONE_NUMBER_COL_LENGTH = 15;
    private final static int NATIONAL_FORMAT_COL_LENGTH = 30;
    private final static int COUNTRY_CODE_COL_LENGTH = 2;
    private final static int CNAM_NAME_COL_LENGTH = 128;
    private final static int CNAM_TYPE_COL_LENGTH = 12;
    private final static int CNAM_ERROR_CODE_COL_LENGTH = 5;
    private final static int CARRIER_NAME_COL_LENGTH = 64;
    private final static int CARRIER_TYPE_COL_LENGTH = 8;
    private final static int CARRIER_ERROR_CODE_COL_LENGTH = CNAM_ERROR_CODE_COL_LENGTH;
    private final static int CARRIER_MOBILE_COUNTRY_CODE_COL_LENGTH = 8;
    private final static int CARRIER_MOBILE_NETWORK_CODE_COL_LENGTH = 15;


    @Basic
    @Column(name = PHONE_NUMBER_COL, nullable = false, length = PHONE_NUMBER_COL_LENGTH)
    private String phoneNumber;
    @Basic
    @Column(name = NATIONAL_FORMAT_COL, length = NATIONAL_FORMAT_COL_LENGTH)
    private String nationalFormat;
    @Basic
    @Column(name = COUNTRY_CODE_COL, length = COUNTRY_CODE_COL_LENGTH)
    private String countryCode;
    @Basic
    @Column(name = CNAM_NAME_COL, length = CNAM_NAME_COL_LENGTH)
    private String cnamName;
    @Basic
    @Column(name = CNAM_TYPE_COL, length = CNAM_TYPE_COL_LENGTH)
    private String cnamType;
    @Basic
    @Column(name = CNAM_ERROR_CODE_COL, length = CNAM_ERROR_CODE_COL_LENGTH)
    private String cnamErrorCode;
    @Basic
    @Column(name = CARRIER_NAME_COL, length = CARRIER_NAME_COL_LENGTH)
    private String carrierName;
    @Basic
    @Column(name = CARRIER_TYPE_COL, length = CARRIER_TYPE_COL_LENGTH)
    private String carrierType;
    @Basic
    @Column(name = CARRIER_ERROR_CODE_COL, length = CARRIER_ERROR_CODE_COL_LENGTH)
    private String carrierErrorCode;
    @Basic
    @Column(name = CARRIER_MOBILE_COUNTRY_CODE_COL, length = CARRIER_MOBILE_COUNTRY_CODE_COL_LENGTH)
    private String carrierMobileCountryCode;
    @Basic
    @Column(name = CARRIER_MOBILE_NETWORK_CODE_COL, length = CARRIER_MOBILE_NETWORK_CODE_COL_LENGTH)
    private String carrierMobileNetworkCode;

    AbstractPhoneNumberEntity() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Optional<String> getNationalFormat() {
        return Optional.ofNullable(nationalFormat);
    }

    void setNationalFormat(String nationalFormat) {
        this.nationalFormat = nationalFormat;
    }

    public Optional<String> getCountryCode() {
        return Optional.ofNullable(countryCode);
    }

    void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Optional<String> getCnamName() {
        return Optional.ofNullable(cnamName);
    }

    void setCnamName(String cnamName) {
        this.cnamName = cnamName;
    }

    public Optional<String> getCnamType() {
        return Optional.ofNullable(cnamType);
    }

    void setCnamType(String cnamType) {
        this.cnamType = cnamType;
    }

    public Optional<String> getCnamErrorCode() {
        return Optional.ofNullable(cnamErrorCode);
    }

    void setCnamErrorCode(String cnamErrorCode) {
        this.cnamErrorCode = cnamErrorCode;
    }

    public Optional<String> getCarrierName() {
        return Optional.ofNullable(carrierName);
    }

    void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Optional<String> getCarrierType() {
        return Optional.ofNullable(carrierType);
    }

    void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public Optional<String> getCarrierErrorCode() {
        return Optional.ofNullable(carrierErrorCode);
    }

    void setCarrierErrorCode(String carrierErrorCode) {
        this.carrierErrorCode = carrierErrorCode;
    }

    public Optional<String> getCarrierMobileCountryCode() {
        return Optional.ofNullable(carrierMobileCountryCode);
    }

    void setCarrierMobileCountryCode(String carrierMobileCountryCode) {
        this.carrierMobileCountryCode = carrierMobileCountryCode;
    }

    public Optional<String> getCarrierMobileNetworkCode() {
        return Optional.ofNullable(carrierMobileNetworkCode);
    }

    void setCarrierMobileNetworkCode(String carrierMobileNetworkCode) {
        this.carrierMobileNetworkCode = carrierMobileNetworkCode;
    }
}
