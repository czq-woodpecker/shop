package cn.itcast.shop.product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.iap.Literal;

import cn.itcast.shop.utils.PageBean;

@Transactional
public class ProductService {
	//注入Dao（需要提供set方法）
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//业务层查询热门商品
	public List<Product> findHot() {
		return productDao.findHot();
	}

	//业务层查询最新商品
	public List<Product> findNew() {
		return productDao.findNew();
	}

	//分类页面的显示商品的方法
	public PageBean<Product> findByPage(Integer cid, Integer page) {
		int limit = 12;//每页显示的记录数
		int totalPage = 0;//总页数
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(12);
		//查询总的记录数
		Integer totalCount =  productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		//总页数的封装：
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//商品数据集合
		int begin = (page - 1) * limit;
		List<Product> list =  productDao.findByPage(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//业务层查询商品详情的方法
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	//业务层查询二级分类下的商品
	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		int limit = 8;//每页记录数
		int totalPage = 0;//总页数
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		//总记录数
		Integer totalCount = productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		//计算总的页数
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//数据的集合：
		int begin = (page -1 ) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	
	
}
