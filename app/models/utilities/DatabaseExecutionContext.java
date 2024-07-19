package models.utilities;


import play.db.Database;
import play.db.DBApi;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

public class DatabaseExecutionContext {
    private final Database dbWrite;

    @Inject
    public DatabaseExecutionContext(DBApi dbApi) {
        this.dbWrite = dbApi.getDatabase("default");
    }

    public Database getDb() {
        return dbWrite;
    }
    
//    public Connection getConnection(DatabaseExecutionContext dbWrite) {
//    	Database db2 = dbWrite.getDb();
//    	return db2.getConnection();
//    }
    
    public Connection getConnection() throws SQLException {
        return dbWrite.getConnection();
    }
}
