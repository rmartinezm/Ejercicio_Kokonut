package io.arkstud.ejerciciokokonut.ui.home.articles

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.model.configuration.GlideApp
import io.arkstud.ejerciciokokonut.model.entity.Article
import io.arkstud.ejerciciokokonut.router.MyRouter
import io.arkstud.ejerciciokokonut.ui.common.BaseView
import kotlinx.android.synthetic.main.fragment_articles.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
 */
class ArticlesFragment : Fragment(), BaseView {

    private val articlesViewModel: ArticlesViewModel by viewModel()
    private val router: MyRouter by inject()

    private val onArticleClicked: (article: Article) -> Unit = {
        router.navigateToArticleDetail(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_articles, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        observeViewModel()
        articlesViewModel.loadAllArticles()
    }

    private fun initializeViews(){
        recyclerViewArticles.layoutManager = LinearLayoutManager(context)
        swipeRefreshLayoutArticles.setOnRefreshListener { articlesViewModel.loadAllArticles() }
    }

    /**
     *
     */
    private fun observeViewModel(){
        articlesViewModel.loading.observe(this, loadingObserver(progressBarArticles))
        articlesViewModel.message.observe(this, messageObserver(context!!))
        articlesViewModel.articles.observe(this, Observer {
            recyclerViewArticles.adapter = ArticlesAdapter(context!!, it, onArticleClicked)
            swipeRefreshLayoutArticles.isRefreshing = false
        })
    }

    /**
     *
     * @param context
     * @param articles
     * @param onClickListener
     */
    private class ArticlesAdapter(
        private val context: Context,
        private val articles: List<Article>?,
        private val onClickListener: (article: Article) -> Unit
    ) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(context)!!
            return ViewHolder(inflater.inflate(R.layout.adapter_article, parent, false))
        }

        override fun getItemCount(): Int = articles?.size ?: 0

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val article = articles?.get(position)
            viewHolder.tvTitle?.text = article?.title
            GlideApp.with(context)
                .load(article?.image_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .centerCrop()
                .into(viewHolder.ivImage)
            viewHolder.itemView.setOnClickListener { onClickListener(article!!) }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val tvTitle: TextView? = itemView.findViewById(R.id.tvTitle)
            val ivImage: ImageView? = itemView.findViewById(R.id.ivImage)

        }

    }

}