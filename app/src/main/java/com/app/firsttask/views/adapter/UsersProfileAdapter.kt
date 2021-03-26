package com.app.firsttask.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.firsttask.databinding.ItemUserProfileBinding
import com.app.firsttask.models.generalModels.UserListModel
import com.app.firsttask.utils.general.AppConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*
import kotlin.collections.ArrayList


class UsersProfileAdapter(
    private var usersProfileList: List<UserListModel>

) : RecyclerView.Adapter<UsersProfileAdapter.UsersProfileViewHolder>() {

    class UsersProfileViewHolder private constructor(private val binding: ItemUserProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            userProfileModel: UserListModel,
        ) {

            val imageUri = userProfileModel.img

            //this is not work perfectly for invert image
            binding.imgAvatar.clearColorFilter()
            Glide.with(binding.imgAvatar.context)
                .load(imageUri)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imgAvatar)
            binding.txtName.text = userProfileModel.name
            if (userProfileModel.birthday.equals("unknown", true)) {
//                binding.txtDOB.text = "${userProfileModel.birthday}"
                binding.txtDOB.text = "Undefine"
            } else {
//                val years = AppConstants.getAge(userProfileModel.birthday)
                val ageTime = userProfileModel.birthday?.let {
                    AppConstants.printDifference(
                        it
                    )
                }
                binding.txtDOB.text = ageTime
            }
        }

        companion object {

            fun from(parent: ViewGroup): UsersProfileViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserProfileBinding.inflate(layoutInflater, parent, false)
                return UsersProfileViewHolder(binding)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersProfileViewHolder {
        return UsersProfileViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: UsersProfileViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val item = usersProfileList[position]
        item.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return usersProfileList.size
    }

    fun setFilterData(usersListData: ArrayList<UserListModel>) {
        usersProfileList = usersListData
        notifyDataSetChanged()
    }

}
