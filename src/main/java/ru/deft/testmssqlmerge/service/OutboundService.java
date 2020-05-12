package ru.deft.testmssqlmerge.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Created by sgolitsyn on 5/7/20
 */
@Service
@RequiredArgsConstructor
public class OutboundService {
    private final DataSource dataSource;

    @SneakyThrows
    public void mergeByQueryWithThreadPool(int vendorMsgId, int outboundStatusTypeID) {
        Connection connection = dataSource.getConnection(); // (1)
//        System.out.println(
//                String.format("Insert or update WITH threadPool with vendorMsgId =  %s, outboundStatusTypeID = %s ...",
//                        vendorMsgId,
//                        outboundStatusTypeID));
        try (connection) {
            connection.setAutoCommit(false); // (2)
            // execute some SQL statements...
            PreparedStatement psMerge = connection.prepareStatement(SQLs.MERGE_OUTBOUND);
            psMerge.setInt(1, vendorMsgId);
            psMerge.setInt(2, outboundStatusTypeID);
            psMerge.executeQuery();

//            sleep(1000);

            PreparedStatement psHistory = connection.prepareStatement(SQLs.SAVE_HISTORY);
            psHistory.setInt(1, vendorMsgId);
            psHistory.setInt(2, outboundStatusTypeID);
            psHistory.execute();

//            sleep(1000);

            connection.commit(); // (3)

//            sleep(1000);
            // good practice to set it back to default true
            connection.setAutoCommit(true);
//            System.out.println("SUCCESS WITH threadPool for thread - " + Thread.currentThread().getName());
        } catch (SQLException e) {
            System.out.println("ERROR in CATCH");
            e.printStackTrace();
            if (!connection.isClosed())
                connection.rollback(); // (4)
        }
    }

    @SneakyThrows
    public void mergeByQueryWithNewConnection(int vendorMsgIg, int outboundStatusTypeID) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
        dataSource.setDriver(d);
        dataSource.setUsername("sa");
        dataSource.setPassword("yourStrong(!)Password");
        dataSource.setUrl("jdbc:sqlserver://localhost:1433;database=test");

//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Connection connection = dataSource.getConnection();
        System.out.println("checking connection...");
        try (connection) {

            System.out.println(
                    String.format("Insert or update WITHOUT threadPool with vendorMsgIg =  %s, outboundStatusTypeID = %s ...",
                            vendorMsgIg,
                            outboundStatusTypeID));
            connection.setAutoCommit(false); // (2)
            // execute some SQL statements...
            PreparedStatement psMerge = connection.prepareStatement(SQLs.MERGE_OUTBOUND);
            psMerge.setInt(1, vendorMsgIg);
            psMerge.setInt(2, outboundStatusTypeID);
            psMerge.executeQuery();

//            sleep(10000));

            PreparedStatement psHistory = connection.prepareStatement(SQLs.SAVE_HISTORY);
            psHistory.setInt(1, vendorMsgIg);
            psHistory.setInt(2, outboundStatusTypeID);
            psHistory.execute();

//            sleep(1000));

            connection.commit(); // (3)

//            sleep((int) (1000));
            // good practice to set it back to default true
            connection.setAutoCommit(true);
            System.out.println(Thread.currentThread().getName());
            System.out.println("SUCCESS WITHOUT threadPool for thread - " + Thread.currentThread().getName());
        } catch (SQLException e) {
            System.out.println("ERROR in CATCH");
            if (!connection.isClosed())
                connection.rollback(); // (4)
        } finally {
            if (!connection.isClosed())
                connection.close(); // (5)
        }
    }

    private void sleep(int i) throws InterruptedException {
        Thread.sleep((long) (Math.random() * i / Thread.currentThread().getId()));
    }
}
