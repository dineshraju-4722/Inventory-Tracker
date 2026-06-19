import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { logout } from "../../redux/userRedux";
import { signout } from "../../apiCalls/AuthCalls";
import { Layout, Button, Dropdown, Avatar, message } from "antd";
import { LogoutOutlined, UserOutlined } from "@ant-design/icons";
import "./Navbar.css";

const { Header } = Layout;

export default function Navbar() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user.user);
  const isAuthenticated = useSelector((state) => state.user.isAuthenticated);

  const handleLogout = async () => {
    try {
      await signout();
      dispatch(logout());
      message.success("Logged out successfully!");
      navigate("/");
    } catch (error) {
      dispatch(logout());
      message.success("Logged out!");
      navigate("/");
    }
  };

  const menuItems = [
    {
      key: "profile",
      label: "My Profile",
      onClick: () => navigate("/profile"),
    },
    {
      type: "divider",
    },
    {
      key: "logout",
      label: "Logout",
      icon: <LogoutOutlined />,
      onClick: handleLogout,
    },
  ];

  return (
    <Header className="navbar">
      <div className="navbar-container">
        <div
          className="navbar-logo"
          onClick={() => navigate("/home")}
          style={{ cursor: "pointer" }}
        >
          📦 Inventory Tracker
        </div>

        <div className="navbar-right">
          {isAuthenticated && user ? (
            <div className="navbar-user">
              <span className="username">{user.username}</span>
              <Dropdown menu={{ items: menuItems }} trigger={["click"]}>
                <Avatar
                  icon={<UserOutlined />}
                  style={{
                    backgroundColor: "#667eea",
                    cursor: "pointer",
                  }}
                />
              </Dropdown>
            </div>
          ) : (
            <div className="navbar-buttons">
              <Button
                type="text"
                onClick={() => navigate("/signin")}
                style={{ color: "white" }}
              >
                Sign In
              </Button>
              <Button
                type="primary"
                onClick={() => navigate("/signup")}
                style={{ backgroundColor: "#fff", color: "#667eea" }}
              >
                Sign Up
              </Button>
            </div>
          )}
        </div>
      </div>
    </Header>
  );
}
