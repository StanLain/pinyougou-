 //控制层 
app.controller('itemCatController' ,function($scope,$controller   ,itemCatService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		itemCatService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){	
		var serviceObject; 	
		
		if($scope.entity.id!=null){
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			$scope.entity.parentId=$scope.parentId
			serviceObject=itemCatService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
					$scope.findByParentId($scope.parentId)
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	/*//新建
	$scope.add=function(){
		
		$scope.entity.parentId=$scope.parentId
		itemCatService.add($scope.entity).success(
				function(response){
					if(response.success){
						//重新查询 
						$scope.findByParentId($scope.parentId)
					}else{
						alert(response.message);
					}
				}		
			);				
	}*/
	
	
	$scope.i=0;
	//批量删除 
	$scope.dele=function(){		
			for($scope.i=0;$scope.i<$scope.selectIds.length;$scope.i++){
				itemCatService.findByParentId($scope.selectIds[$scope.i]).success(
						function(response){
							if(response.length>0){
								itemCatService.findOne(response[0].parentId).success(
										function(response){
											$scope.findByParentId($scope.parentId);
											$scope.selectIds=[];
											alert(response.name+'删除失败，子集有内容，不能删除');
										}
								)
							}else{		
								//索引需要减去1！！！
								itemCatService.dele( $scope.selectIds[$scope.i-1]).success(
										//有子类信息的不让	
										function(response){
											if(response.success){
												$scope.findByParentId($scope.parentId)
												$scope.selectIds=[];
											}						
										}	
									);		
							}
						}
				)
			};
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		itemCatService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	$scope.findByParentId=function(parentId){
		itemCatService.findByParentId(parentId).success(
				function(response){
					$scope.list=response;	
					}
		)
	}
	//完成面包屑
	//分级
	$scope.grade=1;
	$scope.setGrade=function(value){
		$scope.grade=value;
	}
	//分级显示
	$scope.selectList=function(p_entity){
		if($scope.grade==1){
			$scope.entity1=null;
			$scope.entity2=null;
		}
		if($scope.grade==2){
			$scope.parentId=p_entity.id;
			$scope.entity1=p_entity;
			$scope.entity2=null;
		}
		if($scope.grade==3){
			$scope.parentId=p_entity.id;
			$scope.entity2=p_entity;
		}
		$scope.findByParentId(p_entity.id);
	}
	
	$scope.parentId=0;
	
	
});	
