package com.giovannidiluca.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.giovannidiluca.data.model.Article

@Composable
fun NewsDetailsScreen() {
    DetailsCard(
        article = Article(
            title = "Kate, Princess of Wales, announces cancer was found during surgery, undergoing preventative chemotherapy - CBS News",
            description = "The video announcement from Kate, Princess of Wales, comes after months of widespread speculation about her health and controversy over doctored images released by Kensington Palace.",
            urlToImage = "",
            publishedAt = "2024-03-22T19:36:00Z",
            author = "John Doe",
            active = true,
        )
    )
}

@Composable
fun DetailsCard(article: Article) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SubcomposeAsyncImage(
            model = article.urlToImage,
            contentDescription = stringResource(R.string.headline_image_description),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 320.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            loading = { CircularProgressIndicator() },
        )
        Text(
            text = stringResource(R.string.author) + article.author,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(text = article.description, style = MaterialTheme.typography.bodyMedium)
        Text(text = article.publishedAt, style = MaterialTheme.typography.bodySmall)

    }
}