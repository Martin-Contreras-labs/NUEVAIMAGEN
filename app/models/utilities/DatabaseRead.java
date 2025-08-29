package models.utilities;

import play.db.Database;
import play.db.DBApi;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

import javax.inject.Singleton;

@Singleton
public class DatabaseRead {
    private final Database dbRead;

    @Inject
    public DatabaseRead(DBApi dbApi) {
        this.dbRead = dbApi.getDatabase("read");
    }

    
    public Database getDb() {
        return dbRead;
    }
    
    public Connection getConnection() throws SQLException {
        return dbRead.getConnection();
    }
    
    public void executeWithConnection(DatabaseOperation operation) throws SQLException {
        try (Connection connection = dbRead.getConnection()) {
            operation.execute(connection);
        }
    }
    
    @FunctionalInterface
    public interface DatabaseOperation {
        void execute(Connection connection) throws SQLException;
    }
    
}
