package com.bignerdranch.android.playerslistsfragmentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.bignerdranch.android.playerslistsfragmentapp.databinding.FragmentAddPlayerBinding
import java.util.UUID

const val ADD_PLAYER_TAG = "AddPlayerFragment"
class AddPlayerFragment: Fragment() {
    private var _binding: FragmentAddPlayerBinding? = null

    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it's null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPlayerBinding.inflate(layoutInflater, container, false)
        val activity = requireActivity()
        if (activity is DataProvider) {
            //?
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener{
            val player = Player(
                id = UUID.randomUUID(),
                name = binding.getNameText.text.toString(),
                club = binding.getClubNameText.text.toString(),
                photoUrl = binding.getPhotourlText.text.toString(),
                clubUrl = binding.getCluburlText.text.toString()
            )
             setFragmentResult(PLAYER_LIST_TAG, bundleOf(ADD_PLAYER_TAG to player))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}