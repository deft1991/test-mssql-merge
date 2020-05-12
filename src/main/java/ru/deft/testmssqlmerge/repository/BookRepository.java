package ru.deft.testmssqlmerge.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.deft.testmssqlmerge.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByName(String name);

    //Check if key exists and insert a new row
    @Query(value = "BEGIN " +
            " DECLARE @Id INT = 15; " +
            "      DECLARE @NAME nvarchar(50) = 'Value for ID is 15'; " +
            "      MERGE INTO [dbo].[Book] AS demo" +
            "      USING (SELECT @Id AS Id) AS newval(Id)" +
            "            ON demo.Id = newval.Id" +
            "      WHEN MATCHED THEN" +
            "            UPDATE SET NAME = @NAME" +
            "      WHEN NOT MATCHED THEN" +
            "            INSERT (ID, NAME) VALUES (@Id, @NAME)" +
            "      OUTPUT INSERTED.ID; " +
            "END;", nativeQuery = true)
    Long insertByQuery();
}
