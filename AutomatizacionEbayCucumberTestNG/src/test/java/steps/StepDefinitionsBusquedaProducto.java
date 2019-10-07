package steps;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PageBusquedaEbay;
import pages.Producto;

public class StepDefinitionsBusquedaProducto {
	
	//VARIABLES
	PageBusquedaEbay busquedaEbay;
	String articulo;
	String URL="https://www.ebay.com/";
	String formaOrdenamiento="ascendentemente";
	List<Producto> listaProducto;
	int cantidadProductos;
	
	//LOCATORS
	//BUSCADOR DONDE SE INGRESA EL NOMBRE DEL PRODUCTO
	By buscador=By.id("gh-ac");
	//BOTÓN BUSCADOR DONDE SE DA CLICK
	By botonBuscador=By.id("gh-btn");

	
	
	@Given("^Enter to Ebay usando la URL \"([^\"]*)\"$")
	public void enter_to_Ebay_usando_la_URL(String URL) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("1) Enter to Ebay usando la URL:"+URL);
	    System.out.println("=================================================================");
		busquedaEbay= new PageBusquedaEbay();
		busquedaEbay.getDriver().manage().window().maximize();
		busquedaEbay.getDriver().get(URL);

	}

	@When("^Search for \"([^\"]*)\"$")
	public void search_for(String nombreProducto) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("2) Search for :"+nombreProducto);
		System.out.println("=================================================================");
		//SE ESTABLE EL ARTICULA CON EL VALOR DEL PARAMETRO DEL MÉTODO
		articulo=nombreProducto;
		//BUSCA EL BUSCADOR 
		busquedaEbay.getBuscadorProducto(buscador);
		//ESCRIBE SOBRE EL BUSCADOR Y DA CLICK EL BOTÓN BUSCADOR
		busquedaEbay.buscarProducto(articulo, buscador, botonBuscador);
	}

	@When("^Select brand \"([^\"]*)\"$")
	public void select_brand(String brand) throws Throwable {
		System.out.println("3) Select brand: ");
		System.out.println("=================================================================");
		//NIKE=5; PUMA=6, ETC		
		By marca=busquedaEbay.getWebElementoMarca(brand);
		busquedaEbay.getBuscadorProducto(marca).click();

	}

	@When("^Select size \"([^\"]*)\"$")
	public void select_size(String size) throws Throwable {

		Thread.sleep(3000);
		System.out.println("4) Select size: ");
	    System.out.println("=================================================================");
	    //TALLA 10: 5
	    By talla=busquedaEbay.getWebElementoTalla(size);	    
	    busquedaEbay.getBuscadorProducto(talla).click();//SE LA PASA LA TALLA
	}

	@Then("^Print the number of results$")
	public void print_the_number_of_results() throws Throwable {
		System.out.println("5) Print the number of results:");
	    System.out.println("=================================================================");
		By catidadResultados= busquedaEbay.getWebElementoResultado();
		Thread.sleep(3000);
		WebElement cantidad=busquedaEbay.getBuscadorProducto(catidadResultados);
		busquedaEbay.mostrarResultadoBusqueda(cantidad.getText());	

	}

	@Then("^Order by price \"([^\"]*)\"$")
	public void order_by_price(String tipoOrdenamiento) throws Throwable {
		System.out.println("6) Order by price: ");
	    System.out.println("=================================================================");
		//6- Ordenar por precio ascendente	
		By ordenar=busquedaEbay.getWebElementoTipoOrdenamiento();
		//By tipoOrdenar=By.xpath();
		busquedaEbay.getBuscadorProducto(ordenar).click();
		busquedaEbay.getBuscadorProducto(ordenar).click();
		Thread.sleep(1000);
	//EL MAS BAJO PRIMERO=4, EL MAS ALTO PRIMERO=5
			By tipoOrden=busquedaEbay.getWebElementoTipoOrdenElegido(tipoOrdenamiento);
					
			//SELECCIONAR EL TIPO DE ORDEN
			busquedaEbay.getBuscadorProducto(tipoOrden).click();
	}

	@Then("^Assert the order taking the first (\\d+) results$")
	public void assert_the_order_taking_the_first_results(int numero) throws Throwable {
		this.cantidadProductos=numero;
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(3000);
		System.out.println("7) Assert the order taking the first 5 results");
	    System.out.println("=================================================================");
		
	    //ENLISTAR LOS 5 ELEMENTOS O MÁS
	    listaProducto=busquedaEbay.enlistarProducto(cantidadProductos);
	    
	    //VALIDAR LOS 5 PRIMEROS RESULTADOS
		Assert.assertTrue((listaProducto.get(0).getPrecio()<=listaProducto.get(1).getPrecio())&(listaProducto.get(1).getPrecio()<=listaProducto.get(2).getPrecio())&(listaProducto.get(2).getPrecio()<=listaProducto.get(3).getPrecio())&(listaProducto.get(3).getPrecio()<=listaProducto.get(4).getPrecio()));
			

	}

	@Then("^the first (\\d+) products with their prices and print them in console$")
	public void the_first_products_with_their_prices_and_print_them_in_console(int cantidadImprimir) throws Throwable {
		System.out.println("8) the first 5 products with their prices and print them in console");
	    System.out.println("=================================================================");
		//8- Tome los primeros 5 productos con sus precios e imprímalos en la consola.
	    busquedaEbay.imprimirDescendentementePrecio(cantidadImprimir,listaProducto);
	}

	@Then("^Order and print the products by name \\(ascendant\\)$")
	public void order_and_print_the_products_by_name_ascendant() throws Throwable {
		System.out.println("9) Order and print the products by name (ascendant)");
	    System.out.println("=================================================================");
		//9- Ordenar e imprimir los productos por nombre (ascendente)

	    busquedaEbay.imprimirAscendentementePrecio(cantidadProductos, listaProducto);

	}

	@Then("^Order and print the products by price in descendant mode$")
	public void order_and_print_the_products_by_price_in_descendant_mode() throws Throwable {

		System.out.println("10) Order and print the products by price in descendant mode");
	    System.out.println("=================================================================");
		//10- Ordene e imprima los productos por precio en modo descendiente

	    busquedaEbay.imprimirDescendentementePrecio(cantidadProductos,listaProducto);
	    busquedaEbay.getDriver().quit();
	}

}
