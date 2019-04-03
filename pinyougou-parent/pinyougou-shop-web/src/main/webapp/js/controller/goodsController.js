 //控制层 
app.controller('goodsController' ,function($scope,$controller   ,goodsService,uploadService,itemCatService,typeTemplateService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.add=function(){	
		//获取富文本框内容
		$scope.entity.introduction=editor.html();
		
		goodsService.add( $scope.entity  ).success(
			function(response){
				if(response.success){
					//重新查询 
		        	alert("新增成功")
		        	$scope.entity={};
		        	editor.html("");
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//上传文件
	$scope.img_entity={"color":"","url":""};
    $scope.uploadFile=function(){   	
    	uploadService.uploadFile().success(
    			function(response){
    				if(response.success){
    					
    					$scope.img_entity.url=response.message;
    					
    				}else{
    					alert(response.message);
    				}
    				
    			}
    	)
    }
    //所有图片集合itemImages:[]
    $scope.entity={goods:{},goodsDesc:{itemImages:[],specificationItems:[]}};
    //添加一个图片
    $scope.addList=function(){
    	 $scope.entity.goodsDesc.itemImages.push($scope.img_entity);
    }
    //删除一个图片
    $scope.deleimg=function(index){
    	$scope.entity.goodsDesc.itemImages.splice(index,1);
    }
    
    //商品分类的三级联动
    $scope.findList=function(){
    	itemCatService.findByParentId(0).success(
    			function(response){
    				$scope.itemCat1list=response;
    			}
    	)
    }
    

	//2级
	$scope.$watch('entity.goods.category1Id',function(newValue,oldValue){
		
		itemCatService.findByParentId(newValue).success(
			function(response){
				$scope.itemCat2list=response;
			}
		)

	});
    //3级
	$scope.$watch('entity.goods.category2Id',function(newValue,oldValue){
		
		itemCatService.findByParentId(newValue).success(
			function(response){
				$scope.itemCat3list=response;
			}
		)

	});
    //模板编号
	$scope.$watch('entity.goods.category3Id',function(newValue,oldValue){
		itemCatService.findOne(newValue).success(
				function(response){
					$scope.entity.goods.typeTemplateId=response.typeId;
				}
		)
	});
	
	
	//通过模板编号查询对应品牌信息 he 模板详情
	
	
	$scope.$watch('entity.goods.typeTemplateId',function(newValue,oldValue){
		typeTemplateService.findOne(newValue).success(
				function(response){
					$scope.typeTemplate=response;
					$scope.typeTemplate.brandIds=JSON.parse($scope.typeTemplate.brandIds);//将自己转化为json类型
					//扩展属性
					$scope.entity.goodsDesc.customAttributeItems=JSON.parse($scope.typeTemplate.customAttributeItems);
				}
		)
		typeTemplateService.findSpecList(newValue).success(
				function(response){
					$scope.specList=response
				}
				)
	});	
	
	$scope.updateSpecAttribute=function($event,name,value){
	var Object=	$scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems,"attributeName",name);
		if(Object!=null){
			//集合中含有
			if($event.target.checked){//为选中状态，添加
				Object.attributeValue.push(value)
			}else{//点击后为为选中状态，删除
				Object.attributeValue.splice(Object.attributeValue.indexOf(value),1);//删除此条
				//判断是否删除的是此集合中的最后一条
				if(Object.attributeValue.length==0){
					//是最后一条被删除 ，删除此条集合
					$scope.entity.goodsDesc.specificationItems.splice(
							$scope.entity.goodsDesc.specificationItems.indexOf(Object),1
						)
				}
			}
		}else{
			//集合中没有
			$scope.entity.goodsDesc.specificationItems.push(
					{'attributeName':name,'attributeValue':[value]  } 
			)
		}
	}
	
	/*$scope.createItemList=function(){
		$scope.entity.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0' } ];
		var items=$scope.entity.goodsDesc.specificationItems;
		for(var i=0;i<items.length;i++){
			//给itemList添加值
			$scope.entity.itemList = addColumn( $scope.entity.itemList,items[i].attributeName,items[i].attributeValue ); 
		}
		
	}
	addColumn=function(list,columnName,columnValues){
		
		for(var i=0;i<list.length;i++){
			var oldRow=list[i];
			for(var j=0;j<columnValues.length;j++){
				var newRow= JSON.parse( JSON.stringify( oldRow )  );//深克隆
				//看不懂！
				newRow.spec[columnName]=conlumnValues[j];
				newList.push(newRow);
			}
		}
	}*/
	
	$scope.createItemList=function(){	
		$scope.entity.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0' } ];//初始
		var items=  $scope.entity.goodsDesc.specificationItems;	
		for(var i=0;i< items.length;i++){
			$scope.entity.itemList = addColumn( $scope.entity.itemList,items[i].attributeName,items[i].attributeValue );    
		}	
	}
	//添加列值 
	addColumn=function(list,columnName,conlumnValues){
		var newList=[];//新的集合
		for(var i=0;i<list.length;i++){
			var oldRow= list[i];
			for(var j=0;j<conlumnValues.length;j++){
				var newRow= JSON.parse( JSON.stringify( oldRow )  );//深克隆
				newRow.spec[columnName]=conlumnValues[j];
				newList.push(newRow);
			}    		 
		} 		
		return newList;
	}
	
});	
	


