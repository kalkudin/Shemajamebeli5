package com.example.shemajamebeli5

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shemajamebeli5.databinding.FragmentMainChatPageBinding
import kotlinx.coroutines.launch

class MainChatPageFragment :
    BaseFragment<FragmentMainChatPageBinding>(FragmentMainChatPageBinding::inflate) {

    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter : ChatRecycleAdapter

    override fun setUp() {
        setUpChatRecycleAdapter()
    }

    override fun setUpOnClickListeners() {
        filter()
    }

    private fun setUpChatRecycleAdapter() {
        adapter = ChatRecycleAdapter()

        binding.chatRecycleAdapter.layoutManager = LinearLayoutManager(requireContext())
        binding.chatRecycleAdapter.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dataFlow.collect { userList ->
                    adapter.submitList(userList)
                }
            }
        }

        viewModel.addValues()
    }

    private fun filter(){

    }

}