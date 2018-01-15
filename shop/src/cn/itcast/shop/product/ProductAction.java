package cn.itcast.shop.product;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.Category;
import cn.itcast.shop.category.CategoryService;
import cn.itcast.shop.utils.PageBean;

/**
 * 商品访问的Action类
 * @author paperwings
 *
 */


public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//接受cid
	private Integer cid;
	//接收二级分类id
	private Integer csid;
	//接收当前页数
	private Integer page;
	//注入一级分类的Service
	private CategoryService categoryService;
	//注入ProductService
	private ProductService productService;
	//分页商品显示
	private PageBean<Product> pageBean;
	//模型驱动类
	private Product product = new Product();
	
	
	
	

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	
	public Integer getCsid() {
		return csid;
	}


	public PageBean<Product> getPageBean() {
		return pageBean;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
	//提供get方法使页面能够获取Cid
	public Integer getCid() {
		return cid;
	}


	//查询商品的方法
	public String findByCid() {
		//查询分类：
		//查询所有一级分类：
		 List<Category> categoryList = categoryService.findAll();
		 //获得值栈
		 ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		
		//查询商品：
		pageBean =  productService.findByPage(cid,page); 
		 
		 return "findByCidSuccess";
		
		
	}
	
	//查询商品详情
	public String findByPid() {
		//查询所有一级分类：
		 List<Category> categoryList = categoryService.findAll();
		 //获得值栈
		 ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		
		product = productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}

	@Override
	public Product getModel() {
		return product;
	}
	
	//查询二级分类下的商品：
	public String findByCsid(){
		//查询所有一级分类：
		List<Category> categoryList = categoryService.findAll();
		//获得值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		pageBean = productService.findByCsid(csid,page);
		return "findByCsidSuccess";
	}
	
}
