package com.example.androidphonestatuscompose // Replace with your actual package name

// Data class to hold the information for each item in our list
data class StatusItem(
    val id: String, // Unique identifier for the item
    val title: String, // Title of the status item (e.g., "Device Info")
    var summaryText: String, // Short summary (e.g., "Pixel 8 Pro")
    var detailText: String, // Detailed information, shown when expanded
    var isExpanded: Boolean = false // State to track if the item is expanded
)