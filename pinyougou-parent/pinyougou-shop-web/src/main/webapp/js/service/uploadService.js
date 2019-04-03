app.service("uploadService",function($http){
	
	this.uploadFile=function(){
		var formData=new FormData();//html5的新特性 用来传递文件的对象
		  formData.append("file",file.files[0]); 
	 return	$http({
		 		method:"POST",
				url:"../upload.do",
				data:formData,
				headers:{'Content-Type':undefined},//告诉程序 不要以json来传递数据
				transformRequest:angular.identity
	});
	}
});