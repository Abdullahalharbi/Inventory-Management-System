/**
 * 
 */
package ims.DataLayer;


import ims.DataLayer.Common.*;
import ims.Model.*;
import java.util.ArrayList;
import java.sql.*;

/**
 * @author Minh, Kumar, Jean
 *
 */
public class DataAccess {
        
        public ArrayList<Login> GetLogin(String user){
            DataBase db = new DataBase(ConnectionType.MYSQL);
            ArrayList<Login> loginInfo = new ArrayList<Login>();
            
            try {
                Connection conn = db.getConnection();
                Statement stmt = conn.createStatement();
                String query = "SELECT username, password, accessType FROM login WHERE username = " + user;
                
                ResultSet rs = stmt.executeQuery(query);
                
                while( rs.next() ){
                    Login result = new Login( rs.getString(1), rs.getString(2), rs.getString(3) );
                    loginInfo.add(result);
                }
                
                rs.close();
                stmt.close();
                conn.close();
            }
            catch(SQLException e) {
                System.err.println("SQL Error(s) as follows:");
                while (e != null) {
                    System.err.println("SQL Return Code: " + e.getSQLState());
                    System.err.println("  Error Message: " + e.getMessage());
                    System.err.println(" Vendor Message: " + e.getErrorCode());
                    e = e.getNextException();
                }	
            } 
            catch(Exception e) {
                System.err.println(e);
            }  
            
            return loginInfo;
        }
    
    
    
