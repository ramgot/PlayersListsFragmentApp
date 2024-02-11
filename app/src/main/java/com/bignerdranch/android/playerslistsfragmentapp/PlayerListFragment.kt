package com.bignerdranch.android.playerslistsfragmentapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.FragmentTransaction
import com.bignerdranch.android.playerslistsfragmentapp.databinding.FragmentPlayersListBinding

const val PLAYER_LIST_TAG = "PlayerListFragment"

class PlayerListFragment: Fragment() {

    private var _binding: FragmentPlayersListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it's null. Is the vies visible?"
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayersListBinding.inflate(inflater, container, false)
        binding.playersListRecycler.layoutManager = LinearLayoutManager(context)
        val adapter: PlayersAdapter
        var playerDataSource: PlayerDataSource = PlayerDataSource()

        adapter = PlayersAdapter(
            object : PlayerActionListener {
                override fun onPlayerDelete(player: Player) {
                    playerDataSource.deletePlayer(player)
                }

                override fun onPlayerDetails(player: Player) {
                    Toast.makeText(context,  "ok", Toast.LENGTH_SHORT).show()
                }
            }
        )
        val userListener: PlayerListener = {
            adapter.players = it
        }
        playerDataSource.addListener(userListener)
        binding.playersListRecycler.adapter = adapter
        binding.addPlayerButton.setOnClickListener {
           setFragmentResultListener(PLAYER_LIST_TAG) { key, bundle ->
               val player = bundle.getParcelable<Player>(ADD_PLAYER_TAG)!!
               playerDataSource.addPlayer(player)
            }
        }
        binding.addRandomButton.setOnClickListener {
            val player = playerDataSource.formRandomPlayer()
            playerDataSource.addPlayer(player)
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}