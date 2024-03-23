package com.giovannidiluca.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.giovannidiluca.data.model.Article
import com.giovannidiluca.news.NewsViewModel.NewsUiState.Error
import com.giovannidiluca.news.NewsViewModel.NewsUiState.Loading
import com.giovannidiluca.news.NewsViewModel.NewsUiState.Success
import com.giovannidiluca.news.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    when (val uiState = viewModel.uiState.collectAsState().value) {
                        Error -> ErrorMessage()
                        Loading -> Loading()
                        is Success -> HeadlineList(articles = uiState.articles)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ErrorMessage() {
    Snackbar { Text(text = stringResource(R.string.error)) }
}

@Preview
@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(80.dp))
    }
}

@Composable
fun HeadlineList(articles: List<Article>) {
    LazyColumn {
        item { Title() }
        items(articles) { article ->
            HeadlineCard(article = article)
        }
    }
}

@Preview
@Composable
fun Title() {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun HeadlineCard(article: Article) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = article.urlToImage,
                contentDescription = stringResource(R.string.headline_image_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp)),
                loading = { CircularProgressIndicator() },
            )
            Text(text = article.title, fontWeight = FontWeight.Bold)
            Text(text = article.description, style = MaterialTheme.typography.bodySmall)
            Text(text = article.publishedAt, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeadlineCardPreview() {
    NewsTheme {
        HeadlineCard(
            Article(
                title = "Kate, Princess of Wales, announces cancer was found during surgery, undergoing preventative chemotherapy - CBS News",
                description = "The video announcement from Kate, Princess of Wales, comes after months of widespread speculation about her health and controversy over doctored images released by Kensington Palace.",
                urlToImage = "",
                publishedAt = "2024-03-22T19:36:00Z"
            )
        )
    }
}
