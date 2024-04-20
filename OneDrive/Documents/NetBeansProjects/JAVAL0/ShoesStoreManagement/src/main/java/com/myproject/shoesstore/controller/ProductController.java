package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Product;
import com.myproject.shoesstore.service.ProductService;
import com.myproject.shoesstore.service.impl.ProductServiceImpl;
import com.myproject.shoesstore.validation.ProductValidation;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

/**
 *
 * @author Tadaboh;
 */
public class ProductController {

    private final JButton btnSave;
    private final JButton btnAddCode;
    private final JButton btnChooseImage;
    private final JTextField jtfProductCode;
    private final JTextField jtfProductName;
    private final JLabel jlbImage;

    private final JTextField jtfPrice;
    private final JTextField jtfQuantity;
    private final JComboBox<String> jcbSupplierCode;
    private final JTextArea jtaDescription;
    private final JLabel jlbMsg;
    Product product = null;
    ProductService productService = null;

    public ProductController(JButton btnSave, JButton btnAddCode, JButton btnChooseImage, JTextField jtfProductCode, JTextField jtfProductName, JLabel jlbImage, JTextField jtfPrice, JTextField jtfQuantity, JComboBox<String> jcbSupplierCode, JTextArea jtaDescription, JLabel jlbMsg) {
        this.btnSave = btnSave;
        this.btnAddCode = btnAddCode;
        this.btnChooseImage = btnChooseImage;
        this.jtfProductCode = jtfProductCode;
        this.jtfProductName = jtfProductName;
        this.jlbImage = jlbImage;
        this.jtfPrice = jtfPrice;
        this.jtfQuantity = jtfQuantity;
        this.jcbSupplierCode = jcbSupplierCode;
        this.jtaDescription = jtaDescription;
        this.jlbMsg = jlbMsg;

        this.productService = new ProductServiceImpl();
    }

    public void setView(Product product) {
        this.product = product;

        jtfProductCode.setText(product.getProductCode());
        jtfProductName.setText(product.getProductName());

        if (product.getImage() != null) {
            ImageIcon image = new ImageIcon(product.getImage());
            ImageIcon newIcon = setSizeImage(image, jlbImage);
            jlbImage.setIcon(newIcon);
        }

        jtfPrice.setText(String.valueOf(product.getPrice()));
        jtfQuantity.setText(String.valueOf(product.getQuantity()));

        List<String> listSupplierCode = productService.getListSupplierCode();

        String[] array = listSupplierCode.toArray(new String[0]);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(array);
        jcbSupplierCode.setModel(model);
        jcbSupplierCode.setSelectedItem(product.getSupplierCode());
        jtaDescription.setText(product.getDescript());
    }

    public void setEvent() {
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                product.setProductCode(jtfProductCode.getText());
                product.setProductName(jtfProductName.getText());
                ImageIcon icon = (ImageIcon) jlbImage.getIcon();
                byte[] imageData = convertImageIconToByteArray(icon);
                product.setImage(imageData);
                product.setPrice(Double.valueOf(jtfPrice.getText()));
                product.setQuantity(Integer.valueOf(jtfQuantity.getText()));

                int selectedIndex = jcbSupplierCode.getSelectedIndex();
                String selectedSupplierCode = jcbSupplierCode.getItemAt(selectedIndex);

                product.setSupplierCode(selectedSupplierCode);
                product.setDescript(jtaDescription.getText());
                if (ProductValidation.validProduct(product)) {

                    if (showDialog()) {
                        if (ProductValidation.validCreateProductCode(jtfProductCode.getText(), productService)) {
                            int rows = productService.createOrUpdate(product);
                            System.out.println(rows);
                            if (rows > 0) {

                                jlbMsg.setText("Xử lý dữ liệu sản phẩm thành công ");
                                jlbMsg.setForeground(Color.green);
                            } else {
                                jlbMsg.setText("Xử lý dữ liệu sản phẩm thất bại");
                                jlbMsg.setForeground(Color.red);
                            }
                       } else {
                            JOptionPane.showMessageDialog(null, "Mã đã tồn tại !");
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ dữ liệu hoặc xem lại dữ liệu đã nhập vào!");
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSave.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSave.setBackground(new Color(100, 221, 23));

            }

        });

        btnAddCode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String code = setProductCode();
                jtfProductCode.setText(code);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAddCode.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAddCode.setBackground(new Color(100, 221, 23));

            }
        });

        btnChooseImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));

                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    jlbImage.setIcon(resizeImage(path, null));
                } else {
                    System.out.println("No file selected");
                }
            }

        });

    }

    public String setProductCode() {
        StringBuilder productCode = new StringBuilder();
        Random random = new Random();

        productCode.append("SPSS");
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            productCode.append(randomNumber);
        }
        return productCode.toString();
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private boolean checkNotNull() {
        return jtfProductName.getText() != null && !jtfProductName.getText().equalsIgnoreCase("");
    }

    public ImageIcon resizeImage(String imgPath, byte[] image) {
        ImageIcon myImage = null;
        if (imgPath != null) {
            myImage = new ImageIcon(imgPath);
        } else {
            myImage = new ImageIcon(image);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(jlbImage.getWidth(), jlbImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(img2);
        return imageIcon;
    }

    private byte[] convertImageIconToByteArray(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return baos.toByteArray();
    }

    public ImageIcon setSizeImage(ImageIcon imageIcon, JLabel jlbImg) {
        jlbImg.setHorizontalAlignment(JLabel.CENTER);
        jlbImg.setVerticalAlignment(JLabel.CENTER);

        ImageIcon icon = imageIcon;

        Image image = icon.getImage();

        Image newImage = image.getScaledInstance(240, 210, Image.SCALE_SMOOTH);

        ImageIcon newIcon = new ImageIcon(newImage);

        return newIcon;
    }
}
