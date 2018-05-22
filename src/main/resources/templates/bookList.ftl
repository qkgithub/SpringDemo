<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/book/list2" method="post">
	图书名称：<input type="text" placeholder="请输入要查询的图书" name="name"><br>
	图书作者：<input type="text" placeholder="请输入要查询的作者" name="author"><br>
	<input type="submit" value="搜索">
</form>
<a href="/book/bookAdd.html">添加</a>
	<table>
	<tr>
		<th>编号</th>
		<th>图书名称</th>
		<th>图书作者</th>
		<th>操作</th>
	</tr>
	<#list bookList as book>
		<tr>
			<td>${book.id}</td>
			<td>${book.name}</td>
			<td>${book.author}</td>
			<td>
				<a href="/book/preUpdate/${book.id}">修改</a>
				<a href="/book/delete?id=${book.id}">删除</a>
			</td>
		</tr>
	</#list>
</table>
	
	
</body>
</html>