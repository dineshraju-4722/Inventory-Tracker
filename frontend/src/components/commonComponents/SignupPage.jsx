import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { signup } from "../../apiCalls/AuthCalls";
import { signupSuccess, signupError, setLoading } from "../../redux/userRedux";
import { Button, Input, Form, message, Card, Spin } from "antd";
import { UserOutlined, LockOutlined, MailOutlined } from "@ant-design/icons";
import "./AuthPages.css";

export default function SignupPage() {
  const [form] = Form.useForm();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [loading, setLoadingState] = useState(false);

  const handleSignup = async (values) => {
    if (values.password !== values.confirmPassword) {
      message.error("Passwords do not match!");
      return;
    }

    setLoadingState(true);
    dispatch(setLoading(true));
    try {
      await signup(values.username, values.email, values.password);
      dispatch(signupSuccess());
      message.success("Account created successfully! Please sign in.");
      navigate("/signin");
    } catch (error) {
      const errorMsg = error?.message || "Signup failed";
      dispatch(signupError(errorMsg));
      message.error(errorMsg);
    } finally {
      setLoadingState(false);
      dispatch(setLoading(false));
    }
  };

  return (
    <div className="auth-container">
      <Card className="auth-card">
        <h2 className="auth-title">Sign Up</h2>
        <Spin spinning={loading}>
          <Form
            form={form}
            layout="vertical"
            onFinish={handleSignup}
            autoComplete="off"
          >
            <Form.Item
              name="username"
              rules={[
                { required: true, message: "Please enter your username" },
                {
                  min: 3,
                  message: "Username must be at least 3 characters",
                },
              ]}
            >
              <Input
                prefix={<UserOutlined />}
                placeholder="Username"
                size="large"
              />
            </Form.Item>

            <Form.Item
              name="email"
              rules={[
                { required: true, message: "Please enter your email" },
                { type: "email", message: "Please enter a valid email" },
              ]}
            >
              <Input
                prefix={<MailOutlined />}
                placeholder="Email"
                size="large"
                type="email"
              />
            </Form.Item>

            <Form.Item
              name="password"
              rules={[
                { required: true, message: "Please enter your password" },
                {
                  min: 6,
                  message: "Password must be at least 6 characters",
                },
              ]}
            >
              <Input.Password
                prefix={<LockOutlined />}
                placeholder="Password"
                size="large"
              />
            </Form.Item>

            <Form.Item
              name="confirmPassword"
              rules={[
                { required: true, message: "Please confirm your password" },
              ]}
            >
              <Input.Password
                prefix={<LockOutlined />}
                placeholder="Confirm Password"
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
              Sign Up
            </Button>
          </Form>
        </Spin>

        <div className="auth-footer">
          <p>
            Already have an account?{" "}
            <a onClick={() => navigate("/signin")}>Sign In</a>
          </p>
          <p>
            <a onClick={() => navigate("/")}>Back to Home</a>
          </p>
        </div>
      </Card>
    </div>
  );
}
