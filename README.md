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
This class contains REST API method implementations.
![image](https://github.com/user-attachments/assets/dc82d575-bdb4-4b9d-a77f-fdff947fda6b)


