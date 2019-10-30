package com.antondolganov.contacts2.view.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.antondolganov.contacts2.R
import com.antondolganov.contacts2.databinding.FragmentProfileBinding
import com.antondolganov.contacts2.view.MainActivity
import com.antondolganov.contacts2.viewmodel.ProfileViewModel


class FragmentProfile : Fragment() {

    lateinit var model: ProfileViewModel
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProviders.of(this)[ProfileViewModel::class.java]

        setContact()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).setToolbarWithButtonHome(binding.toolbarProfile, "")
    }

    override fun onDestroyView() {
        (activity as MainActivity).setToolbarWithButtonHome(null, "")
        super.onDestroyView()
    }

    private fun setContact() {
        val arguments = arguments
        val id = arguments?.getString("id")
        id?.let {
            model.getContactById(it).observe(viewLifecycleOwner, Observer { contact ->
                binding.contact = contact
            })
        }
    }

    fun onPhoneNumberClick() {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + binding.phone.text))
        startActivity(intent)
    }
}
