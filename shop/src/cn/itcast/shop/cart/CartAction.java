package cn.itcast.shop.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.product.Product;
import cn.itcast.shop.product.ProductService;

/**
 * 购物模块的Action类
 * @author paperwings
 *
 */
public class CartAction extends ActionSupport{
	//接收pid
	private Integer pid;
	//接收数量count
	private Integer count;
	//注入ProductService
	private ProductService productService;
	
	
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}


	
	public void setCount(Integer count) {
		this.count = count;
	}

	


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	/*
	 * 从session范围获取购物车
	 */
	public Cart getCart(HttpServletRequest request){
		//从session范围获取cart对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//判断
		if(cart == null){
			//创建购物车对象
			cart = new Cart();
			//将购物车对象放入session范围
			request.getSession().setAttribute("cart", cart);
			request.getSession().setAttribute("test", "test");////////////////////////
		}
		return cart;
	}

	/*
	 * 添加到购物车的方法：
	 */
	public String addCart(){
		//查询商品信息:
		Product product = productService.findByPid(pid);
		//创建一个购物项:
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);
		//获取购物车依赖对象request
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		
		return "addCartSuccess";
		
	}
}
