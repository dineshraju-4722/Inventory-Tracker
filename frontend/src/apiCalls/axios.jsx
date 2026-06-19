import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:4722",
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // Enable cookies to be sent with every request
});

// Add response interceptor to handle 401 errors
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      console.log("❌ Unauthorized - Cookie may have expired");
    }
    return Promise.reject(error);
  }
);

export default api;
