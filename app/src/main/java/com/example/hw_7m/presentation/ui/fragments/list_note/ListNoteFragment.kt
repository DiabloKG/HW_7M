import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hw_7m.R
import com.example.hw_7m.databinding.FragmentListNoteBinding
import com.example.hw_7m.domain.model.Note
import com.example.hw_7m.presentation.UIState
import com.example.hw_7m.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNoteFragment : BaseFragment(R.layout.fragment_list_note) {

    private val binding by viewBinding(FragmentListNoteBinding::bind)
    private val adapter by lazy { ListNoteAdapter(this::deleteNote, this::updateNote) }
    private val viewModel by viewModels<ListNoteViewModel>()

    override fun setupRequests() {
        getAllNotes()
    }

    override fun setupObservers() {
        viewModel.getAllNotesState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                adapter.addList(it)
            }
        )

        viewModel.deleteNoteState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                getAllNotes()
            }
        )
    }

    override fun initialize() {
        binding.rvNotes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvNotes.adapter = adapter
    }

    private fun deleteNote(note: Note) {
        viewModel.deleteNote(note)
    }

    override fun initClickListeners() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listNoteFragment2_to_createEditNoteFragment2)
        }
    }

    private fun getAllNotes() {
        viewModel.getAllNotes()
    }

    private fun updateNote(note: Note) {
        val bundle = Bundle()
        bundle.putSerializable(KEY, note)
        findNavController().navigate(R.id.action_listNoteFragment2_to_createEditNoteFragment2, bundle)
    }

    companion object {
        const val KEY = "note"
    }
}