

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voleo-Control Panel</title>
<link rel="stylesheet" type="text/css" href="css/voleo.css" />
<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />
<link rel="stylesheet" type="text/css" href="admin/assets/css/default.css" />

<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>

<!-- Tableau Sorter  -->
	<script type="text/javascript" src="admin/jquery-latest.js"></script>
	<script type="text/javascript" src="admin/jquery.metadata.js"></script>
	<script type="text/javascript" src="admin/jquery.tablesorter.js"></script>
	<script type="text/javascript">
	
	$(function() {
		$("table").tablesorter();
	});
	
		</script>	

<!-- End of Autocompleter PART -->

</head>
<body>
<h1>tablesorter 2.0-dev demo, multi column sorting</h1>

<table id="large" cellspacing="0">
	
	<thead>

		<tr>
			<th>Name</th>
			<th>Major</th>
			<th>Sex</th>
			<th class="{sorter:'metadata'}">English</th>
			<th>Japanese</th>
			<th>Calculus</th>
			<th>Geometry</th>
			<th>Geometry</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th>Name</th>

			<th>Major</th>
			<th>Sex</th>
			<th>English</th>
			<th>Japanese</th>
			<th>Calculus</th>
			<th>Geometry</th>
			<th>Geometry</th>

		</tr>
	</tfoot>
	<tbody>
		<tr>
			<td>Student01</td>
			<td>Languages</td>
			<td>male</td>

			<td class="{sortValue: 0}">80</td>
			<td>70</td>
			<td>75</td>
			<td>80</td>
			<td><select><option>brian</option><option>christian</option></select></td>
		</tr>
		<tr>
			<td>Student02</td>

			<td>Mathematics</td>
			<td>male</td>
			<td class="{sortValue: 1}">90</td>
			<td>88</td>
			<td>100</td>
			<td>90</td>
<td><select><option>brian</option><option>christian</option></select></td>
		</tr>
		<tr>
			<td>Student03</td>
			<td>Languages</td>
			<td>female</td>
			<td class="{sortValue: 2}">85</td>
			<td>95</td>

			<td>80</td>
			<td>85</td>
			<td><select><option>brian</option><option>christian</option></select></td>
		</tr>
		<tr>
			<td>Student04</td>
			<td>Languages</td>
			<td>male</td>

			<td class="{sortValue: 3}">60</td>
			<td>55</td>
			<td>100</td>
			<td>100</td>
			<td><select><option>angus</option><option>zebra</option></select></td>
		</tr>
	</tbody>
</table>

	</body>
</html>











