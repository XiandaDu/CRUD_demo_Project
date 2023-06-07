FROM node:20.1.0-alpine
WORKDIR /app
# Copy package.json and package-lock.json to the working directory
COPY package*.json ./
# Install dependencies
RUN npm install
# Copy all files to the working directory
COPY . .
# Build the Vue.js app
RUN npm install -g http-server
RUN npm install
RUN npm run build
# Serve the app on port 80
EXPOSE 5170
# Start the app with the production server
CMD ["http-server", "-p", "5170", "dist"]


