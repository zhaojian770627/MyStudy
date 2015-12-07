function setHtmlValue()
{
	var json;
	json='[ { "dataname" : "SerialNumberValue", "datatype" : "text", "datavalue" : "111111", "out" : false }, { "dataname" : "PatientNumberValue", "datatype" : "text", "datavalue" : "222222", "out" : false }, { "dataname" : "HospitalNumberValue", "datatype" : "text", "datavalue" : "333333", "out" : false }, { "dataname" : "kbValue", "datatype" : "text", "datavalue" : "\u00e5\u00a4\u0096\u00e7\u00a7\u0091", "out" : false }, { "dataname" : "cwValue", "datatype" : "text", "datavalue" : "12", "out" : false }, { "dataname" : "CheckChargeValue", "datatype" : "text", "datavalue" : "12", "out" : false }, { "dataname" : "CheckPositionValue", "datatype" : "text", "datavalue" : "\u00e5\u00a4\u00b4", "out" : false }, { "dataname" : "CheckItemValue", "datatype" : "text", "datavalue" : "B\u00e8\u00b6\u0085", "out" : false }, { "dataname" : "ApplyDoctorValue", "datatype" : "text", "datavalue" : "\u00e5\u00bc\u00a0\u00e4\u00b8\u0089", "out" : false }, { "dataname" : "CheckDoctorValue", "datatype" : "text", "datavalue" : "\u00e6\u009d\u008e\u00e5\u009b\u009b", "out" : false }, { "dataname" : "RecordDoctorValue", "datatype" : "text", "datavalue" : "\u00e7\u008e\u008b\u00e4\u00ba\u0094", "out" : false }, { "dataname" : "InitialDiagnoseValue", "datatype" : "value", "datavalue" : "\u00e8\u0082\u00ba\u00e7\u0082\u008e", "out" : true }, { "dataname" : "Chrysanthemum_small", "datatype" : "image", "datavalue" : "Chrysanthemum_small.jpg", "out" : true }, { "dataname" : "Hydrangeas_small", "datatype" : "image", "datavalue" : "Hydrangeas_small.jpg", "out" : true } ]';
	var objs=eval("(" + json + ")");
	for(var i=0;i<objs.length;i++)
	{
		setDataItemValue(objs[i]);
	}
}

function setDataItemValue(obj)
{
	var seletor;
	if(obj.datatype=="image")
	{
		seletor ="#"+ obj.dataname;
		if($(seletor).length>0)
		{
			$(seletor).remove();
		}
		var img=$('<img>');
		img.attr({id:obj.dataname,datatype:"image",datavalue:obj.datavalue,src:"images/"+obj.datavalue});
		img.addClass("out");
		img.appendTo('#Image');
	}
	else
	{
		seletor ="#"+ obj.dataname;
		if($(seletor).length>0)
		{
			if(obj.datatype=="text")
			{
				$(seletor).text(obj.datavalue);
				$(seletor).attr("datatype","text")
			}
			else if(obj.datatype=="value")
			{
				$(seletor).val(obj.datavalue);
				$(seletor).addClass("out");
				$(seletor).attr("datatype","value");
			}
		}
	}
}

function getHtmlValue()
{
	alert($(".out").length);
	var array=$(".out").map(function()
			{
				
				var dtype = $(this).attr("datatype");
				if(dtype=="value")
				{
					var o={dataname:$(this).id,datatype:dtype,datavalue:$(this).val()};
					return o;
				}else if(dtype="image")
				{
					var o={dataname:$(this).id,datatype:dtype,datavalue:$(this).attr("datavalue")};
					return o;
				}
			}
		).toArray();
	alert(array);
}