package com.antondolganov.contacts2.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antondolganov.contacts2.data.model.Contact
import com.antondolganov.contacts2.databinding.ItemContactBinding
import androidx.recyclerview.widget.DiffUtil
import java.util.*
import android.view.LayoutInflater
import com.antondolganov.contacts2.callback.ContactClickListener

class ContactsAdapter : PagedListAdapter<Contact, ContactsAdapter.ContactHolder>(DIFF_CALLBACK) {

    lateinit var contactClickListener: ContactClickListener

    fun setClickListener(contactClickListener: ContactClickListener) {
        this.contactClickListener = contactClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactHolder(binding.getRoot())
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.binding?.contact = getItem(position)
    }

    inner class ContactHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val binding: ItemContactBinding?

        init {

            binding = DataBindingUtil.bind(itemView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            binding?.contact?.let {
                contactClickListener.onContactClick(it)
            }
        }
    }

    companion object {
       private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return Objects.equals(oldItem.id, newItem.id)
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return (Objects.equals(oldItem.id, newItem.id)
                        && Objects.equals(oldItem.name, newItem.name)
                        && Objects.equals(oldItem.phone, newItem.phone)
                        && oldItem.height == newItem.height)
            }
        }
    }
}