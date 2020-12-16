package com.project.segunfrancis.superherocollection.presentation.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.FragmentHomeBinding
import com.project.segunfrancis.superherocollection.di.LikePreference
import com.project.segunfrancis.superherocollection.presentation.detail.DetailActivity
import com.project.segunfrancis.superherocollection.presentation.main.MainViewModel
import com.project.segunfrancis.superherocollection.presentation.main.SuperHeroLoadStateAdapter
import com.project.segunfrancis.superherocollection.presentation.main.SuperHeroRecyclerAdapter
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants.INTENT_KEY
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants.convertDpToPx
import com.project.segunfrancis.superherocollection.presentation.utils.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.String.valueOf
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var toast: Toast? = null
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()

    @LikePreference
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = SuperHeroRecyclerAdapter({ item -> // OnItemClick
            startActivity(
                Intent(
                    requireContext(),
                    DetailActivity::class.java
                ).putExtra(INTENT_KEY, item)
            )
        }, { item -> // OnItemLike
            if (toast == null) {
                showToast(item.name.plus(" added to favorites"))
            } else {
                toast?.cancel()
                showToast(item.name.plus(" added to favorites"))
            }
            //viewModel.setShowBadge(true)
            // add to favorite
            item.isFavorite = true
            viewModel.setFavorite(item)
            val editor = sharedPreferences.edit()
            editor.putBoolean(valueOf(item.id), true)
            editor.apply()
        }, { item -> // OnItemUnlike
            if (toast == null) {
                showToast(item.name.plus(" removed from favorites"))
            } else {
                toast?.cancel()
                showToast(item.name.plus(" removed from favorites"))
            }
            // remove from favorite
            item.isFavorite = false
            viewModel.removeFavorite(item)
            val editor = sharedPreferences.edit()
            editor.putBoolean(valueOf(item.id), false)
            editor.apply()
        }, { lb, item -> // Set state of Like button
            lb.isLiked = sharedPreferences.getBoolean(valueOf(item.id), false)
        })
        binding.superHeroRecyclerView.addItemDecoration(
            MarginItemDecoration(
                requireContext().resources.getInteger(R.integer.span_count),
                16,
                convertDpToPx(requireContext())
            )
        )
        binding.retryButton.setOnClickListener { adapter.retry() }
        binding.superHeroRecyclerView.adapter = adapter.withLoadStateFooter(
            footer = SuperHeroLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            binding.superHeroRecyclerView.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            binding.retryAnimation.isVisible = loadState.source.refresh is LoadState.Error

            // Display on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error
            errorState?.let {
                showToast(it.error.localizedMessage!!)
            }
        }

        lifecycleScope.launch {
            viewModel.superHeroesData.collect {
                adapter.submitData(it)
            }
        }
    }

    private fun showToast(message: String) {
        toast = Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        )
        toast?.show()
    }
}