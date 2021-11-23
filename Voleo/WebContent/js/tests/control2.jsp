<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
                    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  
  <script>
  $(document).ready(function(){
    $("#example").tablesorter({sortList:[[0,0],[2,1]], widgets: ['zebra']});
  });
  </script>
  
</head>
<body>
  <link rel="stylesheet" href="http://dev.jquery.com/view/trunk/themes/flora/flora.all.css" type="text/css" media="screen" title="Flora (Default)">
<script  src="http://dev.jquery.com/view/trunk/plugins/tablesorter/2.0/jquery.tablesorter.js"></script>

	<table id="example" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>

				<th>Age</th>
				<th>Total</th>
				<th>Discount</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td>Peter</td>
				<td>Parker</td>
				<td>28</td>
				<td>$9.99</td>
				<td>20%</td>

				<td>Jul 6, 2006 8:14 AM</td>
			</tr>
			<tr>
				<td>John</td>
				<td>Hood</td>
				<td>33</td>
				<td>$19.99</td>

				<td>25%</td>
				<td>Dec 10, 2002 5:14 AM</td>
			</tr>
			<tr>
				<td>Clark</td>
				<td>Kent</td>
				<td>18</td>

				<td>$15.89</td>
				<td>44%</td>
				<td>Jan 12, 2003 11:14 AM</td>
			</tr>
			<tr>
				<td>Bruce</td>
				<td>Almighty</td>

				<td>45</td>
				<td>$153.19</td>
				<td>44%</td>
				
				<td>Jan 18, 2001 9:12 AM</td>
			</tr>
			<tr>
				<td>Bruce</td>

				<td>Evans</td>
				<td>22</td>
				<td>$13.19</td>
				<td>11%</td>
				<td>Jan 18, 2007 9:12 AM</td>
			</tr>

		</tbody>
	</table>
</body>
</html>












