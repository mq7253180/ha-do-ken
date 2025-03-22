<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>结果页</title>
	</head>
	<body>
		<font color="green">${status}<label id="msg"><#if msg?exists>-${msg}</#if></label><#if data?exists>-${data}</#if></font>
	</body>
	<script src="<@property key="prefix.resource"/>/js/result.js"></script>
</html>