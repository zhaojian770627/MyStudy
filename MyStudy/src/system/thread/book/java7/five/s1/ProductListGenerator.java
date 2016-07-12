package system.thread.book.java7.five.s1;

import java.util.ArrayList;
import java.util.List;

/**
 * This class generates a product list of a determined size.
 * Each product is initialized with a predefined name and price.
 *
 */
public class ProductListGenerator {

	/**
	 * This method generates the list of products
	 * @param size the size of the product list
	 * @return the generated list of products
	 */
	public List<Product> generate (int size) {
		List<Product> ret=new ArrayList<Product>();
		
		for (int i=0; i<size; i++){
			Product product=new Product();
			product.setName("Product "+i);
			product.setPrice(10);
			ret.add(product);
		}
		
		return ret;
	}

}
