# Apteka
This project I create for recruitment purpose to PGS 
Project I create is online pharmacy.I have created 3 tables: User, Warehouse and Medicine. In Medicine I put name of medicine, price of it, decription and date when we can use it. In Warehouse table I put information about pharmacy: which customers doing here shop, what kind of medicine we can finf there and the name of warehouse. In User table we have 2 roles: User and Admin. Each of them can do another thing. 
<b>Admin can do: <br/></br>
*create medicine <br/>
*delete medicine <br/>
*see list of all medicine<br/>
*see details of one medicine<br/>
*see list sorted by name of medicine<br/>
*see pages with list of mediciens<br/>
*find by name of medicine<br/>
*update the information of medicine<br/>
*update infroamtion about themself<br/>
*delete users<br/>
*see list of users<br/>
*see detail about one user<br/>
*see sorted list of users<br/>
*see page with list of users<br/>
*find user by login<br/>
*find user by surname<br/>
*create account for themself and log in<br/>
*create warehouse</br>
*delete warehouse<br/>
*see detail of one warehouse<br/>
*see list of warehouse<br/>
*see sorted list of warehouse<br/>
*see page with list of warehouses<br/>
*find warehouse by name<br/>
<b>To become Admin in Postman You need write address: localhost:8080/api/register and POST with JSON:<br/>
{
	"login":"Some login",<br/>
    "name":"Some name", <br/>
    "surname":"Some surname", <br/>
    "password":"Some password", <br/>
    "role":"Admin" <-- it's important. Thanks to it You become admin
}
<br/>
	    User can do:</b>
*see list of all medicine<br/>
*see details of one medicine<br/>
*see list sorted by name of medicine<br/>
*see pages with list of medicien<br/>
*find by name of medicine<br/>
*update inforamtion about themself<br/>
*create account for themself and log in<br/>
*see detail of one warehouse<br/>
*see list of warehouse<br/>
*see sorted list of warehouse<br/>
*see page with list of warehouse<br/>
*find warehouse by name<br/>
<b>To become User in Postman You need write address: localhost:8080/api/register and POST with JSON:<br/>
{
	"login":"Some login",<br/>
    "name":"Some name", <br/>
    "surname":"Some surname", <br/>
    "password":"Some password", <br/>
    "role":"User" <-- it's important. Thanks to it You become user
		       }</b>
<br/>
If You not logged You can't do anything. If You want logged type localhost:8080/api/login and GET and 
{
	"login":"User",
	"password":"User"
}
in Postman. 
Application can validate if You type empty data, if the login is already in use, if You update yourself not another user - and it throw some exception. I also hashed user's password and store it as a hash in the database. 


