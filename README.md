
# 🍔 CraveEase - Food Ordering System

CraveEase is a desktop-based Java Swing application that simulates a modern and user-friendly food ordering experience. It allows customers to browse menus (Food, Drinks, Desserts), add items to a cart, choose a payment method (cash or online), and even rate their experience.

---

## 🛠 How to Run the Application

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (e.g., IntelliJ, Eclipse) or command-line terminal

### Steps to Execute
1. **Save the Source File**  
   Ensure the file is named:  
   `CraveEase.java`

2. **Compile the Program**  
   Open a terminal in the same directory as the file and run:
   ```bash
   javac CraveEase.java
   ```

3. **Run the Application**
   ```bash
   java CraveEase
   ```

> ⚠️ **Note:** The application requires an internet connection to load food item images and QR codes from online sources.

---

## 🔍 Core Concepts & Methodologies Used

### ✅ Java Swing for GUI
- Utilizes `JFrame`, `JPanel`, `JTabbedPane`, `JButton`, and other components to create an interactive desktop application.
- Organized layouts using `BorderLayout`, `FlowLayout`, and `GridLayout`.

### ✅ Object-Oriented Programming (OOP)
- `FoodItem` class is used to represent individual menu items.
- Encapsulation of features into methods for maintainability and readability.

### ✅ Event-Driven Programming
- Action listeners handle events like adding to cart, submitting orders, making payments, and rating the service.

### ✅ Online Resources Integration
- Food item images and QR codes are fetched via URLs using `ImageIO`.
- Payment QR codes are generated using an external API:
  ```
  https://api.qrserver.com/v1/create-qr-code/
  ```

### ✅ User Experience (UX) Features
- Live cart updates with pricing.
- Payment confirmation through dialogs.
- Interactive star-based rating system and feedback collection.

---

## 🎨 UI Highlights

- Gradient header with logo and app title.
- Categorized menu in tabbed layout: **Food**, **Drinks**, **Desserts**.
- Card-style menu items with quantity selector and "Add to Cart" button.
- Real-time cart display and order summary.
- Star rating popup and optional feedback submission after order.

---

## 📷 External Resources Used

- Unsplash and other image sources for food visuals.
- Free QR code API for payment simulation.
- Online-hosted images for star ratings and confirmation art.

---

