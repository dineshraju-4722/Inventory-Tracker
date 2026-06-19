import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import Navbar from "../commonComponents/Navbar";
import AddStockModal from "./AddStockModal";
import {
  getUserCart,
  getImportedHistory,
  addToImportedHistory,
} from "../../apiCalls/ProductCalls";
import { Card, Row, Col, Button, Table, message, Empty, Spin } from "antd";
import {
  PlusOutlined,
  DeleteOutlined,
  HistoryOutlined,
  InboxOutlined,
} from "@ant-design/icons";
import moment from "moment";
import "./UserHomePage.css";

export default function UserHomePage() {
  const navigate = useNavigate();
  const isAuthenticated = useSelector((state) => state.user.isAuthenticated);
  const user = useSelector((state) => state.user.user);

  const [userCart, setUserCart] = useState([]);
  const [importedHistory, setImportedHistory] = useState([]);
  const [showAddStock, setShowAddStock] = useState(false);
  const [loading, setLoading] = useState(false);
  const [historyLoading, setHistoryLoading] = useState(false);

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/signin");
    } else {
      fetchUserCart();
      fetchImportedHistory();
    }
  }, [isAuthenticated, navigate]);

  const fetchUserCart = async () => {
    setLoading(true);
    try {
      const data = await getUserCart();
      setUserCart(data || []);
    } catch (error) {
      const errorMsg = error?.message || "Failed to load products";
      console.error("Error fetching user cart:", error);
      if (error?.status === 401 || errorMsg.includes("401")) {
        message.error("Authentication failed. Please login again.");
      } else {
        message.error(errorMsg);
      }
    } finally {
      setLoading(false);
    }
  };

  const fetchImportedHistory = async () => {
    setHistoryLoading(true);
    try {
      const data = await getImportedHistory();
      setImportedHistory(data || []);
    } catch (error) {
      const errorMsg = error?.message || "Failed to load history";
      console.error("Error fetching imported history:", error);
      if (error?.status === 401 || errorMsg.includes("401")) {
        message.error("Authentication failed. Please login again.");
      } else {
        message.error(errorMsg);
      }
    } finally {
      setHistoryLoading(false);
    }
  };

  const handleAddStock = async (productId, quantity, userId) => {
    setLoading(true);
    try {
      // Add to user cart or update if exists
      

      // Add to imported history
      await addToImportedHistory(productId, quantity, user.id);

      message.success("Stock added successfully!");
      setShowAddStock(false);
      fetchUserCart();
      fetchImportedHistory();
    } catch (error) {
      const errorMsg = error?.message || "Failed to add stock";
      message.error(errorMsg);
    } finally {
      setLoading(false);
    }
  };

  const productColumns = [
    {
      title: "Product Name",
      dataIndex: ["product", "productName"],
      key: "productName",
      render: (text) => <strong>{text}</strong>,
    },
    {
      title: "Supplier",
      dataIndex: ["product", "supplier", "companyName"],
      key: "supplier",
      render: (text) => text || "N/A",
    },
    {
      title: "Stock Quantity",
      dataIndex: "quantity",
      key: "quantity",
      align: "center",
      render: (quantity) => (
        <span className="quantity-badge">{quantity}</span>
      ),
    },
    {
      title: "Action",
      key: "action",
      align: "center",
      render: () => (
        <Button
          type="primary"
          icon={<PlusOutlined />}
          size="small"
          onClick={() => setShowAddStock(true)}
        >
          Add
        </Button>
      ),
    },
  ];

  const historyColumns = [
    {
      title: "Product Name",
      dataIndex: ["product", "productName"],
      key: "productName",
      render: (text) => <strong>{text}</strong>,
    },
    {
      title: "Quantity Added",
      dataIndex: "quantity",
      key: "quantity",
      align: "center",
    },
    {
      title: "Date",
      dataIndex: "date",
      key: "date",
      render: (date) => moment(date).format("DD/MM/YYYY"),
    },
    {
      title: "Time",
      dataIndex: "time",
      key: "time",
    },
  ];

  if (!isAuthenticated) {
    return null;
  }

  return (
    <div className="user-home-page">
      <Navbar />
      <div className="home-content">
        <div className="welcome-section">
          <h1>Welcome, {user?.username}! 👋</h1>
          <p>Manage your inventory stock efficiently</p>
        </div>

        {/* Products with Stock Section */}
        <Card className="section-card">
          <div className="section-header">
            <h2>
              <InboxOutlined /> Current Stock
            </h2>
            <Button
              type="primary"
              icon={<PlusOutlined />}
              onClick={() => setShowAddStock(true)}
            >
              Add Stock
            </Button>
          </div>

          <Spin spinning={loading}>
            {userCart.length > 0 ? (
              <Table
                columns={productColumns}
                dataSource={userCart}
                rowKey={(record) => record.userCartId}
                pagination={{ pageSize: 10 }}
                className="products-table"
              />
            ) : (
              <Empty
                image={Empty.PRESENTED_IMAGE_SIMPLE}
                description="No products in stock"
                style={{ padding: "40px" }}
              >
                <Button
                  type="primary"
                  onClick={() => setShowAddStock(true)}
                  icon={<PlusOutlined />}
                >
                  Add First Product
                </Button>
              </Empty>
            )}
          </Spin>
        </Card>

        {/* Imported History Section */}
        <Card className="section-card history-card">
          <div className="section-header">
            <h2>
              <HistoryOutlined /> Import History
            </h2>
            <span className="history-count">{importedHistory.length} entries</span>
          </div>

          <Spin spinning={historyLoading}>
            {importedHistory.length > 0 ? (
              <Table
                columns={historyColumns}
                dataSource={importedHistory}
                rowKey={(record) => `${record.productId}-${record.date}-${record.time}`}
                pagination={{ pageSize: 10 }}
                className="history-table"
              />
            ) : (
              <Empty
                image={Empty.PRESENTED_IMAGE_SIMPLE}
                description="No import history"
                style={{ padding: "40px" }}
              />
            )}
          </Spin>
        </Card>
      </div>

      {/* Add Stock Modal */}
      <AddStockModal
        visible={showAddStock}
        onClose={() => setShowAddStock(false)}
        onSubmit={handleAddStock}
        loading={loading}
        userId={user?.id}
      />
    </div>
  );
}
