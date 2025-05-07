import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.net.URL;
import java.io.IOException;
import java.awt.image.BufferedImage;

class FoodItem {
    String name;
    int price;
    int quantity;

    public FoodItem(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

public class FoodOrderingSystem extends JFrame {
    private JTextField nameField, phoneField;
    private JTextArea orderSummary;
    private ArrayList<FoodItem> cart = new ArrayList<>();
    private double total = 0.0;
    private int userRating = 0;

    public FoodOrderingSystem() {
        setTitle("CraveEase - Food Ordering System");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 245, 245));

        add(createHeader(), BorderLayout.NORTH);

        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.add(createUserDetailsPanel(), BorderLayout.NORTH);
        mainContent.add(createMenuPanel(), BorderLayout.CENTER);

        add(mainContent, BorderLayout.CENTER);
        add(createOrderPanel(), BorderLayout.EAST);
        add(createFooter(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color gradientStart = new Color(255, 165, 0);
                Color gradientEnd = new Color(255, 140, 0);
                GradientPaint gradientPaint = new GradientPaint(0, 0, gradientStart, getWidth(), getHeight(), gradientEnd);
                g2d.setPaint(gradientPaint);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(getWidth(), 100));
    
        // Create a container for the logo and title
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        contentPanel.setOpaque(false); // Make it transparent to show the gradient background
    
        // Add logo
        // Add logo
try {
    URL logoUrl = new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYr-r-BGg0B3X2nXtKkHg-ytlHUnBMqPoPlQ&s");
    BufferedImage originalImage = ImageIO.read(logoUrl);
    
    // Determine the maximum dimensions for the logo
    int maxWidth = 80;
    int maxHeight = 80;
    
    // Calculate aspect ratio to maintain proportions
    int originalWidth = originalImage.getWidth();
    int originalHeight = originalImage.getHeight();
    double aspectRatio = (double) originalWidth / originalHeight;

    int scaledWidth = maxWidth;
    int scaledHeight = (int) (maxWidth / aspectRatio);

    if (scaledHeight > maxHeight) {
        scaledHeight = maxHeight;
        scaledWidth = (int) (maxHeight * aspectRatio);
    }

    // Scale the image
    Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
    JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
    contentPanel.add(logoLabel);
} catch (IOException e) {
    System.out.println("Error loading logo: " + e.getMessage());
}
    
        // Title label
        JLabel titleLabel = new JLabel("DineDash - Food Ordering System");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        contentPanel.add(titleLabel);
    
        headerPanel.add(contentPanel, BorderLayout.CENTER);
    
        return headerPanel;
    }

    private JPanel createUserDetailsPanel() {
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        userPanel.setBackground(Color.WHITE);
        userPanel.setBorder(BorderFactory.createTitledBorder("Customer Details"));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(15);

        userPanel.add(nameLabel);
        userPanel.add(nameField);
        userPanel.add(Box.createHorizontalStrut(20));
        userPanel.add(phoneLabel);
        userPanel.add(phoneField);

        return userPanel;
    }

    private JComponent createMenuPanel() {
        JTabbedPane menuTabs = new JTabbedPane();
        menuTabs.setFont(new Font("Arial", Font.BOLD, 14));

        menuTabs.addTab("Food", new JScrollPane(createCategoryPanel(
                new String[]{"Burger", "Pizza", "Pasta", "Fries", "Sandwich", "Tacos"},
                new int[]{150, 250, 180, 100, 120, 150},
                new String[]{
                        "https://images.unsplash.com/photo-1550547660-d9450f859349",
                        "https://images.unsplash.com/photo-1613564834361-9436948817d1",
                        "https://images.unsplash.com/photo-1551183053-bf91a1d81141",
                        "https://plus.unsplash.com/premium_photo-1672774750509-bc9ff226f3e8",
                        "https://images.unsplash.com/photo-1592415486689-125cbbfcbee2",
                        "https://plus.unsplash.com/premium_photo-1661730329741-b3bf77019b39"
                }
        )));

        menuTabs.addTab("Drinks", new JScrollPane(createCategoryPanel(
                new String[]{"Soda", "Coffee", "Coke", "Juice", "Tea", "Milkshake"},
                new int[]{50, 90, 50, 80, 60, 100},
                new String[]{
                        "https://images.unsplash.com/photo-1603968070333-58761fa00853",
                        "https://plus.unsplash.com/premium_photo-1674327105074-46dd8319164b",
                        "https://plus.unsplash.com/premium_photo-1725075086083-89117890371d",
                        "https://images.unsplash.com/photo-1600271886742-f049cd451bba",
                        "https://images.unsplash.com/photo-1597481499666-130f8eb2c9cd",
                        "https://images.unsplash.com/photo-1619158401201-8fa932695178"
                }
        )));

        menuTabs.addTab("Desserts", new JScrollPane(createCategoryPanel(
                new String[]{"Ice Cream", "Cake", "Brownies"},
                new int[]{80, 120, 90},
                new String[]{
                        "https://plus.unsplash.com/premium_photo-1678198786424-c2cc6593f59c",
                        "https://images.unsplash.com/photo-1599785209707-a456fc1337bb",
                        "https://images.unsplash.com/photo-1636743715220-d8f8dd900b87"
                }
        )));

        return menuTabs;
    }

    private JPanel createCategoryPanel(String[] items, int[] prices, String[] imageUrls) {
        JPanel panel = new JPanel(new GridLayout(0, 3, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(245, 245, 245));

        for (int i = 0; i < items.length; i++) {
            panel.add(createMenuCard(items[i], prices[i], imageUrls[i]));
        }
        return panel;
    }

    private JPanel createMenuCard(String itemName, int price, String imageUrl) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        card.setPreferredSize(new Dimension(280, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        // Image Panel
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(250, 250));
        try {
            URL url = new URL(imageUrl + "?w=250&h=250&fit=crop");
            BufferedImage originalImage = ImageIO.read(url);
            Image scaledImage = originalImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaledImage));
            imagePanel.add(imgLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            JLabel placeholder = new JLabel("📷 Image not available", JLabel.CENTER);
            placeholder.setForeground(Color.GRAY);
            imagePanel.add(placeholder);
        }
        card.add(imagePanel, gbc);

        // Item Details
        gbc.gridy++;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(itemName, JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("₹" + price, JLabel.CENTER);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        priceLabel.setForeground(new Color(0, 100, 0));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(nameLabel);
        detailsPanel.add(priceLabel);
        detailsPanel.add(Box.createVerticalStrut(10));

        card.add(detailsPanel, gbc);

        // Quantity Controls
        gbc.gridy++;
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        controlPanel.setBackground(Color.WHITE);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinner.setPreferredSize(new Dimension(60, 30));

        JButton addBtn = new JButton("Add to Cart");
        addBtn.setBackground(new Color(50, 205, 50));
        addBtn.setForeground(Color.WHITE);
        addBtn.setPreferredSize(new Dimension(120, 35));

        addBtn.addActionListener(e -> {
            int quantity = (Integer) spinner.getValue();
            addToCart(new FoodItem(itemName, price, quantity));
        });
        controlPanel.add(new JLabel("Qty:"));
        controlPanel.add(spinner);
        controlPanel.add(addBtn);

        card.add(controlPanel, gbc);

        return card;
    }

    private JPanel createOrderPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout(10, 10));
        orderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        orderPanel.setPreferredSize(new Dimension(350, 0));

        orderSummary = new JTextArea();
        orderSummary.setEditable(false);
        orderSummary.setFont(new Font("Monospaced", Font.PLAIN, 14));
        orderSummary.setBackground(new Color(240, 255, 240));

        JButton placeOrderBtn = new JButton("Place Order");
        placeOrderBtn.setBackground(new Color(255, 69, 0));
        placeOrderBtn.setForeground(Color.WHITE);
        placeOrderBtn.addActionListener(e -> showPaymentOptions());

        orderPanel.add(new JScrollPane(orderSummary), BorderLayout.CENTER);
        orderPanel.add(placeOrderBtn, BorderLayout.SOUTH);
        return orderPanel;
    }

