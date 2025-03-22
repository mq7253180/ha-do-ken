<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><@i18n key="title.error"/></title>
	</head>
	<body>
		<font color="red"><@i18n key="status.error.500"/></font><br/>
		<#if exception?exists><font color="blue">${exception}</font></#if>
	</body>
</html>
