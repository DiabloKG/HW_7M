import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_7m.databinding.NoteItemBinding
import com.example.hw_7m.domain.model.Note

class ListNoteAdapter(
    private val deleteNote: (Note) -> Unit,
    private val updateNote: (Note) -> Unit
) : RecyclerView.Adapter<ListNoteAdapter.ListNoteViewHolder>() {

    private var list = arrayListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNoteViewHolder {
        return ListNoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ListNoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Note>) {
        this.list = list as ArrayList<Note>
        notifyDataSetChanged()
    }

    inner class ListNoteViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvDescription.text = note.description

            itemView.setOnClickListener {
                updateNote(note)
            }

            itemView.setOnLongClickListener {
                deleteNote(note)
                false
            }
        }
    }
}