# Android Banking App - Technical Task Submission

This repository contains the completed technical task for the **Android Developer** position at **ABC TECH Group / LEONUS DIGITAL**. The application is a high-fidelity banking simulation built with modern Android standards and Jetpack Compose.

## Task Overview
The project implements a core banking user experience based on the provided Figma designs, integrating REST APIs for user data, account management, and transaction history.

### Key Features
* **Authentication:** Implementation of Google Login as the primary entry point.
* **Session Management:** Persistence of login state and user data using modern storage solutions.
* **Dashboard (Home):** Dynamic user profile fetching (Name, Avatar) and a horizontal Account Carousel displaying balance and IBAN details.
* **Transaction History:** A filtered list of transactions displaying formatted dates, truncated descriptions, and amounts.
* **Transaction Details:** Detailed view of a single transaction including ID, status, and location data.

---

## Technical Stack & Decisions

### Architecture: Clean Architecture + MVVM
I have implemented a layered architecture to ensure a clear separation of concerns, making the code testable and maintainable:
* **Domain Layer:** Contains business logic, domain models, and UseCase definitions.
* **Data Layer:** Handles API communication, DTO mapping, and repository implementations.
* **Presentation Layer:** Manages UI state with ViewModels and renders the UI with Jetpack Compose.

### Core Libraries
* **UI:** Jetpack Compose (Material 3) for a reactive and modern UI.
* **Concurrency:** Kotlin Coroutines & Flow for non-blocking asynchronous operations.
* **Networking:** Retrofit & OkHttp for REST API integration.
* **Dependency Injection:** Hilt (Dagger) for scalable dependency management.
* **Image Loading:** Coil for efficient avatar and icon rendering.

---

## Project Structure

```text
app/
├── data/
│   ├── remote/          # Retrofit interfaces & MockAPI service definitions
│   ├── model/           # Data Transfer Objects (DTOs)
│   └── repository/      # Repository implementations (Data mapping)
├── domain/
│   ├── model/           # Domain/Business models
│   ├── repository/      # Repository interfaces
│   └── usecase/         # Discrete business logic units
├── ui/
│   ├── screens/         # Feature screens (Login, Dashboard, List, Detail)
│   ├── components/      # Reusable UI elements (Carousel, Transaction Items)
│   ├── theme/           # Design system (Color, Typography, Shape)
│   └── viewmodel/       # UI State management
└── di/                  # Hilt modules for network and repository injection

```

## Build & Installation
Clone the Repository:
git clone git@github.com:din997/BankingApp.git

Open in Android Studio: Recommended version: Koala or later.

Gradle Sync: Allow the IDE to fetch dependencies.

Run: Deploy to a physical device or emulator.
