package com.antondolganov.contacts2.view.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.antondolganov.contacts2.adapter.ContactsAdapter
import com.antondolganov.contacts2.callback.ContactClickListener
import com.antondolganov.contacts2.data.model.Contact
import com.antondolganov.contacts2.databinding.FragmentListContactsBinding
import com.antondolganov.contacts2.viewmodel.ContactsListViewModel
import com.google.android.material.snackbar.Snackbar
import java.time.Duration


class FragmentListContacts : Fragment(), ContactClickListener {


    lateinit var model: ContactsListViewModel
    lateinit var binding: FragmentListContactsBinding
    var navController: NavController? = null
    lateinit var contactsAdapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            com.antondolganov.contacts2.R.layout.fragment_list_contacts,
            container,
            false
        );
        return binding.getRoot();
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProviders.of(this)[ContactsListViewModel::class.java]

        setUI()
        observeViewModel()
        getContactsFromServer()
        loadContactList()
    }

    private fun setUI() {

        navController = Navigation.findNavController(activity!!, com.antondolganov.contacts2.R.id.nav_host_fragment)

        contactsAdapter = ContactsAdapter()
        contactsAdapter.setClickListener(this)

        binding.contactList.layoutManager = LinearLayoutManager(activity)
        binding.contactList.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))
        binding.contactList.adapter = contactsAdapter
        binding.contactList.setHasFixedSize(true)
        binding.contactList.itemAnimator = DefaultItemAnimator()

    }

    private fun getContactsFromServer() {
        model.contactsLiveData.observe(viewLifecycleOwner, Observer { contacts ->
            model.insertContactList(contacts)
        })
    }

    private fun loadContactList() {
        model.getContactsPagedList().observe(this,
            Observer { contacts ->
                contactsAdapter.submitList(contacts)
            })
    }

    private fun observeViewModel() {
        model.showLoadingLiveData.observe(viewLifecycleOwner, Observer { flag ->
            showLoading(flag)
        })

        model.statusLiveData.observe(viewLifecycleOwner, Observer { status ->
            snackbarShow(status)
        })
    }

    private fun showLoading(isShow: Boolean) {
        binding.loading.visibility = if (isShow) View.VISIBLE else View.INVISIBLE
    }

    fun snackbarShow(text: String) {
        Snackbar.make(binding.contactList, text, Snackbar.LENGTH_LONG).show()
    }

    override fun onContactClick(simpleContact: Contact) {

    }
}
