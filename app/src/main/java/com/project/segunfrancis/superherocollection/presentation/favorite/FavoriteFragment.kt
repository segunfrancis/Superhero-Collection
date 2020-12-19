package com.project.segunfrancis.superherocollection.presentation.favorite

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.FavoriteFragmentBinding
import com.project.segunfrancis.superherocollection.di.LikePreference
import com.project.segunfrancis.superherocollection.presentation.detail.DetailActivity
import com.project.segunfrancis.superherocollection.presentation.main.MainViewModel
import com.project.segunfrancis.superherocollection.presentation.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var toast: Toast? = null
    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    @LikePreference
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.favorite_fragment, container, false)
        _binding = FavoriteFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = FavoriteRecyclerAdapter({ item ->
            startActivity(
                Intent(
                    requireContext(),
                    DetailActivity::class.java
                ).putExtra(AppConstants.INTENT_KEY, item)
            )
        }, { item ->
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
            editor.putBoolean(item.id.toString(), true)
            editor.apply()
        }, { item ->
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
            editor.putBoolean(item.id.toString(), false)
            editor.apply()
        }, { lb, item ->
            lb.isLiked = sharedPreferences.getBoolean(item.id.toString(), false)
        })
        binding.favSuperHeroRecyclerView.adapter = adapter
        binding.favSuperHeroRecyclerView.addItemDecoration(
            MarginItemDecoration(
                requireContext().resources.getInteger(R.integer.span_count),
                16,
                AppConstants.convertDpToPx(requireContext())
            )
        )
        viewModel.allFavorites.observe(viewLifecycleOwner) {
            binding.emptyAnimation.isVisible = it.isNullOrEmpty()
            adapter.submitList(it)
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

    override fun onDestroyView() {
        super.onDestroyView()
        toast?.cancel()
        _binding = null
    }
}