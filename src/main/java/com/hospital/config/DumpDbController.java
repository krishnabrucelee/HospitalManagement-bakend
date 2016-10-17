package com.hospital.config;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;

import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DumpDbController {

    private final Object monitor = new Object();

    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource dataSource) {
       this.dataSource = dataSource;
       this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value = "/dumpDb", method = RequestMethod.POST)
    public @ResponseBody JSONObject dumpDb(@RequestBody JSONObject dbName) throws Exception {
        synchronized (this.monitor) {
     	   
     	   Connection conn = null;
     	   Statement stmt = null;
        	JSONObject jsonObject = new JSONObject(dbName);
        	String dbNameValue = jsonObject.get("dbName").toString();
        	Boolean status = dbCreation(dbNameValue, conn, stmt);
			if (status == true) {
				System.out.println("Tables created...");
			}
        }
		return dbName;
    }
    
    
    public Boolean dbCreation(String dbName, Connection conn, Statement stmt) throws Exception {
    	String q = "";
    	Boolean status = true;
    	// JDBC driver name and database URL
    	   String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    	   String DB_URL = "jdbc:mysql://localhost/";

    	   //  Database credentials
    	   String USER = "root";
    	   String PASS = "mysql";

    	   try{
    	      //STEP 2: Register JDBC driver
    	      Class.forName(JDBC_DRIVER);

    	      //STEP 3: Open a connection
    	      System.out.println("Connecting to database...");
    	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

    	      //STEP 4: Execute a query
    	      System.out.println("Creating database...");
    	      stmt = conn.createStatement();
    	      
    	      String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
    	      stmt.executeUpdate(sql);
    	      String useDb = "USE " + dbName;
    	      stmt.executeUpdate(useDb);
    	      
    	      System.out.println("Database created successfully...");
    	      
    	      Resource dump = new ClassPathResource("HospitalDump.sql");
				if(dump.isReadable()) {
					System.out.println(dump.getFile());
				}
						
				try {
	        	    BufferedReader bf = new BufferedReader(new FileReader(dump.getFile()));
	        	        String line = null;
	        	        line = bf.readLine();
	        	        while (line != null) {
	        	            q = q + line + "\n";
	        	            line = bf.readLine();
	        	        }
	        	    } catch (Exception ex) {
	        	        ex.printStackTrace();
	        	    }
	        	// Now we have the content of the dumpfile in 'q'.
	        	// We must separate the queries, so they can be executed. And Java Simply does this:
	        	String[] commands = q.split(";");
	
	        	try {
//	        		stmt = conn.createStatement();
	        	    for (String s : commands) {
	        	    	stmt.execute(s);
	        	    }
	        	} catch (Exception ex) {
	        		ex.printStackTrace();
	        	}
    	      
    	      
    	      
    	   }catch(SQLException se){
    	      //Handle errors for JDBC
    		   System.out.println("Database creation failed");
    		   status = false;
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	      status = false;
    	   }
		return status;
    }
}