package com.example.contactapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.card.MaterialCardView

class ContactAdapter(
    private val list: List<Contact>
): Adapter<ContactAdapter.ContactViewHolder>() {

    private var selectedItemPosition = -1

    private var defaultColor: ColorStateList? = null


    inner class ContactViewHolder(private var itemContact: MaterialCardView): ViewHolder(itemContact){
        val tvname: TextView = itemContact.findViewById(R.id.tvName)
        val tvmobile: TextView = itemContact.findViewById(R.id.tvMobileNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        Log.d("TAG", "onCreateViewHolder: ")
        val view : View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_contact, parent, false)


        return ContactViewHolder(view as MaterialCardView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        Log.d("TAG1", "onBindViewHolder: $position")

        val contact = list[position]
//        Log.d("TAG3", "onBindViewHolder: ${list[position]}")
        holder.tvname.text = contact.name
        holder.tvmobile.text = contact.mobileNumber
        if (contact.name.startsWith("A", true)){
            if (defaultColor == null){
                defaultColor = holder.tvname.textColors
            }
            holder.tvname.setTextColor(Color.GREEN)
        }else{
            defaultColor?.let {
                holder.tvname.setTextColor(it)
            }

        }

        // add border while selecting item
        (holder.itemView as MaterialCardView).apply {
            if (position == selectedItemPosition) {
                    strokeWidth = 4
                    setStrokeColor(ColorStateList.valueOf(Color.BLUE))
            }else{
                strokeWidth = 0
            }
            setOnClickListener {
                notifyItemChanged(selectedItemPosition)
                selectedItemPosition = holder.adapterPosition
                notifyItemChanged(selectedItemPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
