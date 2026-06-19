import api from "./axios";

// Products
export const getProducts = async () => {
  try {
    const response = await api.get("/api/products");
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

// Suppliers
export const getSuppliers = async () => {
  try {
    const response = await api.get("/api/suppliers");
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

// User Cart
export const getUserCart = async () => {
  try {
    const response = await api.get("/api/user/usercart");
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};



// Imported History
export const getImportedHistory = async () => {
  try {
    const response = await api.get("/api/importedhistories");
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const addToImportedHistory = async (productId, quantity, userId) => {
  try {
    const response = await api.post("/api/importedhistory", {
      productId,
      quantity,
      userId,
    });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

// Increment quantity for existing product
export const incrementQuantity = async (productId, quantity = 1) => {
  try {
    const response = await api.put("/api/user/usercart", {
      productId,
      quantity,
    });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

// Decrement quantity for existing product
export const decrementQuantity = async (userCartId, productId, currentQuantity) => {
  try {
    if (currentQuantity <= 1) {
      throw new Error("Quantity cannot be less than 1");
    }
    const newQuantity = currentQuantity - 1;
    const response = await api.put("/api/user/usercart", {
      productId,
      quantity: -1, // Send negative to decrement
    });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};
