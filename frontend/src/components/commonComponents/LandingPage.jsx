import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import Navbar from "./Navbar";
import { Button, Card, Row, Col } from "antd";
import { ArrowRightOutlined, ShoppingOutlined } from "@ant-design/icons";
import "./LandingPage.css";

export default function LandingPage() {
  const navigate = useNavigate();
  const isAuthenticated = useSelector((state) => state.user.isAuthenticated);

  return (
    <div className="landing-page">
      <Navbar />
      <div className="landing-content">
        <div className="hero-section">
          <h1 className="hero-title">Welcome to Inventory Tracker</h1>
          <p className="hero-subtitle">
            Manage your inventory efficiently and effectively
          </p>
          <div className="hero-buttons">
            {!isAuthenticated ? (
              <>
                <Button
                  type="primary"
                  size="large"
                  onClick={() => navigate("/signin")}
                  icon={<ArrowRightOutlined />}
                >
                  Get Started
                </Button>
                <Button
                  size="large"
                  onClick={() => navigate("/signup")}
                >
                  Create Account
                </Button>
              </>
            ) : (
              <Button
                type="primary"
                size="large"
                onClick={() => navigate("/home")}
                icon={<ArrowRightOutlined />}
              >
                Go to Dashboard
              </Button>
            )}
          </div>
        </div>

        <div className="features-section">
          <h2>Key Features</h2>
          <Row gutter={[24, 24]} className="features-grid">
            <Col xs={24} sm={12} lg={6}>
              <Card className="feature-card">
                <ShoppingOutlined className="feature-icon" />
                <h3>Product Management</h3>
                <p>Easily manage and track all your products</p>
              </Card>
            </Col>
            <Col xs={24} sm={12} lg={6}>
              <Card className="feature-card">
                <ShoppingOutlined className="feature-icon" />
                <h3>Stock Tracking</h3>
                <p>Monitor inventory levels in real-time</p>
              </Card>
            </Col>
            <Col xs={24} sm={12} lg={6}>
              <Card className="feature-card">
                <ShoppingOutlined className="feature-icon" />
                <h3>Supplier Management</h3>
                <p>Manage suppliers and their details</p>
              </Card>
            </Col>
            <Col xs={24} sm={12} lg={6}>
              <Card className="feature-card">
                <ShoppingOutlined className="feature-icon" />
                <h3>Reports</h3>
                <p>Generate detailed inventory reports</p>
              </Card>
            </Col>
          </Row>
        </div>
      </div>
    </div>
  );
}
