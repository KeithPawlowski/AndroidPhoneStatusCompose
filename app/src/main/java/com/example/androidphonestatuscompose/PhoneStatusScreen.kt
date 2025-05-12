package com.example.androidphonestatuscompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Correct import for items extension
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PhoneStatusScreen(
    statusItems: List<StatusItem>,
    onItemClick: (StatusItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(statusItems, key = { it.id }) { item -> // Providing a key is good for performance
            StatusListItemCard(
                item = item,
                onCardClick = { onItemClick(item) }
            )
            Spacer(modifier = Modifier.height(8.dp)) // Space between cards
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class) // For Card, which is experimental in M3
@Composable
fun StatusListItemCard(
    item: StatusItem,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onCardClick, // Make the whole card clickable
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f) // Takes available space
                )
                Icon(
                    imageVector = if (item.isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (item.isExpanded) "Collapse" else "Expand"
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.summaryText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant // Softer color for summary
            )

            // Detail Section (conditionally displayed)
            if (item.isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.detailText,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}