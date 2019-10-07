package pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageBusquedaEbay {
	private WebDriver driver;
	private WebElement webElement;
	private List<Producto> listaProducto;
	
	public PageBusquedaEbay() {
		File Archivo = new File(System.getProperty("user.dir"));
		String Path = Archivo.getAbsolutePath() + "\\webDriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", Path);
		 driver = new ChromeDriver();
	}

	public WebElement getBuscadorProducto(By elemento)
	{

		return driver.findElement(elemento);
			
	}
	
	public WebElement getBuscadorProductoXpath(String elemento)
	{

		return driver.findElement(By.xpath(elemento));
			
	}
	
	//Metodo que recibe como parametro el articulo a buscar
	public void buscarProducto(String descripcion, By buscador, By boton)
	{

		getBuscadorProducto(buscador).sendKeys(descripcion);
		//OOTIENE EL BOTON BUSCAR y HACE CLICK SOBRE EL
		getBuscadorProducto(boton).click();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getWebElement() {
		return webElement;
	}

	public void setWebElement(WebElement webElement) {
		this.webElement = webElement;
	}
	
	//MUESTRA LA CANTIDAD DE ARTICULOS EN EL RESULTADOS DE LA BUSQUEDA
	public void mostrarResultadoBusqueda(String cantidad)
	{
		System.out.println("Cantidad de Resultados: "+cantidad);
	}
	
	public By getWebElementoMarca(String brand)
	{
		By marca=null;
		switch(brand) {
		case "PUMA": marca=By.xpath("//*[@id=\"w4-w13\"]/ul/li[6]/div/a/div/input");break;
		case "NIKE": marca=By.xpath("//*[@id=\"w4-w13\"]/ul/li[5]/div/a/div/input");break;
		}
		
		return marca;
	}
	
	public By getWebElementoTalla(String size)
	{
		 By talla=null;
		switch(size) {
		case "10":talla=By.xpath("//*[@id=\"x-refine__group_1__0\"]/ul/li[5]/div/a/div/input");break;
		case "10.5": talla=By.xpath("//*[@id=\"x-refine__group_1__0\"]/ul/li[6]/div/a/div/input");break;
		}
		
		return talla;
	}
	
	public By getWebElementoResultado()
	{
		By catidadResultados= By.xpath("//*[@id=\"mainContent\"]/div[1]/div/div[2]/div/div[1]/h1/span[1]");
		return catidadResultados;
	}
	
	public By getWebElementoTipoOrdenamiento()
	{
		By ordenar=By.xpath("//*[@id=\"w8\"]/button/div");
		return ordenar;
	}
	
	public By getWebElementoTipoOrdenElegido(String tipoOrdenElegido)
	{
		//EL MAS BAJO PRIMERO=4, EL MAS ALTO PRIMERO=5
		By tipoOrden=null;
		switch(tipoOrdenElegido) {
		case "DE MENOR A MAYOR PRECIO":tipoOrden=By.xpath("//*[@id=\"w8\"]/div/div/ul/li[4]");break;
		case "DE MAYOR A MENOR PRECIO":tipoOrden=By.xpath("//*[@id=\"w8\"]/div/div/ul/li[5]");break;
		}
		
		return tipoOrden;
	}
	
	public List<Producto> enlistarProducto(int cantidadProductos)
	{
		By primerPrecio=null;
		Double precio=0.0;		
		//Tome los primeros 5 productos con sus precios e imprímalos en la consola: 
		By nombrePrimerProducto=null;
		String nombreProductoPrimero=null;
		//CREAR UNA LISTA CON LOS 5 PRIMEROS PROODUCTOS ORDENAS ACEDENTEMENTE POR PRECIO
		List<Producto> listaProducto=new ArrayList<Producto>();
		for(int i=1; i<=cantidadProductos;i++)
		{
			primerPrecio=By.xpath("//*[@id=\"srp-river-results-listing"+i+"\"]/div/div[2]/div[3]/div[1]/span");
			precio=new Double(getBuscadorProducto(primerPrecio).getText().toString().substring(3,9));
			nombrePrimerProducto=By.xpath("//*[@id=\"srp-river-results-listing"+i+"\"]/div/div[2]/a/h3");
			nombreProductoPrimero=getBuscadorProducto(nombrePrimerProducto).getText().toString();
			Producto primerProducto= new Producto(nombreProductoPrimero, precio);
			listaProducto.add(primerProducto);
		}
		
		return listaProducto;
	}
	
	public void imprimirDescendentementePrecio(int cantidadProductos, List<Producto> lista)
	{
		for(int i=0;i<cantidadProductos;i++)
		{
			System.out.println("Primer precio "+lista.get(i).getNombre());
			System.out.println("Primer precio "+lista.get(i).getPrecio());
		
		}
	}
	
	public void imprimirAscendentementePrecio(int cantidadProductos, List<Producto> lista)
	{
		for(int i=cantidadProductos-1;i>=0;i--)
		{
			System.out.println("Primer precio "+lista.get(i).getNombre());
			System.out.println("Primer precio "+lista.get(i).getNombre());
			
		}
	}

	
	
	
	
	
	
	

}
