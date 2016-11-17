# Msc Computer Science Individual Project

**Linking Charities Worldwide - MSc Computer Science Imperial College London Individual Project**

![Main](https://github.com/magdalenek/masters-project/raw/master/project-sample/view/main.gif)


**Project Summary:** There is a large number of non-profit organisations operating around the world, with over 165,000 registered bodies in UK solely. However, a majority of all the yearly funds of more than 250 billion dollars raised by individuals collectively are distributed almost exclusively between a small number of non-profit organisations - those that are either the largest, or have the greatest media presence. 

The aim of this project was to develop a proof-of-concept product, providing the non-profit organisations around the world with an option to list themselves in an online platform. The intent behind the platform was to present the smaller charities with much needed exposure that they cannot often afford and so desperately need. Such a solution was additionally intended to provide the potential donors with an alternative choice of charities to donate to, and as a result helping twice - aiding the chosen cause and the charity as such.


**Selected Technologies Used:**
+ Front End: Custom css design with Twitter Bootstrap, vanilla JavaScript, jQuery, d3.js, Thymeleaf.
+ Back end: Spring Boot, MongoDb, JavaMail, RESTful API.


**Architecture Design:**
[![Screen Shot 2016-11-17 at 13.50.39.png](https://s21.postimg.org/3qnqw84p3/Screen_Shot_2016_11_17_at_13_50_39.png)](https://postimg.org/image/4g6j8l58j/)


**Main Features:**
- Interactive selection of country the charity resides in from the animated globe
- Charity Search
  - CRUD application allowing for conduct query based searches of charities with custom pagination and sorting 
-  Charity Verifier (2 step process for the UK, email only for Rest of the World)
 - Step 1: comparison of registered data with data obtained from Charity registry that was parsed and stored in project database
 - Step 2: email token-based verification of registered details
- Admin Panel
  -	Custom admin panel to manage and manually approve charity registrations
  -	Live filtering of data conducted in the front end
  -	Note-taking available for each entry
  
  ![Admin](https://github.com/magdalenek/masters-project/raw/master/project-sample/view/admin.gif)
