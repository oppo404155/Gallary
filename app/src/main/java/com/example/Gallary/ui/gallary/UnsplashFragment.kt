package com.example.Gallary.ui.gallary

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState.*
import com.example.Gallary.R
import com.example.Gallary.databinding.FragmentUnsplashBinding
import com.example.Gallary.ui.Adapters.UnsplashAdapter
import com.example.Gallary.ui.UnsplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnsplashFragment : Fragment(R.layout.fragment_unsplash) {

    val unsplashViewModel: UnsplashViewModel by viewModels()
    var _gallaryLayout: FragmentUnsplashBinding? = null
    val gallaryLayout get() = _gallaryLayout!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _gallaryLayout = FragmentUnsplashBinding.bind(view)
        val unsplashAdapter = UnsplashAdapter()
        gallaryLayout.apply {
            unsplashRecycler.apply {

                setHasFixedSize(true)
                adapter = unsplashAdapter.withLoadStateHeaderAndFooter(
                    header = UnsplashLoadStateAdapter { unsplashAdapter.retry() },
                    footer = UnsplashLoadStateAdapter { unsplashAdapter.retry() }
                )

                unsplashAdapter.onItemClicked = {
          var action= bundleOf("photo" to  it)
             findNavController().navigate(R.id.action_unsplashFragment_to_unsplashDetailsFragment,action)
                }
                retryBtn.setOnClickListener {
                    unsplashAdapter.retry()
                }


            }

        }
        unsplashViewModel.photos.observe(viewLifecycleOwner) {
            unsplashAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        unsplashAdapter.addLoadStateListener { loadstate ->
            gallaryLayout.apply {
                progress.isVisible = loadstate.source.refresh is Loading
                unsplashRecycler.isVisible = loadstate.source.refresh is NotLoading
                retryBtn.isVisible = loadstate.source.refresh is Error
                erorrTxv.isVisible = loadstate.source.refresh is Error
                if (loadstate.source.refresh is NotLoading &&
                    loadstate.append.endOfPaginationReached &&
                    unsplashAdapter.itemCount < 1
                ) {
                    unsplashRecycler.isVisible = false
                    emptyTxv.isVisible = true

                } else {
                    emptyTxv.isVisible = false
                }
            }
        }
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_gallary, menu)
        val searchItem = menu.findItem(R.id.search_menu)
        val searchview = searchItem.actionView as SearchView
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    gallaryLayout.unsplashRecycler.scrollToPosition(0)
                    unsplashViewModel.searchphoto(query)
                    searchview.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _gallaryLayout = null
    }


}