<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<sql:query var="rs" dataSource="jdbc/MySql">
select host,user from mysql.user
</sql:query>

<html>
  <head>
    <title>DB Test</title>
  </head>
  <body>

  <h2>Results</h2>
<!-- Ovviamente mettwere le query in una jsp non è una buona idea -->

	<table>
		<thead>
		<tr>
			<th>USER</th>
			<th>HOST</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="row" items="${rs.rows}">
		<tr>
			<td>${row.user}</td>
			<td>${row.host}</td>
		</tr>
		</c:forEach>
		</tbody>	
	</table>


  </body>
</html>