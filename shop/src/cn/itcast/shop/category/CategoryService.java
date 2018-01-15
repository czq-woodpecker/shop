package cn.itcast.shop.category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

//开启事务
@Transactional
public class CategoryService {
	//注入Dao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//业务层查询所有的一级分类的方法
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
}
