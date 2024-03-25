package com.giovannidiluca.data

import com.giovannidiluca.data.utils.toArticle
import com.giovannidiluca.network.model.ArticleResponse
import com.giovannidiluca.network.model.Source
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticleResponseTest {
    private val imageFallback =
        "https://clarionhealthcare.com/wp-content/uploads/2020/12/default-fallback-image.png"
    private val removedTag = "[Removed]"

    @Test
    fun toArticle_shouldConvertToArticle() {
        val articleResponse = ArticleResponse(
            source = Source(id = "1", name = "News Source"),
            author = "John Doe",
            title = "Sample Article",
            description = "This is a sample article description",
            url = "https://example.com",
            urlToImage = "https://example.com/image.jpg",
            publishedAt = "2024-03-22T19:36:00Z",
            content = "Sample article content"
        )

        val article = articleResponse.toArticle()

        assertEquals(articleResponse.title, article.title)
        assertEquals(articleResponse.description, article.description)
        assertEquals(articleResponse.urlToImage, article.urlToImage)
        assertEquals(
            "Mar, 22 2024",
            article.publishedAt
        )
        assertEquals(articleResponse.author, article.author)
        assertEquals(articleResponse.title != removedTag, true)
    }

    @Test
    fun toArticle_withNullUrlToImage_shouldUseFallbackValues() {
        val articleResponse = ArticleResponse(
            source = Source(id = "1", name = "News Source"),
            author = "John Doe",
            title = "Sample Article",
            description = null,
            url = "https://example.com",
            urlToImage = null,
            publishedAt = "2024-03-22T19:36:00Z",
            content = "Sample article content"
        )

        val article = articleResponse.toArticle()

        assertEquals(imageFallback, article.urlToImage)
    }

    @Test
    fun toArticle_withRemovedTitle_shouldSetAsInactive() {
        val articleResponse = ArticleResponse(
            source = Source(id = "1", name = "News Source"),
            author = "John Doe",
            title = "[Removed]",
            description = null,
            url = "https://example.com",
            urlToImage = "https://example.com",
            publishedAt = "2024-03-22T19:36:00Z",
            content = "Sample article content"
        )

        val article = articleResponse.toArticle()

        assertEquals(false, article.active)
    }
}
