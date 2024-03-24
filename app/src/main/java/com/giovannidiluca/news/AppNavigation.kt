package com.giovannidiluca.news

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.giovannidiluca.data.model.Article
import kotlinx.serialization.json.Json
import java.net.URLDecoder

private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

@VisibleForTesting
internal const val ARTICLE_JSON_ARG = "articleJson"

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "news_list") {
        composable("news_list") {
            NewsListScreen(navController)
        }

        composable(
            route = "news_details_route/{$ARTICLE_JSON_ARG}",
            arguments = listOf(
                navArgument(ARTICLE_JSON_ARG) { type = NavType.StringType },
            ),
        ) {
            val articleDecoded =
                URLDecoder.decode(it.arguments?.getString(ARTICLE_JSON_ARG), URL_CHARACTER_ENCODING)
            val article = Json.decodeFromString<Article>(articleDecoded)
            NewsDetailsScreen(article)
        }
    }
}
