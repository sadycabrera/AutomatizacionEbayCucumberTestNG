Feature: Busqueda de Articulo
  Yo, como cliente de Ebay, quiero poder buscar artículos en la página web por 

Scenario: Title of your scenario

Given Enter to Ebay usando la URL "https://www.ebay.com/"
When Search for "shoes"
And Select brand "PUMA"
And Select size "10"
Then Print the number of results
And Order by price "DE MENOR A MAYOR PRECIO"
And Assert the order taking the first 5 results
And  the first 5 products with their prices and print them in console
And Order and print the products by name (ascendant)
And Order and print the products by price in descendant mode

