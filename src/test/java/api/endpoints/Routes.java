 package api.endpoints;

/* 
Swagger URI --> https://petstore.swagger.io

Create user(Post) : https://petstore.swagger.io/v2/user
Get user (Get): https://petstore.swagger.io/v2/user/{username}
Update user (Put) : https://petstore.swagger.io/v2/user/{username}
Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

*/

public class Routes {
 //inside this routes class, I will maintain ONLY URL's from swagger doc
	
	public static String base_url = "https://petstore.swagger.io/v2"; //static: call this variable using classname without creating object.
	
	//User module :url's from pet store swagger doc
	public static String post_url= base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	//Store module :url's from pet store swagger doc
	
	//Pet module :url's from pet store swagger doc
}
