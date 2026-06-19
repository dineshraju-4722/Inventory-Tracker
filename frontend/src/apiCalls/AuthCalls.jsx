import api from "./axios";

export const signin = async (username, password) => {
  try {
    const response = await api.post("/api/auth/signin", {
      username,
      password,
    });
    console.log("✅ Login successful. Using cookie-based auth.");
    return response.data;
  } catch (error) {
    console.error("❌ Login error:", error);
    throw error.response?.data || error.message;
  }
};

export const signup = async (username, email, password) => {
  try {
    const response = await api.post("/api/auth/signup", {
      username,
      email,
      password,
      role: ["user"],
    });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const getUsername = async () => {
  try {
    const response = await api.get("/api/auth/username");
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const getUser = async () => {
  try {
    const response = await api.get("/api/auth/user");
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const signout = async () => {
  try {
    const response = await api.post("/api/auth/signout");
    console.log("✅ Logout successful. Cookie cleared.");
    return response.data;
  } catch (error) {
    console.log("⚠️ Logout called (cookie will be cleared by backend)");
    throw error.response?.data || error.message;
  }
};
