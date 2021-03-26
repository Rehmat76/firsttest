package com.app.firsttask.views.fragments

import android.nfc.tech.MifareUltralight
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.firsttask.databinding.FragmentUserListingBinding
import com.app.firsttask.models.generalModels.UserListModel
import com.app.firsttask.utils.general.AppConstants
import com.app.firsttask.viewmodel.UserProfileViewModel
import com.app.firsttask.views.adapter.UsersProfileAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate

class UserListFragment : BaseFragment<FragmentUserListingBinding>() {

    private lateinit var binding: FragmentUserListingBinding
    private val viewModel: UserProfileViewModel by viewModel()
    private var usersListData = ArrayList<UserListModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: UsersProfileAdapter
    private var currentPageNo = 0
    private var isLastPage = false

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Boolean
    ): FragmentUserListingBinding {
        binding = FragmentUserListingBinding.inflate(inflater, container, b)
        return binding
    }

    override fun initViews() {
        recyclerView = binding.recyclerView
        layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        adapter = UsersProfileAdapter(usersListData)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener)

        viewModel.callListUserData(AppConstants.STARTING_PAGE_OFFSET)

        Timer().schedule(1000, (60*1000)) {
            activity?.runOnUiThread {
                //on main thread
                Toast.makeText(requireContext(), "UpdateAdapter", Toast.LENGTH_SHORT).show()
                recyclerView.adapter?.notifyDataSetChanged()
            }
//            recyclerView.adapter?.notifyDataSetChanged()
        }/*.schedule(10 * 1000) {
            Toast.makeText(requireContext(), "UpdateAdapter", Toast.LENGTH_SHORT).show()
            recyclerView.adapter?.notifyDataSetChanged()
        }*/
    }

    override fun attachViewModel() {
        with(viewModel) {
            usersList.observe(viewLifecycleOwner, {
                binding.progressBar.visibility = View.GONE
                // stop animating Shimmer and hide the layout
                usersListData.addAll(it)
                recyclerView.adapter?.notifyDataSetChanged()
                isLastPage = AppConstants.STARTING_PAGE_LIMIT > it.size
            })
        }
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (!isLastPage) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= MifareUltralight.PAGE_SIZE
                ) {
                    currentPageNo = if (usersListData.size == 0) {
                        AppConstants.STARTING_PAGE_OFFSET
                    } else {
                        usersListData.size
                    }
                    currentPageNo.let {
                        isLastPage = true
                        binding.progressBar.visibility = View.VISIBLE
                        viewModel.getUsersList(it)

                    }
                }
            }
        }
    }
}