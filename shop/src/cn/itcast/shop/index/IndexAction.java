package cn.itcast.shop.index;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.Category;
import cn.itcast.shop.category.CategoryService;
import cn.itcast.shop.product.Product;
import cn.itcast.shop.product.ProductService;

/*
 * 首页访问的Action
 */
public class IndexAction extends ActionSupport{
	//注入一级分类的Service
	private CategoryService categoryService;
	//注入商品的Service
	private ProductService productService;
	//热门商品的集合(要让这些数据被显示在页面上有两种方法 ：一种如下（提供get方法对外开放）  另一种是手动压栈)
	private List<Product> hotList;
	//最新商品的集合	
	private List<Product> newList;
	
	
	
	public List<Product> getNewList() {
		return newList;
	}

	public List<Product> getHotList() {
		return hotList;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;	
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}




	@Override
	/*
	 * 执行首页访问的方法
	 */
	public String execute() throws Exception {
		//查询所有的一级分类
		List<Category> categoryList = categoryService.findAll();
		//存入到Session（不要使用ServletActionContext存放）
		ActionContext.getContext().getSession().put("categoryList", categoryList);
//		ServletActionContext.getContext().getSession().put("categoryList", categoryList);
		//查询热门商品
		hotList = productService.findHot();
		//查询最新商品
		newList = productService.findNew();
		
		return "indexSuccess";
	}
}