    private void addToCart(FoodItem item) {
        cart.add(item);
        total += item.price * item.quantity;
        updateOrderSummary();
    }

    private void updateOrderSummary() {
        orderSummary.setText("");
        double currentTotal = 0.0;
        for (FoodItem item : cart) {
            double itemTotal = item.price * item.quantity;
            orderSummary.append(item.quantity + " x " + item.name + " - ₹" + String.format("%.2f", itemTotal) + "\n");
            currentTotal += itemTotal;
        }
        total = currentTotal;
        orderSummary.append("\nTotal: ₹" + String.format("%.2f", total) + "\n");
    }

    private void showPaymentOptions() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!", "Order Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name and phone number!", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JDialog paymentDialog = new JDialog(this, "Payment Options", true);
        paymentDialog.setSize(400, 300);
        paymentDialog.setLayout(new BorderLayout(10, 10));

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton cashButton = new JButton("Pay in Cash");
        cashButton.setPreferredSize(new Dimension(250, 60));
        cashButton.setMaximumSize(new Dimension(250, 60));
        cashButton.setBackground(new Color(100, 149, 237));
        cashButton.setForeground(Color.WHITE);
        cashButton.setFont(new Font("Arial", Font.BOLD, 14));
        cashButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cashButton.addActionListener(e -> {
            paymentDialog.dispose();
            confirmOrder(name, phone, "Pay On Counter");
        });

