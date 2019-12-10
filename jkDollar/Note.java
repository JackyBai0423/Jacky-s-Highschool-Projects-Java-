package jkDollar;

import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
 * Class for Note（现金！！！）
 */
public class Note implements Serializable{
char[] SerialNum;
String time;
char[] OriginBuyer;
public Note(char[] SerialNum,char[] OriginalBuyer){	//Constuction Method
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	this.SerialNum = SerialNum;
	this.time = df.format(new Date());
	this.OriginBuyer = OriginalBuyer;
	//Connect with local Access Database
	/**
	 * problem faced: no sutable driver for microsoft access database...QAQ
	 */
	/*
	try {
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\\Users\\24506\\Documents\\Database2.accdb";
		Connection con = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
		Statement sta = con.createStatement();
		ResultSet rst = sta.executeQuery("select * from Data");//Data为access数据库中的一个表名
		PreparedStatement sql;
		sql = con.prepareStatement("insert into Data"+"values(SerialNum.toString(), df.format(new Date()), OriginalBuyer)");//Write the data into the database
		sql.executeUpdate();
		if(rst.next()){
			System.out.println("java code implementation:" + rst.getString("name"));
			
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	*/
}
public String toString(){
	return String.valueOf(SerialNum);
//	return "Serial number:"+String.valueOf(SerialNum)+"\nBuyer:"+String.valueOf(OriginBuyer)+"\nDate&time:"+time;
}
}
