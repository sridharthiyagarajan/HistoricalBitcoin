# HISTORICAL BITCOIN SOLUTION

## ********** SCOPE DOCUMENT **********
[ScopeDocument.docx](https://github.com/user-attachments/files/18628971/ScopeDocument.docx)

## ********** APPLICATION - HIGH LEVEL FLOW DIAGRAM **********
![image](https://github.com/user-attachments/assets/5fe84dd7-f5f7-45b6-9121-941a9f213893)

## ********** SUMMARY **********
This system is built with a ReactJS front end, providing a dynamic and responsive user interface where users can input data such as start and end dates, currency, and an offline flag to switch between using local or real-time data. Axios is used in the front end to make HTTP requests to the backend, ensuring smooth communication and data exchange.

The backend is powered by Spring Boot, which handles all the core logic of the application. It manages requests from the frontend, processes data, and interacts with various components. For real-time Bitcoin data, the backend integrates with the CoinDesk API using RestTemplate, allowing the system to fetch up-to-date Bitcoin prices. When offline mode is enabled, the backend retrieves Bitcoin price data from a local H2 database, which stores historical data for offline use.

Together, this architecture combines a modern and flexible front-end technology (ReactJS), a powerful and scalable backend (Spring Boot), efficient HTTP communication (Axios), and seamless data integration from an external service (CoinDesk API), with the ability to store data locally for offline use (H2 database). This setup creates a robust and user-friendly solution for tracking Bitcoin prices.

## ********** HOW TO BUILD FRONTEND **********
Please follow steps as mentioned below for building, creating an image and pushing the frontend module to docker hub.

### Install dependencies
npm install

### Build the React application (output in 'build' folder)  
npm run build

### Build the Docker image
docker build -t bitcoinui .

### Tag the image
docker tag bitcoinui:latest sridharthiyagarajan7/historical-bitcoin-ui:latest

### Push the image to Docker Hub
docker push sridharthiyagarajan7/historical-bitcoin-ui:latest

## ********** HOW TO BUILD BACKEND **********
Please follow steps as mentioned below for building, creating an image and pushing the backend module to docker hub.

### Build the Docker image
docker build -t bitcoinservice .
(Note: I have used spring boot plugin way due to JDK version availability limitation in my local setup.)

### Tag the image for Docker Hub
docker tag bitcoinservice:latest sridharthiyagarajan7/historical-bitcoin-service:latest

### Push the image to Docker Hub
docker push sridharthiyagarajan7/historical-bitcoin-service:latest

Note: Alternate way to create backend module image using spring-boot maven plugin:
<img width="1726" alt="image" src="https://github.com/user-attachments/assets/363c1f7a-95e9-424e-9c56-e909558cbf00" />

## ********** DOCKER COMMANDS TO BRING APPLICATION UP AND RUNNING **********
docker run -d -p 3000:3000 --name bitcoinuidocker sridharthiyagarajan7/historical-bitcoin-ui
docker run -d -p 8080:8080 --name bitcoinservicedocker sridharthiyagarajan7/historical-bitcoin-service

## ********** DOCKER IMAGES IN DOCKER HUB **********
<img width="1728" alt="image" src="https://github.com/user-attachments/assets/252e3711-dd45-4374-bba3-f0fd37b86125" />

## ********** SEQUENCE DIAGRAM **********
### USE OFFLINE DATA as TRUE
<img width="578" alt="image" src="https://github.com/user-attachments/assets/5ed7b9c5-f747-4a38-b635-54c13603c556" />

### USE OFFLINE DATA as FALSE
![image](https://github.com/user-attachments/assets/68623633-6dc6-43c1-ab94-aebd9c817774)

## ********** CLASS DIAGRAM **********
### com.casestudy.it.bitcoinservice.rest.controller.BitcoinController:
The BitcoinController class exposes RESTful APIs to fetch Bitcoin price history and details, supporting various input parameters like date range, currency, and offline data flag. It delegates the business logic to the BitcoinService and returns the relevant data or error responses.
![image](https://github.com/user-attachments/assets/dc82d575-bdb4-4b9d-a77f-fdff947fda6b)

### com.casestudy.it.bitcoinservice.service.impl.BitcoinServiceImpl:
The BitcoinServiceImpl class is responsible for retrieving Bitcoin price history and details, either from a local database or an external CoinDesk API, based on the provided parameters. It also validates the currency, calculates high and low prices, and handles the conversion between database entities and API response DTOs.
![image](https://github.com/user-attachments/assets/213b9ecc-1256-4d5a-92f6-fc8ea810ac2a)

### com.casestudy.it.bitcoinservice.client.coindesk.CoinDeskBitcoinClient:
The CoinDeskBitcoinClient class is a service responsible for interacting with the CoinDesk API to fetch historical Bitcoin prices and supported currencies. It has methods for retrieving Bitcoin price data for a given date range and currency, and also populating the supported currencies in the database during application startup.
![image](https://github.com/user-attachments/assets/5421ef68-d230-4ea8-a9b1-0490700786a8)

### com.casestudy.it.bitcoinservice.db.repository.BitcoinRepository:
The BitcoinRepository interface extends JpaRepository and provides a method to retrieve Bitcoin price data from the database within a specified date range and currency, ordered by date. It is used for interacting with the underlying database to fetch Bitcoin price history.
![image](https://github.com/user-attachments/assets/48773fe4-1770-4fec-ab66-6a10fdcb551a)

### com.casestudy.it.bitcoinservice.db.repository.BitcoinCurrencyRepository:
The BitcoinCurrencyRepository interface extends JpaRepository and provides methods to fetch Bitcoin currency data based on currency or a combination of currency and country. It interacts with the database to retrieve and manage Bitcoin currency entities.
![image](https://github.com/user-attachments/assets/f20fb0e0-da30-498b-933d-5692fc63bfcf)

### Limitations:
Jenkins is installed locally and running, but after installing required pipeline and GIT plugins, the pipeline failed to trigger due to admin permission issues with Docker commands in the local machine setup.
![image](https://github.com/user-attachments/assets/f92c44b1-c282-4527-954a-7d64a3071e51)

## ********** TEST CASES CODE COVERAGE **********
![image](https://github.com/user-attachments/assets/1f604525-5116-4a1b-bb8e-550fbc711af7)

## ********** APPLICATION OUTPUT - IMAGE & VIDEO EVIDENCE **********
1. Landing page.
<img width="1728" alt="image" src="https://github.com/user-attachments/assets/a839f3ef-8a10-4d7b-810e-ca23cbffb408" />

2. In UseOfflineMode checkbox as TRUE, inputs are given in above screen.
<img width="1728" alt="image" src="https://github.com/user-attachments/assets/fb86e5d9-f3b8-4c24-962b-3711a74658ae" />

3. Clicking on "Get History" button fetches results from database.
<img width="1728" alt="image" src="https://github.com/user-attachments/assets/5a7396e4-a2ea-4a13-8565-49be273d20d0" />

4. Clicking on a specific bitcoin price gets navigated to detail page.
<img width="1728" alt="image" src="https://github.com/user-attachments/assets/bc13661a-4c5b-40dd-8431-717516866f1c" />

5. Clicking on "Back to List" makes the user land in the previous search results page.
<img width="1728" alt="image" src="https://github.com/user-attachments/assets/7d73097f-27fc-49bb-b644-a62259a0fc28" />

6. Clicking on "Get History" button again UseOfflineMode checkbox as FALSE fetches lots of data from CoinDesk system via API integration.
<img width="1728" alt="image" src="https://github.com/user-attachments/assets/bf58c3d7-c062-44fc-b236-89b8cf4982f2" />

Please find the VIDEO evidence 









