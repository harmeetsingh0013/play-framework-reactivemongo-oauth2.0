# Scala Oauth 2.0 using Play-Framework 2.3.x 

In this sample application we are using **Oauth2.0** for creating API with **Play-Framework 2.3.x** , **ReactiveMongo-Extensions**, **Cake-Pattern** and **Scala-Oauth2-Provider**. We are using Oauth-2.0 just for secure my web-serivces using token based Authentication. This sample Oauth application similar behave like **Spring-Security-Oauth2.0** for token based authentication. Following are the reference that help me for create a sample application.   
**NOTE: We are using JSON Based format with Reactive-Mongo**

* [Play-Frmework 2.3.x](https://www.playframework.com/documentation/2.3.x/Highlights23)
* [ReactiveMongo-Extensions](https://github.com/ReactiveMongo/ReactiveMongo-Extensions)
* [Cake-Pattern](http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth)
* [Scala-Oauth2-Provider](https://github.com/nulab/scala-oauth2-provider)

As we discuss this sample is just for Token based authentication, we are also customize Oauth2 according to our requirements. In this we are using just two collections for authentication as bewlow: 
```sh
`users` Collection: 
{
    "_id" : ObjectId("553bdcdb91000091001f8e95"),
    "name" : "Harmeet",
    "email" : "har@user.com",
    "password" : "123456",
    "grantType" : "password"
}

`accessToken` Collection 
{
    "_id" : ObjectId("553c9580410000cc02fe566c"),
    "accessToken" : "ZTc2YjNkNzQtOWU1ZC00NmQzLThiZjUtMzY0NjhlMmM2Y2Zh",
    "refreshToken" : "Njk4MWFlMzgtYjIzYS00MTZhLTk4YzItZTVhMzYzODg2NDVj",
    "userId" : ObjectId("553bdcdb91000091001f8e95"),
    "scope" : "",
    "expiresIn" : 3600.0000000000000000,
    "createdAt" : ISODate("2015-04-26T07:36:32.049Z"),
    "clientId" : "har@user.com"
}
```

When the user send the request for accessing **accessToken**, if the token is exist and not expire return the token other wise create token and remove old one. We are using password grant type and following is the structure of our request: 

```sh
URL: http://localhost:9000/oauth2/token
POST Params: grant_type=password&client_id=har@user.com&client_secret=123456&username=har@user.com&password=123456
```
For run this sample application below are the steps:
- Start the mongo db server and change the cridentials according to you server in **application.conf** file.
- Create the two collections **users** and **accessToken**.
- Add the dummy user entry in the **users** collection.
- Download and extract the code and run the command **activator ui**.
- Compile and Build the code.
- Run the code and use rest client for access you token as above request structure. 
- After getting token, Access other resources you need to set token in header as below: 
    -   Authorization: Bearer **your token**

For access every secure resource we need to add our token in header as above. 
