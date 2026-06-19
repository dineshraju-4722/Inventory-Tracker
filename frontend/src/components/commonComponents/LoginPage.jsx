import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { signin } from "../../apiCalls/AuthCalls";
import { loginSuccess, loginError, setLoading } from "../../redux/userRedux";
import { Button, Input, Form, message, Card, Spin } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
import "./AuthPages.css";

export default function LoginPage() {
  const [form] = Form.useForm();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [loading, setLoadingState] = useState(false);

  const handleLogin = async (values) => {
    setLoadingState(true);
    dispatch(setLoading(true));
    try {
      const result = await signin(values.username, values.password);
      dispatch(loginSuccess(result));
      message.success("Login successful!");
      navigate("/home");
    } catch (error) {
      const errorMsg = error?.message || "Login failed";
      dispatch(loginError(errorMsg));
      message.error(errorMsg);
    } finally {
      setLoadingState(false);
      dispatch(setLoading(false));
    }
  };

  return (
    <div className="auth-container">
      <Card className="auth-card">
        <h2 className="auth-title">Sign In</h2>
        <Spin spinning={loading}>
          <Form
            form={form}
            layout="vertical"
            onFinish={handleLogin}
            autoComplete="off"
          >
            <Form.Item
              name="username"
              rules={[
                { required: true, message: "Please enter your username" },
              ]}
            >
              <Input
                prefix={<UserOutlined />}
                placeholder="Username"
                size="large"
              />
            </Form.Item>

            <Form.Item
              name="password"
              rules={[
                { required: true, message: "Please enter your password" },
              ]}
            >
              <Input.Password
                prefix={<LockOutlined />}
                placeholder="Password"
                size="large"
              />
            </Form.Item>

            <Button
              type="primary"
              htmlType="submit"
              size="large"
              block
              loading={loading}
            >
              Sign In
            </Button>
          </Form>
        </Spin>

        <div className="auth-footer">
          <p>
            Don't have an account?{" "}
            <a onClick={() => navigate("/signup")}>Sign Up</a>
          </p>
          <p>
            <a onClick={() => navigate("/")}>Back to Home</a>
          </p>
        </div>
      </Card>
    </div>
  );
}
