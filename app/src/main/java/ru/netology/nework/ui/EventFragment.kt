package ru.netology.nework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.nework.adapter.EventAdapter
import ru.netology.nework.databinding.FragmentEventsBinding
import ru.netology.nework.dto.Event
import ru.netology.nework.viewModel.EventsViewModel

@AndroidEntryPoint
class EventFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding: FragmentEventsBinding
        get() = _binding!!

    private val viewModel: EventsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EventAdapter(object : EventAdapter.OnInteractionListener {
            override fun onLike(event: Event) {

            }

        })
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        viewModel.events.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }

        //viewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
        //    Toast.makeText(requireContext(), error.status.toString(), Toast.LENGTH_SHORT).show()
        //    //TODO create error handler
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}