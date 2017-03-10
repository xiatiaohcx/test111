<%@ page language="java" import="java.util.*,java.net.URL,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Hello World</title>
  </head>
  <body>
    <%
        //（1）指定服务地址，其中dbname需要自己修改
        //String dbUrl = "jdbc:mysql://sqld.duapp.com:4050/dbname";
        //（2）直接从请求header中获取ip、端口、用户名和密码信息
	//String host = request.getHeader("BAE_ENV_ADDR_SQL_IP");
	//String port = request.getHeader("BAE_ENV_ADDR_SQL_PORT");
	//String username = request.getHeader("BAE_ENV_AK");
	//String password = request.getHeader("BAE_ENV_SK");
       //（3）从线程变量BaeEnv接口获取ip、端口、用户名和密码信息
    /**
	String host = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_ADDR_SQL_IP);
	String port = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_ADDR_SQL_PORT);
	String username = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_AK);
	String password = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_SK);
	String driverName = "com.mysql.jdbc.Driver";
	String dbUrl = "jdbc:mysql://";
	String serverName = host + ":" + port + "/";
 
        //从平台查询应用要使用的数据库名
	String databaseName = "IwMhiFnBibuOuhiJPWML";
	String connName = dbUrl + serverName + databaseName;
	String sql = "select * from message";
 
	Connection connection = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		Class.forName(driverName);
                //具体的数据库操作逻辑
		connection = DriverManager.getConnection(connName, username,
				password);
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		String id = "", name = "";
      out.println("id&nbsp;&nbsp;&nbsp;&nbsp;name<br/>");
      out.println("username:"+username);
      out.println("password:"+password);
		while (rs.next()) {
			id = rs.getString("id");
			name = rs.getString("title");
          out.println(id + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + name + "<br/>");
		}
	} catch (ClassNotFoundException ex) {
		// 异常处理逻辑
      throw ex;
	} catch (SQLException e) {
		// 异常处理逻辑
 		throw e;
	} finally {
		try {
			if (connection != null) {
				connection.close();
			}
			} catch (SQLException e) {
 				throw e;
		}
	}
	 */
 %>
  </body>
</html>