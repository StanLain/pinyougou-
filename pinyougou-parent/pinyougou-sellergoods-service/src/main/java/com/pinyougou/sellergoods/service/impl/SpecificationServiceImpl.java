package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		//使用组合类添加
		TbSpecification tbSpecification= specification.getTbSpecification();
		specificationMapper.insert(tbSpecification);
		Long id=tbSpecification.getId();//获取存储后的规格id
		
			List<TbSpecificationOption> all=specification.getSpecificationOptionList();
			
			for(TbSpecificationOption tbSpecificationOption:all) {
				tbSpecificationOption.setSpecId(id);//给规格选项 添加specId
				specificationOptionMapper.insert(tbSpecificationOption);
		}
		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
	
		//使用组合类添加
		TbSpecification tbSpecification= specification.getTbSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		//删除原来的数据
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria=example.createCriteria();
		criteria.andSpecIdEqualTo(tbSpecification.getId());
		
		specificationOptionMapper.deleteByExample(example);
		//在添加数据
		List<TbSpecificationOption> all=specification.getSpecificationOptionList();
			
		for(TbSpecificationOption tbSpecificationOption:all) {
			tbSpecificationOption.setSpecId(tbSpecification.getId());//给规格选项 添加specId
			specificationOptionMapper.insert(tbSpecificationOption);
		}
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		Specification specification=new Specification();
		specification.setTbSpecification(specificationMapper.selectByPrimaryKey(id));
		//设置一对多返回值
		//设置查询条件是specificationOption表的SpecId值和specification表的id相同SpecIdEqualTo(id)
		TbSpecificationOptionExample tbSpecificationOptionExample=new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria=tbSpecificationOptionExample.createCriteria();
		criteria.andSpecIdEqualTo(id);
		
		List<TbSpecificationOption> specificationOptionList=specificationOptionMapper.selectByExample(tbSpecificationOptionExample);
		specification.setSpecificationOptionList(specificationOptionList);
		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			// 删除specificationOption表的关联数据
			TbSpecificationOptionExample example=new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria=example.createCriteria();
			criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public List<Map> findSelectOptionList() {
		
			return specificationMapper.selectOptionList();
		}
	
}