        JButton qrButton = new JButton("Pay Online");
        qrButton.setPreferredSize(new Dimension(250, 60));
        qrButton.setMaximumSize(new Dimension(250, 60));
        qrButton.setBackground(new Color(50, 205, 50));
        qrButton.setForeground(Color.WHITE);
        qrButton.setFont(new Font("Arial", Font.BOLD, 14));
        qrButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        qrButton.addActionListener(e -> {
            paymentDialog.dispose();
            showQRCodePayment(name, phone);
        });

        optionsPanel.add(Box.createVerticalGlue());
        optionsPanel.add(cashButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        optionsPanel.add(qrButton);
        optionsPanel.add(Box.createVerticalGlue());

        JLabel titleLabel = new JLabel("Select Payment Method", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        paymentDialog.add(titleLabel, BorderLayout.NORTH);
        paymentDialog.add(optionsPanel, BorderLayout.CENTER);
        paymentDialog.setLocationRelativeTo(this);
        paymentDialog.setVisible(true);
    }

    private void showQRCodePayment(String name, String phone) {
        JDialog qrDialog = new JDialog(this, "QR Code Payment", true);
        qrDialog.setSize(500, 600);
        qrDialog.setLayout(new BorderLayout(10, 10));

        try {
            URL qrUrl = new URL("https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=FoodieHubPayment:" + total);
            BufferedImage qrImage = ImageIO.read(qrUrl);
            JLabel qrLabel = new JLabel(new ImageIcon(qrImage));
            qrLabel.setHorizontalAlignment(JLabel.CENTER);
            qrDialog.add(qrLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            JLabel errorLabel = new JLabel("Could not load QR code", JLabel.CENTER);
            errorLabel.setForeground(Color.RED);
            qrDialog.add(errorLabel, BorderLayout.CENTER);
        }

        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        infoPanel.add(new JLabel("Scan this QR code to pay ₹" + String.format("%.2f", total), JLabel.CENTER));
        infoPanel.add(new JLabel("Supported apps: PayTM, PhonePe, Google Pay", JLabel.CENTER));
        infoPanel.add(new JLabel("Order will be processed after payment confirmation", JLabel.CENTER));

        JButton confirmButton = new JButton("Payment Done");
        confirmButton.setBackground(new Color(50, 205, 50));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(e -> {
            qrDialog.dispose();
            confirmOrder(name, phone, "Online");
        });

        JButton backButton = new JButton("Back to Payment Options");
        backButton.addActionListener(e -> {
            qrDialog.dispose();
            showPaymentOptions();
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);

        qrDialog.add(infoPanel, BorderLayout.NORTH);
        qrDialog.add(buttonPanel, BorderLayout.SOUTH);
        qrDialog.setLocationRelativeTo(this);
        qrDialog.setVisible(true);
    }

    private void confirmOrder(String name, String phone, String paymentMethod) {
        JDialog confirmDialog = new JDialog(this, "Order Confirmation", true);
        confirmDialog.setSize(500, 400);
        confirmDialog.setLayout(new BorderLayout(10, 10));

        try {
            URL url = new URL("https://img.freepik.com/free-vector/order-confirmed-concept-illustration_114360-1486.jpg");
            BufferedImage img = ImageIO.read(url);
            Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaledImg));
            imgLabel.setHorizontalAlignment(JLabel.CENTER);
            confirmDialog.add(imgLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            JLabel errorLabel = new JLabel("Order Confirmed!", JLabel.CENTER);
            errorLabel.setFont(new Font("Arial", Font.BOLD, 16));
            confirmDialog.add(errorLabel, BorderLayout.CENTER);
        }

        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        infoPanel.add(new JLabel("Thank you for your order!", JLabel.CENTER));
        infoPanel.add(new JLabel("Name: " + name, JLabel.CENTER));
        infoPanel.add(new JLabel("Phone: " + phone, JLabel.CENTER));
        infoPanel.add(new JLabel("Payment Method: " + paymentMethod, JLabel.CENTER));

        JButton closeButton = new JButton("Close");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);

        closeButton.addActionListener(e -> {
            confirmDialog.dispose();
            showStarRatingDialog(); // Show Star Rating Dialog after closing confirmation dialog
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(closeButton);

        confirmDialog.add(infoPanel, BorderLayout.NORTH);
        confirmDialog.add(buttonPanel, BorderLayout.SOUTH);

        confirmDialog.setLocationRelativeTo(this);
        confirmDialog.setVisible(true);
    }
	
     


  
  
private void showStarRatingDialog() {
        JDialog ratingDialog = new JDialog(this, "Rate Our Service", true);
        ratingDialog.setSize(500, 200);
        ratingDialog.setLayout(new BorderLayout(10, 10));

        // Instruction Label
        JLabel instructionLabel = new JLabel("How would you rate our service?");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);
        ratingDialog.add(instructionLabel, BorderLayout.NORTH);

        // Star Rating Panel
        JPanel starPanel = new JPanel(new FlowLayout());
        starPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create star labels for rating
        JLabel[] starLabels = new JLabel[5];
        try {
            // Star image URL - replace with your actual URL
            URL starUrl = new URL("https://t3.ftcdn.net/jpg/01/09/84/42/240_F_109844239_A7MdQSDf4y1H80cfvHZuSa0zKBkZ68S7.jpg");
            
            for (int i = 0; i < starLabels.length; i++) {
                // Load the star image
                BufferedImage originalImage = ImageIO.read(starUrl);
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon starIcon = new ImageIcon(scaledImage);
                
                // Create label with star icon
                starLabels[i] = new JLabel(starIcon);
                starLabels[i].setPreferredSize(new Dimension(50, 50));
                
                int rating = i + 1; // Star number (1 to 5)
                final JLabel currentLabel = starLabels[i];
                
                currentLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Highlight selected stars
                        try {
                            for (int j = 0; j < starLabels.length; j++) {
                                BufferedImage img = ImageIO.read(starUrl);
                                if (j < rating) {
                                    // Darken the star to show selection
                                    Graphics2D g2d = img.createGraphics();
                                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                                    g2d.setColor(new Color(218, 165, 32)); // Gold color
                                    g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
                                    g2d.dispose();
                                }
                                
                                Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                                starLabels[j].setIcon(new ImageIcon(scaledImg));
                            }
                            userRating = rating;

                            // Show feedback dialog after rating
                            ratingDialog.dispose();
                            showFeedbackDialog();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(ratingDialog, "Error loading star image", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // Hover effect with medium dark yellow
                        try {
                            BufferedImage img = ImageIO.read(starUrl);
                            Graphics2D g2d = img.createGraphics();
                            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                            g2d.setColor(new Color(218, 165, 32)); // Golden rod color
                            g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
                            g2d.dispose();
                            
                            Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            currentLabel.setIcon(new ImageIcon(scaledImg));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(ratingDialog, "Error loading star image", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Reset star if not selected
                        try {
                            BufferedImage img = ImageIO.read(starUrl);
                            Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            currentLabel.setIcon(new ImageIcon(scaledImg));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(ratingDialog, "Error loading star image", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                
                starPanel.add(currentLabel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Fallback to text stars if image loading fails
            JOptionPane.showMessageDialog(ratingDialog, "Could not load star images", "Error", JOptionPane.ERROR_MESSAGE);
        }

        ratingDialog.add(starPanel, BorderLayout.CENTER);

        ratingDialog.setLocationRelativeTo(this);
        ratingDialog.setVisible(true);
    }
                          

private void resetInputs() {
    // Clear customer details
    nameField.setText("");
    phoneField.setText("");

    // Clear cart and total
    cart.clear();
    total = 0.0;

    // Reset order summary
    orderSummary.setText("");

    // Reset user rating
    userRating = 0;

    // Optionally: Show a message indicating inputs have been reset
    System.out.println("All inputs have been reset.");
}


 


private void showFeedbackDialog() {
    JDialog feedbackDialog = new JDialog(this, "Feedback", true);
    feedbackDialog.setSize(400, 200);
    feedbackDialog.setLayout(new BorderLayout(10, 10));

    JLabel instructionLabel = new JLabel("Any additional feedback?");
    instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
    instructionLabel.setHorizontalAlignment(JLabel.CENTER);

    JTextArea feedbackArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(feedbackArea);

    JButton submitButton = new JButton("Submit Feedback");
    submitButton.addActionListener(e -> {
        String feedback = feedbackArea.getText();
        JOptionPane.showMessageDialog(feedbackDialog, "Thank you for your feedback!");
        feedbackDialog.dispose();

        // Reset all inputs after submitting feedback
        resetInputs();
    });

    feedbackDialog.add(instructionLabel, BorderLayout.NORTH);
    feedbackDialog.add(scrollPane, BorderLayout.CENTER);
    feedbackDialog.add(submitButton, BorderLayout.SOUTH);

    feedbackDialog.setLocationRelativeTo(this);
    feedbackDialog.setVisible(true);
}


    private JPanel createFooter() {
        JPanel footerPanel = new JPanel();
        footerPanel.setPreferredSize(new Dimension(getWidth(), 50));
        footerPanel.setBackground(new Color(51, 51, 51));

        JLabel copyrightLabel = new JLabel("© 2024 DineDash. All rights reserved.");
        copyrightLabel.setForeground(Color.WHITE);
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerPanel.add(copyrightLabel);

        return footerPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FoodOrderingSystem::new);
     }
}
