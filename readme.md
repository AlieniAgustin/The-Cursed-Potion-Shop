# 🧪 The Cursed Potion Shop: Spring MVC Challenge (This readme is old; more things have been added. I'll update it later xd.)

## 📜 The Scenario
Welcome to **"The Bubbling Cauldron,"** the most questionable alchemy shop in the kingdom. The Head Alchemist is brilliant but terrible at paperwork. He keeps brewing unstable mixtures and forgetting which ones are safe to sell.

The Ministry of Magic is coming for an inspection next week. Your task is to build a digital **Inventory Management System** using **Spring Boot** to organize the chaos, enforce safety regulations automatically, and help the shop avoid being shut down.

---

## 🎯 The Mission
You must create a web application that allows the Alchemist to **Catalog**, **Create**, **View**, and **Destroy** potions. The system must act as a "safety filter," enforcing strict magic laws without human intervention.

### 1. Inventory Management (The Catalog)
The shop needs a central dashboard to view all current stock. The Alchemist needs to see the **Name**, **Effect**, **Risk Level**, and **Legal Status** of every item at a glance.

### 2. The Brewing Process (Creation & Validation)
When adding a new potion to the system, the following rules must be enforced strictly:
* **Unique Naming:** No two potions can share the same name. If a duplicate is attempted, the system must reject it and warn the user.
* **Volatility Index:** Every potion must have a defined **Risk Level** between **1 and 100**. Values outside this range are physically impossible and should generate an error.
* **Automatic Censorship:** The Alchemist often brews dangerous things. If a potion is created with a **Risk Level greater than 90**, the system must **automatically** mark it as **"Illegal"** (Prohibited). If it is 90 or below, it is "Legal". This status cannot be chosen manually; it is determined by the risk.

### 3. Emergency Protocol (Deletion)
If an inspector arrives, the Alchemist needs a quick way to **remove** a potion from the inventory permanently.

### 4. Incident Prevention (Error Handling)
The application must be crash-proof. If the Alchemist enters invalid data (like a risk level of 200 or a duplicate name) or tries to view a potion that doesn't exist:
* The system **must not** crash (no "Whitelabel Error Pages").
* It must display a helpful, user-friendly error message explaining exactly what went wrong.
* Redirects must handle messages correctly so the user knows if an action succeeded or failed.

---

## 🛠 Technical Requirements

* **Framework:** Java Spring Boot (Maven).
* **Architecture:** Model-View-Controller (MVC).
* **Data Storage:** In-memory storage (Lists/Maps) is acceptable for this prototype. Database connection is not required yet.
* **Frontend:** Server-side rendering using **Thymeleaf**.

---

## 🚀 Expected Endpoints/Features

The Ministry expects the following workflows to be functional:

1.  **Catalog View:** A page listing all available potions.
2.  **Creation Form:** A dedicated page to input new potion details.
3.  **Details View:** A specific page to view the full dossier of a single potion by its ID.
4.  **Disposal Action:** A mechanism to delete an item and return to the catalog with a confirmation message.

---

## 🏃‍♂️ How to Run the Application

to do

---
