package com.runitrut.rutgers_revised_notes_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.runitrut.rutgers_revised_notes_app.Model.Note
import com.runitrut.rutgers_revised_notes_app.R

class NotesRVAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
    ) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>(){

    // variable for all notes in our List
        private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // create variables for each View of the RVH
        val noteTV = itemView.findViewById<TextView>(R.id.note_title_TV)
        val timeTV = itemView.findViewById<TextView>(R.id.time_stamp_TV)
        val deleteTV = itemView.findViewById<ImageView>(R.id.delete_IV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates our layout file for each item of the RV
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent,false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        // returns the entire size of the the list
        return allNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Links the Note data class into the RV for each data point.
        holder.noteTV.text = allNotes[position].noteTitle
        holder.timeTV.text =  "Last Updated: " + allNotes[position].timeStamp
        // setOnClick to delete the RV item
        holder.deleteTV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }
        // set a onClickListener
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }
    // This fun updates the list of notes.
    fun updateList(newList: List<Note>){
        // clears the notes array list
        allNotes.clear()
        // adds new list to the all notes list
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterface{
    // uses @Delete for the RoomDB
    fun onDeleteIconClick(note: Note)
}
interface NoteClickInterface{
    // interface for clicking action
    // on recycler view item for updating it.
    fun onNoteClick(note: Note)
}