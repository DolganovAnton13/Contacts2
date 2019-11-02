package com.antondolganov.contacts2.view.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.antondolganov.contacts2.R
import com.antondolganov.contacts2.adapter.ContactsAdapter
import com.antondolganov.contacts2.callback.ContactClickListener
import com.antondolganov.contacts2.data.model.Contact
import com.antondolganov.contacts2.databinding.FragmentListContactsBinding
import com.antondolganov.contacts2.viewmodel.ContactsListViewModel
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.widget.RxSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class FragmentListContacts : Fragment(), ContactClickListener, SwipeRefreshLayout.OnRefreshListener {

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
            R.layout.fragment_list_contacts,
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
        getResultsQuerySearch()
    }

    private fun setUI() {

        navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)

        contactsAdapter = ContactsAdapter()
        contactsAdapter.setClickListener(this)

        binding.contactList.layoutManager = LinearLayoutManager(activity)
        binding.contactList.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))
        binding.contactList.adapter = contactsAdapter
        binding.contactList.setHasFixedSize(true)
        binding.contactList.itemAnimator = DefaultItemAnimator()

        binding.swipeRefreshLayout.setOnRefreshListener(this);
    }

    private fun getContactsFromServer() {
        model.contactsLiveData.observe(viewLifecycleOwner, Observer { contacts ->
            model.insertContactList(contacts)
        })
    }

    private fun loadContactList() {
        model.getContactsPagedList().observe(viewLifecycleOwner, Observer { contacts ->
            if (model.showLoadingLiveData.value == false) {
                contactsAdapter.submitList(contacts)
            }
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

    @SuppressLint("CheckResult")
    private fun getResultsQuerySearch() {
        if (model.searchQuery != null) {
            binding.searchView.setQuery(model.searchQuery, true)
        } else
            getContactsFromServer()


        RxSearchView.queryTextChanges(binding.searchView)
            .debounce(800, TimeUnit.MILLISECONDS)
            .map(CharSequence::toString)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!it.isEmpty()) {
                    model.searchQuery = it
                    showResultsSearchQuery()
                } else {
                    model.searchQuery = null
                    loadContactList()
                }
            }, {
                snackbarShow("Ошибка ${it.message}")
                showLoading(false)
            })


    }

    private fun showResultsSearchQuery() {
        model.getResultsSearchQuery().observe(viewLifecycleOwner, Observer { contacts ->
            contactsAdapter.submitList(contacts);
        })
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow)
            binding.loading.visibility = View.VISIBLE
        else {
            binding.loading.visibility = View.INVISIBLE
            binding.swipeRefreshLayout.setRefreshing(false)
        }
    }

    fun snackbarShow(text: String) {
        Snackbar.make(binding.contactList, text, Snackbar.LENGTH_LONG).show()
    }

    override fun onContactClick(simpleContact: Contact) {
        val bundle = Bundle()
        bundle.putString("id", simpleContact.id)
        navController!!.navigate(com.antondolganov.contacts2.R.id.fragmentProfile, bundle)
    }

    override fun onRefresh() {
        binding.searchView.setQuery(null, false);
        model.searchQuery = null
        model.loadContactsFromServer()
    }
}
