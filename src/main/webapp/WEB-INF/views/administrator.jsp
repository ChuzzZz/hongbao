<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrator</title>
</head>

<body>
<form name="form" action="luckyrain_start?round=???">
<input type="submit" value="开启第一轮红包雨" onclick="form1.round.value='1'">
<br/>
<input type="submit" value="开启第二轮红包雨" onclick="form1.round.value='2'">
<br/>
<input type="submit" value="开启第三轮红包雨" onclick="form1.round.value='3'">
<br/>
</form>
</body>

</html>