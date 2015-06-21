package hr.fer.zemris.java.hw14.aplikacija5.listener;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * Razred implementira sucelje {@link ServletContextListener}.
 * Stvara {@link ComboPooledDataSource} koristen u aplikaciji,
 * dodaje u bazu tablice ako one ne postoje.
 * @author jelena
 *
 */
@WebListener
public class DatabasePoolContextListener implements ServletContextListener {

	@SuppressWarnings({ "resource"})
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		String path = sce.getServletContext().getRealPath("/WEB-INF/dbsettings.properties");

		//procitaj dbsettings.properties
		Map<String, String> props = DatabaseInitUtility.loadProperties(path);
		
		String connectionURL = "jdbc:derby://" + 
								props.get("host") + 
								":" +
								props.get("port") +
								"/" + 
								props.get("name");

		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass("org.apache.derby.jdbc.ClientDriver");
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		
		cpds.setJdbcUrl(connectionURL);
		cpds.setUser(props.get("user"));
		cpds.setPassword(props.get("password"));
		//minimalno 
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
		
		sce.getServletContext().setAttribute("hr.fer.zemris.dbpool", cpds);
		Connection con = null;
		
		try {
			con = cpds.getConnection();
		} catch (SQLException ignorable) {}
		//dodaj tablice ako ne postoje
		try {
			addTable("Polls", con);
			//cekamo dok se Polls kreira ako ne postoji,
			//da ne bi doslo do pristupa nepostojecoj tablici
			DatabaseMetaData dbmd = con.getMetaData();
	        ResultSet rs = dbmd.getTables(null, null, "POLLS",null);
	        while(!rs.next()) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException ignorable) {}
	        	rs = dbmd.getTables(null, null, "POLLS",null);
	        }
			addTable("PollOptions", con);
		} catch (SQLException e) {}
		
	}

	/**
	 * Metoda dodaje tablicu s zadanim imenom u bazu.
	 * Ako je tablica Polls prazna, popuni nju i tablicu PollOptions inicijalnim
	 * podacima.
	 * @param tableName ime tablice
	 * @param con koristena konekcija
	 * @return true ako je dodana tablica, false inace.
	 * @throws SQLException u slucaju problema s dodavanjem
	 */
	private boolean addTable(String tableName, Connection con) throws SQLException{
		if(con!=null) {
	        DatabaseMetaData dbmd = con.getMetaData();
	        ResultSet rs = dbmd.getTables(null, null, tableName.toUpperCase(),null);
	        
	        if(rs.next()) {
	        	//ako je prazna tablica polls
	        	PreparedStatement pst = null;
	    		try {
	    			pst = con.prepareStatement("select id, title from Polls order by id");
	    			try {
	    				ResultSet polls = pst.executeQuery();
	    				if(!polls.next() && tableName.equals("Polls")) {
	    					DatabaseInitUtility.fillTables(con);
	    				}
	    			} finally {
	    				try {
	    					pst.close();
	    				} catch (Exception ignorable) {
	    				}
	    			}
	    		} catch (Exception ignorable) {}
	        }
	        else {
	           if(tableName.equals("Polls")) {
	        	   DatabaseInitUtility.createPollsTable(con);
	           }else {
	        	   DatabaseInitUtility.createOptionsTable(con);
	        	   DatabaseInitUtility.fillTables(con);
	           }
	        }
	        return true;
	    }
	    return false;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ComboPooledDataSource cpds = (ComboPooledDataSource) sce.getServletContext().getAttribute("hr.fer.zemris.dbpool");
		if (cpds != null) {
			try {
				DataSources.destroy(cpds);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}