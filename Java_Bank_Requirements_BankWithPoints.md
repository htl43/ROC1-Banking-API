# Banking - API

## Executive Summary

The Bank app is a console-based application that simulates banking operations. A customer can apply for an account, view their balance, and make withdrawals and deposits. An employee can approve or deny accounts and view account balances for their customers.

**Activity Diagram**

![](./imgs/activity.jpg)

## Technologies / Dependencies

- Maven build automation tool
- JDBC and Postgres database
- Servlet and Tomcat server
- Web: HTML, CSS, Bootstrap, JavaScript, Fetch API
- Version Control Github
- Jenkins pipline
- Remote cloud database with Amazon RDS
- Virtual machine with Amazon EC2
- Host static website with Amazon S3

## Features

1. Constructing an End point for create User Account by using Postman
2. Encrypted password before storing in database
3. Functionality for User login to account from website
4. Session is stored for User credential after login
5. Ability for Employee Account can submit a reimbursment or view them all.
6. Ability for Manager Account can view all reimbursments
7. Ability for Manager can filter past or pending reimbursments
8. Ability for Manager can apporve or deny a pending reimbursment

## Strech Goals:

- Replace JDBC with Hibernate to manage the database connection.
- Users can upload a document or image of their receipt when submitting reimbursements which can stored in the database and reviewed by a financial manager.
- Using Angular for Frontend

## Getting Started

1. Clone the project to your local machine and open it with a JDK
2. Install a Postgres database in local or remoted host(RDS)
3. Config database connecion url, username and password in package: com.revature.ers.utilities
4. Create database following:

**ER Diagram**

![](./imgs/physical.jpg)

5. Maven Update to build dependencies
6. Install Tomcat on running enviroment

**7. Runing on Local Host**

- Open CorsFillter.java in com.reavature.ers.web.fillters
  - Change the res.setHeader("Access-Control-Allow-Origin", "null") for local machine
- Run Maven project on local Tomcat server
- Open frontend folder
  - Modify the const url for all javascript to localhost:8080
  - Open the user.html to start application

**7. Runing with AWS RDS, EC2, S3**

- Upload frontend files to S3 bucket
  - Deploy a Static Website Hosting to get endpoint
  - Setting permissions: public access, bucket policy and Cross-origin resource sharing (CORS)
- Open CorsFillter.java in com.reavature.ers.web.fillters
  - Change the res.setHeader("Access-Control-Allow-Origin", _"hosting endpoint"_) for local machine
- Setup RDS runing
- Setup EC2 runing and get public IP address
  - Install Jenkins and run service on EC2
  - Install Tomcat9 and run service on EC2 with config own port
  - Config EC2 Inbound and Outbound for Jenkins, Tomcat, Postgres and other trafics
- Create a repository on github account
  - Add and push project to the repository
  - Adding webhook to the repository that connect to Jenkins IP address
- Login to the admin account on Jenkin server
  - Create a new freestyle item
  - Config for Source Code Management which the Repository URL
  - Add the command Execute Shell in Buid tab:
  ```
  mvn clean package
  rm -f /**/tomcat9/webapps/_.war
  mv target/_.war /**/tomcat9/webapps/
  ```
  - Save and run "Build Now"
- Open Website Hosting endpoint in S3 to start application

  ## Usage

1. Use Postman to create employee or manager account

   ![](./imgs/create.jpg)

2. Go to website and login with your created account

   ![](./imgs/emp-login.jpg)

3. Select **Request A Reimbursment** from Dropdown Menu, fill in information and click **Submit** button. Click **OK** to confirm your submit.

   ![](./imgs/submit-rib.jpg)

4. Select **View All Reimbursment** from Dropdown Menu to view all your submited reimbursment

   ![](./imgs/emp-view.jpg)

5. Login to the app with a manager account

   ![](./imgs/mng-login.jpg)

6. Select **View All Reimbursment** from Dropdown Menu to view all employee submited reimbursment

   ![](./imgs/mng-viewall.jpg)

7. Select **View Pending Reimbursment** from Dropdown Menu to view all pending reimbursment.Choose Reimbursment Id and approved or deny status, then click **confirm**

   ![](./imgs/mng-dec.jpg)

8. Select **View Past Reimbursment** from Dropdown Menu to view all past reimbursment

   ![](./imgs/mng-viewpast.jpg)

## License

MIT
