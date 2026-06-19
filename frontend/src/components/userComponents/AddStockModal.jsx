import { useState, useEffect } from "react";
import {
  Modal,
  Form,
  Select,
  InputNumber,
  message,
  Spin,
  Divider,
  Card,
  Row,
  Col,
} from "antd";
import { getProducts, getSuppliers } from "../../apiCalls/ProductCalls";
import "./AddStockModal.css";

export default function AddStockModal({
  visible,
  onClose,
  onSubmit,
  loading,
  userId,
}) {
  const [form] = Form.useForm();
  const [products, setProducts] = useState([]);
  const [suppliers, setSuppliers] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [dataLoading, setDataLoading] = useState(false);

  useEffect(() => {
    if (visible) {
      fetchData();
    }
  }, [visible]);

  const fetchData = async () => {
    setDataLoading(true);
    try {
      const [productsData, suppliersData] = await Promise.all([
        getProducts(),
        getSuppliers(),
      ]);
      setProducts(productsData);
      setSuppliers(suppliersData);
    } catch (error) {
      message.error("Failed to load products and suppliers");
    } finally {
      setDataLoading(false);
    }
  };

  const handleProductChange = (value) => {
    const product = products.find((p) => p.productId === value);
    setSelectedProduct(product);
  };

  const handleSubmit = async (values) => {
    if (!selectedProduct) {
      message.error("Please select a product");
      return;
    }
    await onSubmit(values.productId, values.quantity, userId);
    form.resetFields();
    setSelectedProduct(null);
  };

  const getSupplierDetails = () => {
    if (!selectedProduct || !selectedProduct.supplier) return null;
    const supplier = selectedProduct.supplier;
    return supplier;
  };

  const supplierDetails = getSupplierDetails();

  return (
    <Modal
      title="Add Stock"
      open={visible}
      onCancel={() => {
        onClose();
        form.resetFields();
        setSelectedProduct(null);
      }}
      onOk={() => form.submit()}
      confirmLoading={loading}
      width={600}
    >
      <Spin spinning={dataLoading}>
        <Form
          form={form}
          layout="vertical"
          onFinish={handleSubmit}
          autoComplete="off"
        >
          <Form.Item
            name="productId"
            label="Select Product"
            rules={[
              { required: true, message: "Please select a product" },
            ]}
          >
            <Select
              placeholder="Choose a product"
              onChange={handleProductChange}
              optionLabelProp="label"
            >
              {products.map((product) => (
                <Select.Option key={product.productId} value={product.productId}>
                  <div className="product-option">
                    <strong>{product.productName}</strong>
                  </div>
                </Select.Option>
              ))}
            </Select>
          </Form.Item>

          {selectedProduct && (
            <>
              <Divider />
              <div className="supplier-section">
                <h4>Supplier Details</h4>
                {selectedProduct.supplier ? (
                  <Card className="supplier-card">
                    <Row gutter={16}>
                      <Col span={12}>
                        <p>
                          <strong>Company:</strong> {supplierDetails.companyName}
                        </p>
                      </Col>
                      <Col span={12}>
                        <p>
                          <strong>Email:</strong> {supplierDetails.email}
                        </p>
                      </Col>
                      <Col span={12}>
                        <p>
                          <strong>Pin Code:</strong> {supplierDetails.pinCode}
                        </p>
                      </Col>
                    </Row>
                  </Card>
                ) : (
                  <p className="no-supplier">No supplier assigned</p>
                )}
              </div>
              <Divider />
            </>
          )}

          <Form.Item
            name="quantity"
            label="Quantity"
            rules={[
              { required: true, message: "Please enter quantity" },
              {
                type: "number",
                min: 1,
                message: "Quantity must be at least 1",
              },
            ]}
          >
            <InputNumber
              placeholder="Enter quantity"
              style={{ width: "100%" }}
              min={1}
            />
          </Form.Item>
        </Form>
      </Spin>
    </Modal>
  );
}