	/**
	 * Gets the order list based upon the locality and date 
	 * 
	 */
	public ArrayList<Order> GetOrderList(Location locality){
		
		Location tempLocal = locality;
                //Sample Code
		DataBase db = new DataBase(ConnectionType.ODBC);
                db.getConnection();
                    
		//Create orderList
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		//Create objects according to the selected location
		if (tempLocal == Location.Both)
		{
			//Create branch objects
			Branch branch1 = new Branch("Branch1", "123 Second Street", "8489923412", "Ho Chi Minh");
			Branch branch2 = new Branch("Branch2", "456 Community Street", "8488273456", "Ho Chi Minh");
				
			//Create order1
			ArrayList<Ingredient> ingredients1 = new ArrayList<Ingredient>();
			Ingredient pepperoni = new Ingredient ("Pepperoni", 3, 20, "kilograms");
			Ingredient cheese = new Ingredient ("Cheese", 10, 10, "kilograms");
			Ingredient oil = new Ingredient ("Oil", 5, 2, "liters");
			ingredients1.add(pepperoni);
			ingredients1.add(cheese);
			ingredients1.add(oil);
			Order order1 = new Order(1, "01/01/2011", branch1, 170, ingredients1);
			
			//Create order2
			ArrayList<Ingredient> ingredients2 = new ArrayList<Ingredient>();
			Ingredient egg = new Ingredient ("Egg", 12, 3, "dozens");
			Ingredient italianSausage = new Ingredient ("Sausage", 500, 10, "grams");
			Ingredient oil1 = new Ingredient ("Oil", 600, 2, "milliliters");
			ingredients2.add(egg);
			ingredients2.add(italianSausage);
			ingredients2.add(oil1);
			Order order2 = new Order(2, "01/01/2011", branch2, 42.2, ingredients2);
			
			//Create order3
			ArrayList<Ingredient> ingredients3 = new ArrayList<Ingredient>();
			Ingredient dough = new Ingredient ("Dough", 10, 5, "kilograms");
			Ingredient bacon = new Ingredient ("Bacon", 5, 10, "kilograms");
			ingredients3.add(dough);
			ingredients3.add(bacon);
			Order order3 = new Order(3, "05/05/2011", branch1, 100, ingredients3);
			
			//Create order4
			ArrayList<Ingredient> ingredients4 = new ArrayList<Ingredient>();
			Ingredient tuna = new Ingredient ("Tuna", 10, 50, "kilograms");
			Ingredient milk = new Ingredient ("Milk", 10, 2, "liters");
			ingredients4.add(tuna);
			ingredients4.add(milk);
			Order order4 = new Order(4, "05/05/2011", branch2, 520, ingredients4);
			
			//Add orders in orderlist
			orderList.add(order1);
			orderList.add(order2);
			orderList.add(order3);
			orderList.add(order4);
		}
		
		else if (tempLocal == Location.Branch1)
		{
			//Create branch object
			Branch branch1 = new Branch("Branch1", "123 Second Street", "8489923412", "Ho Chi Minh");
			
			//Create order1
			ArrayList<Ingredient> ingredients1 = new ArrayList<Ingredient>();
			Ingredient pepperoni = new Ingredient ("Pepperoni", 3, 20, "kilograms");
			Ingredient cheese = new Ingredient ("Cheese", 10, 10, "kilograms");
			Ingredient oil = new Ingredient ("Oil", 5, 2, "liters");
			ingredients1.add(pepperoni);
			ingredients1.add(cheese);
			ingredients1.add(oil);
			Order order1 = new Order(1, "01/01/2011", branch1, 170, ingredients1);
			
			//Create order3
			ArrayList<Ingredient> ingredients3 = new ArrayList<Ingredient>();
			Ingredient dough = new Ingredient ("Dough", 10, 5, "kilograms");
			Ingredient bacon = new Ingredient ("Bacon", 5, 10, "kilograms");
			ingredients3.add(dough);
			ingredients3.add(bacon);
			Order order3 = new Order(3, "05/05/2011", branch1, 100, ingredients3);
			
			//Add orders in orderlist
			orderList.add(order1);
			orderList.add(order3);
		}
		
		else if (tempLocal == Location.Branch2)
		{
			//Create branch objects
			Branch branch2 = new Branch("Branch2", "456 Community Street", "8488273456", "Ho Chi Minh");
			
			//Create order2
			ArrayList<Ingredient> ingredients2 = new ArrayList<Ingredient>();
			Ingredient egg = new Ingredient ("Egg", 12, 3, "dozens");
			Ingredient italianSausage = new Ingredient ("Italian Sausage", 500, 10, "grams");
			Ingredient oil1 = new Ingredient ("Oil", 600, 2, "milliliters");
			ingredients2.add(egg);
			ingredients2.add(italianSausage);
			ingredients2.add(oil1);
			Order order2 = new Order(2, "01/01/2011", branch2, 42.2, ingredients2);
			
			//Create order4
			ArrayList<Ingredient> ingredients4 = new ArrayList<Ingredient>();
			Ingredient tuna = new Ingredient ("Tuna", 10, 50, "kilograms");
			Ingredient milk = new Ingredient ("Milk", 10, 2, "liters");
			ingredients4.add(tuna);
			ingredients4.add(milk);
			Order order4 = new Order(4, "05/05/2011", branch2, 520, ingredients4);
			
			//Add orders in orderlist
			orderList.add(order2);
			orderList.add(order4);
		}
		return orderList;
	}

	
	/**
	 * Gets all the pizza's that are currently in the system
	 * @return  
	 */
	public ArrayList<Pizza> GetPizzaList() {
		
		//Create pizzaList
		ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
		
		//Create base ingredients dough, oil, and cheese for differensize of pizza
		Ingredient cheeseL = new Ingredient("Cheese", 12, 0, "grams");
		Ingredient oilL = new Ingredient ("Oil", 15, 0, "milliliters");
		Ingredient doughL = new Ingredient ("Dough", 300, 0, "grams");
		Ingredient cheeseM = new Ingredient("Cheese", 12, 0, "grams");
		Ingredient oilM = new Ingredient ("Oil", 10, 0, "milliliters");
		Ingredient doughM = new Ingredient ("Dough", 250, 0, "grams");
		Ingredient cheeseS = new Ingredient("Cheese", 10, 0, "grams");
		Ingredient oilS = new Ingredient ("Oil", 10, 0, "milliliters");
		Ingredient doughS = new Ingredient ("Dough", 200, 0, "grams");
		
		//Create pepperoni pizza size large
		Ingredient pepperoniPL = new Ingredient("Pepperoni", 2, 0, "grams");
		ArrayList<Ingredient> ingredientsPL = new ArrayList<Ingredient>();
		ingredientsPL.add(pepperoniPL);
		ingredientsPL.add(cheeseL);
		ingredientsPL.add(oilL);
		ingredientsPL.add(doughL);
		Pizza pepperoniLarge = new Pizza ("Pepperoni", "Large", ingredientsPL);
		
		//Create pepperoni pizza size medium
		Ingredient pepperoniPM = new Ingredient("Pepperoni", 3, 0, "grams");			
		ArrayList<Ingredient> ingredientsPM = new ArrayList<Ingredient>();
		ingredientsPM.add(pepperoniPM);
		ingredientsPM.add(cheeseM);
		ingredientsPM.add(oilM);
		ingredientsPM.add(doughM);		
		Pizza pepperoniMedium = new Pizza ("Pepperoni", "Medium", ingredientsPM);
		
		//Create pepperoni pizza size small
		Ingredient pepperoniPS = new Ingredient("Pepperoni", 2, 0, "grams");					
		ArrayList<Ingredient> ingredientsPS = new ArrayList<Ingredient>();
		ingredientsPS.add(pepperoniPS);
		ingredientsPS.add(cheeseS);
		ingredientsPS.add(oilS);
		ingredientsPS.add(doughS);
		Pizza pepperoniSmall = new Pizza ("Pepperoni", "Small", ingredientsPS);
		
		//Create meat lover pizza size large
		Ingredient italianSausageMLL = new Ingredient("Sausage", 9, 0, "grams");
		Ingredient baconMLL = new Ingredient("Bacon", 9, 0, "grams");
		ArrayList<Ingredient> ingredientsMLL = new ArrayList<Ingredient>();
		ingredientsMLL.add(doughL);
		ingredientsMLL.add(cheeseL);
		ingredientsMLL.add(oilL);
		ingredientsMLL.add(italianSausageMLL);
		ingredientsMLL.add(baconMLL);
		Pizza meatLoverLarge = new Pizza ("Meat Lover", "Large", ingredientsMLL);
		
		//Create meat lover pizza size medium
		Ingredient italianSausageMLM = new Ingredient("Sausage", 7, 0, "grams");
		Ingredient baconMLM = new Ingredient("Bacon", 7, 0, "grams");
		ArrayList<Ingredient> ingredientsMLM = new ArrayList<Ingredient>();
		ingredientsMLM.add(doughM);
		ingredientsMLM.add(cheeseM);
		ingredientsMLM.add(oilM);
		ingredientsMLM.add(italianSausageMLM);
		ingredientsMLM.add(baconMLM);
		Pizza meatLoverMedium = new Pizza ("Meat Lover", "Medium", ingredientsMLM);
		
		//Create meat lover pizza size medium
		Ingredient italianSausageMLS = new Ingredient("Sausage", 5, 0, "grams");
		Ingredient baconMLS = new Ingredient("Bacon", 5, 0, "grams");
		ArrayList<Ingredient> ingredientsMLS = new ArrayList<Ingredient>();
		ingredientsMLS.add(doughS);
		ingredientsMLS.add(cheeseS);
		ingredientsMLS.add(oilS);
		ingredientsMLS.add(italianSausageMLS);
		ingredientsMLS.add(baconMLS);
		Pizza meatLoverSmall = new Pizza ("Meat Lover", "Small", ingredientsMLS);
		
		pizzaList.add(pepperoniLarge);
		pizzaList.add(pepperoniMedium);
		pizzaList.add(pepperoniSmall);
		pizzaList.add(meatLoverLarge);
		pizzaList.add(meatLoverMedium);
		pizzaList.add(meatLoverSmall);
		
		return pizzaList;
	}
	
	
	/**
	 * Returns the ingredients for a specified pizza name and pizza size
	 * @param pizzaName
	 * @param pizzaSize
	 * @return
	 */
	public ArrayList<Ingredient> GetIngredients(String pizzaName, String pizzaSize){
		
		ArrayList<Pizza> pizzaList = GetPizzaList();
		
		for (Pizza pizza : pizzaList) {
			
			if (pizza.getPizzaName().equalsIgnoreCase(pizzaName) && pizza.getPizzaSize().equalsIgnoreCase(pizzaSize)) {
				
				return pizza.getIngredients();
			
			}
			
		}
		
		return null;
	}
}
