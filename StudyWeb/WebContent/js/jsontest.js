//var txt = '{ "employees" : [' + '{ "firstName":"Bill" , "lastName":"Gates" },'
//		+ '{ "firstName":"George" , "lastName":"Bush" },'
//		+ '{ "firstName":"Thomas" , "lastName":"Carter" } ]}';

var txt = '[' + '{ "firstName":"Bill" , "lastName":"Gates" },'
+ '{ "firstName":"George" , "lastName":"Bush" },'
+ '{ "firstName":"Thomas" , "lastName":"Carter" } ]';

var objs = eval("(" + txt + ")");
alert(objs);
var last=JSON.stringify(objs); //将JSON对象转化为JSON字符
alert(last);