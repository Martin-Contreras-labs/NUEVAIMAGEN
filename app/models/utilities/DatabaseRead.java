package models.utilities;

import play.db.Database;
import play.db.DBApi;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

public class DatabaseRead {
    private final Database dbRead;

    @Inject
    public DatabaseRead(DBApi dbApi) {
        this.dbRead = dbApi.getDatabase("read");
    }

    public Database getDb() {
        return dbRead;
    }
    
//    public Connection getConnection(DatabaseRead dbRead) {
//    	Database db2 = dbRead.getDb();
//    	return db2.getConnection();
//    }
    
    public Connection getConnection() throws SQLException {
        return dbRead.getConnection();
    }
}
