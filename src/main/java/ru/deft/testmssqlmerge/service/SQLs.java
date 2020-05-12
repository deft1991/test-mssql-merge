package ru.deft.testmssqlmerge.service;

/*
 * Created by sgolitsyn on 5/8/20
 *
 * look at https://weblogs.sqlteam.com/dang/2009/01/31/upsert-race-condition-with-merge/
 */
public class SQLs {

    public static final String MERGE_OUTBOUND = "BEGIN  " +
            "  " +
            "    DECLARE @SmsRequestID INT = 123;  " +
            "    DECLARE @VendorID smallint = 0;  " +
            "    DECLARE @VendorMessageID varchar(34) = ?;  " +
            "    DECLARE @VendorMessagingServiceID varchar(34) = 'VendorMessagingServiceID';  " +
            "    DECLARE @VendorAccountID varchar(34) = 'VendorAccountID';  " +
            "    DECLARE @OutboundStatusTypeID smallint = ?;  " +
            "    DECLARE @CreatedDate datetimeoffset(2) = CURRENT_TIMESTAMP;  " +
            "    DECLARE @UpdatedDate datetimeoffset(2) = CURRENT_TIMESTAMP;  " +

            "    MERGE INTO [dbo].[Outbound] WITH (HOLDLOCK) AS outboundDemo  " +
//                 without HOLDLOCK will race condition
//            "    MERGE INTO [dbo].[Outbound] AS outboundDemo  " +
            "    USING (SELECT @VendorMessageID                   AS VendorMessageID,  " +
            "                  @OutboundStatusTypeID as OutboundStatusTypeID) AS newval(VendorMessageID, OutboundStatusTypeID)  " +
            "    ON outboundDemo.VendorMessageID = newval.VendorMessageID  " +
            "    WHEN MATCHED  " +
            "        and outboundDemo.OutboundStatusTypeID < newval.OutboundStatusTypeID  " +
            "        THEN  " +
            "        UPDATE  " +
            "        SET " +
            "            SmsRequestID             = COALESCE(@SmsRequestID, SmsRequestID), " +
            "            VendorID                 = VendorID + 1,  " +
            "            OutboundStatusTypeID     = @OutboundStatusTypeID,  " +
            "            UpdatedDate              = @UpdatedDate  " +
            "    WHEN NOT MATCHED THEN  " +
            "        INSERT (" +
            "               SmsRequestID, VendorID, VendorMessageID, VendorMessagingServiceID, VendorAccountID,  " +
            "                OutboundStatusTypeID, CreatedDate, UpdatedDate)  " +
            "        VALUES (" +
            "                @SmsRequestID, @VendorID, @VendorMessageID, @VendorMessagingServiceID, @VendorAccountID,  " +
            "                @OutboundStatusTypeID, @CreatedDate, @UpdatedDate)  " +
            "        OUTPUT INSERTED.OutboundID  " +
            "            , $action;  " +
            "END;";

    public static final String SAVE_HISTORY = "insert into OutboundStatusHistory " +
            "(OutboundID, OutboundStatusTypeID, VersionStartDate, VersionEndDate) " +
            " values (?, ?, CURRENT_TIMESTAMP, current_timestamp);";
}
