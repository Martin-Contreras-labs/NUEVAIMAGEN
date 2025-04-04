package models.utilities;


import play.db.Database;
import play.db.DBApi;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

import javax.inject.Singleton;


@Singleton
public class DatabaseExecutionContext {
    private final Database dbWrite;

    @Inject
    public DatabaseExecutionContext(DBApi dbApi) {
        this.dbWrite = dbApi.getDatabase("default");
    }

    public Database getDb() {
        return dbWrite;
    }
    
    
    public Connection getConnection() throws SQLException {
        return dbWrite.getConnection();
    }
    
    public void executeWithConnection(DatabaseOperation operation) throws SQLException {
        try (Connection connection = dbWrite.getConnection()) {
            operation.execute(connection);
        }
    }
    
    @FunctionalInterface
    public interface DatabaseOperation {
        void execute(Connection connection) throws SQLException;
    }
}
