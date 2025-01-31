# Step 1: Use an official Node.js image as a base
FROM node:16-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy package.json and package-lock.json first
COPY package*.json ./

# Step 4: Install dependencies
RUN npm install

# Step 5: Copy the rest of the application
COPY . .

# Step 6: Build the React app
RUN npm run build

# Step 7: Expose the port your app runs on
EXPOSE 3000

# Step 8: Start the application
CMD ["npm", "start"]
