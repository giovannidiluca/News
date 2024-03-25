package com.giovannidiluca.news

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.giovannidiluca.data.model.Article
import com.giovannidiluca.news.feed.HeadlineCard
import org.junit.Rule
import org.junit.Test

class HeadlineListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun headlineList_shouldDisplayCorrectItems() {
        val articles = listOf(
            Article("Title 1", "Description 1", "URL1", "2024-03-22T19:36:00Z", "", true, ""),
            Article("Title 2", "Description 2", "URL2", "2024-03-22T19:36:00Z", "", true, ""),
            Article("Title 3", "Description 3", "URL3", "2024-03-22T19:36:00Z", "", true, ""),
            Article("Title 4", "Description 4", "URL4", "2024-03-22T19:36:00Z", "", false, "")
        )

        composeTestRule.setContent {
            val listState = rememberLazyListState()
            LazyColumn(state = listState) {
                items(articles) { article ->
                    if (article.active) HeadlineCard(article = article, onClick = {})
                }
            }
        }

        articles.forEach { testArticle ->
            if (testArticle.active)
                composeTestRule
                    .onNodeWithText(testArticle.title)
                    .assertExists()
                    .assertHasClickAction()
            else
                composeTestRule
                    .onNodeWithText(testArticle.title)
                    .assertDoesNotExist()
        }
    }
}
