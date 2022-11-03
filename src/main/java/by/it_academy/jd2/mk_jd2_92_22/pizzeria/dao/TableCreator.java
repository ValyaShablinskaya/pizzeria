package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import org.hsqldb.cmdline.SqlFile;
import java.io.File;
import java.sql.Connection;

public class TableCreator {
    private final BDConnector connector;

    public TableCreator(BDConnector connector) {
        this.connector = connector;
    }

    public void runScript(String scriptFile) {

        try (Connection connection = connector.getConnection()) {
            SqlFile file = new SqlFile(new File(scriptFile));
            file.setConnection(connection);
            file.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
