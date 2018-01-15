package cn.itcast.shop.product;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.web.context.request.NativeWebRequest;

import cn.itcast.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	//Dao层查询热门商品 只显示10个（分页查询）
	public List<Product> findHot() {
		//方法1：（缺点：当条件比较复杂时比较麻烦）
//		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
//		criteria.add(Restrictions.eq("is_hot", 1));
//		List<Product> list =  this.getHibernateTemplate().findByCriteria(criteria,0,10);
		
		//方法2：
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>("from Product where is_hot=?", new Object[]{1}, 0, 10));
		return list;
	}
	
	//Dao层查询最新商品
	public List<Product> findNew() {
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>("from Product order by pdate desc", null, 0, 10));
		return list;
	}

	//统计某个分类下的商品的数量
	public Integer findCountByCid(Integer cid) {
		//SQL:select count(*) from product p,categorysecond cs where p.csid = cs.csid and cs.cid = 1;
		//select p from Product p ,CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?
		List<Long> list = this.getHibernateTemplate().find("select count(*) from Product p,CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?",cid);
//		System.out.println("list===================:"+list.get(0).intValue());
		return list.get(0).intValue();
	}

	public List<Product> findByPage(Integer cid, int begin, int limit) {
		String hql = "select p from Product p,CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		return list;
	}

	public Product findByPid(Integer pid) {
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	
	//统计某个二级分类下的商品数量
	public Integer findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p join p.categorySecond cs where cs.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		return list.get(0).intValue();
	}
	
	
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		return list;
	}

}
