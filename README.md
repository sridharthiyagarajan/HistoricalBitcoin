# Application High Level Flow Diagram
![image](https://github.com/user-attachments/assets/5fe84dd7-f5f7-45b6-9121-941a9f213893)

# ********** HOW TO BUILD FRONTEND **********
Please follow steps as mentioned below for building, creating an image and pushing the frontend module to docker hub.

# Install dependencies
npm install

# Build the React application (output in 'build' folder)  
npm run build

# Build the Docker image
docker build -t bitcoinui .

# Tag the image
docker tag bitcoinui:latest sridharthiyagarajan7/historical-bitcoin-ui:latest

# Push the image to Docker Hub
docker push sridharthiyagarajan7/historical-bitcoin-ui:latest

# ********** HOW TO BUILD BACKEND **********
Please follow steps as mentioned below for building, creating an image and pushing the backend module to docker hub.

# Build the Docker image
docker build -t bitcoin-backend .

# Tag the image for Docker Hub
docker tag bitcoin-backend:latest sridharthiyagarajan7/historical-bitcoin-backend:latest

# Push the image to Docker Hub
docker push sridharthiyagarajan7/historical-bitcoin-backend:latest

Alternate way to create backend module image using spring-boot maven plugin:
<img width="1726" alt="image" src="https://github.com/user-attachments/assets/363c1f7a-95e9-424e-9c56-e909558cbf00" />

# ********** SEQUENCE DIAGRAM **********
# USE OFFLINE DATA as TRUE
<img width="578" alt="image" src="https://github.com/user-attachments/assets/5ed7b9c5-f747-4a38-b635-54c13603c556" />

# USE OFFLINE DATA as FALSE
![image](https://github.com/user-attachments/assets/68623633-6dc6-43c1-ab94-aebd9c817774)

# ********** CLASS DIAGRAM **********
# com.casestudy.it.bitcoinservice.rest.controller.BitcoinController:
The BitcoinController class exposes RESTful APIs to fetch Bitcoin price history and details, supporting various input parameters like date range, currency, and offline data flag. It delegates the business logic to the BitcoinService and returns the relevant data or error responses.
![image](https://github.com/user-attachments/assets/dc82d575-bdb4-4b9d-a77f-fdff947fda6b)

# com.casestudy.it.bitcoinservice.service.impl.BitcoinServiceImpl:
The BitcoinServiceImpl class is responsible for retrieving Bitcoin price history and details, either from a local database or an external CoinDesk API, based on the provided parameters. It also validates the currency, calculates high and low prices, and handles the conversion between database entities and API response DTOs.
![image](https://github.com/user-attachments/assets/213b9ecc-1256-4d5a-92f6-fc8ea810ac2a)

# com.casestudy.it.bitcoinservice.client.coindesk.CoinDeskBitcoinClient:
The CoinDeskBitcoinClient class is a service responsible for interacting with the CoinDesk API to fetch historical Bitcoin prices and supported currencies. It has methods for retrieving Bitcoin price data for a given date range and currency, and also populating the supported currencies in the database during application startup.
![image](https://github.com/user-attachments/assets/5421ef68-d230-4ea8-a9b1-0490700786a8)

# com.casestudy.it.bitcoinservice.db.repository.BitcoinRepository:
The BitcoinRepository interface extends JpaRepository and provides a method to retrieve Bitcoin price data from the database within a specified date range and currency, ordered by date. It is used for interacting with the underlying database to fetch Bitcoin price history.
![image](https://github.com/user-attachments/assets/48773fe4-1770-4fec-ab66-6a10fdcb551a)

# com.casestudy.it.bitcoinservice.db.repository.BitcoinCurrencyRepository:
The BitcoinCurrencyRepository interface extends JpaRepository and provides methods to fetch Bitcoin currency data based on currency or a combination of currency and country. It interacts with the database to retrieve and manage Bitcoin currency entities.
![image](https://github.com/user-attachments/assets/f20fb0e0-da30-498b-933d-5692fc63bfcf)

Limitations:
Jenkins is installed locally and running, but after installing required pipeline and GIT plugins, the pipeline failed to trigger due to admin permission issues with Docker commands in the local machine setup.
![image](https://github.com/user-attachments/assets/f92c44b1-c282-4527-954a-7d64a3071e51)






