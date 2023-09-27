package cl.daracenad.elearning.exercises.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.daracenad.elearning.exercises.data.database.entities.AppParameterEntity

import cl.daracenad.elearning.exercises.databinding.CardTopicBinding

class TopicAdapter :RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    private var oldData = emptyList<AppParameterEntity>()

    class TopicViewHolder(val binding: CardTopicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder(
            CardTopicBinding.inflate(
                LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }
    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {

        holder.binding.tvTopicName.text = oldData[position].key.toString()

    }

    override fun getItemCount(): Int {

        return oldData.size
    }

    fun setData(newData: List<AppParameterEntity>){
        oldData = newData
        notifyDataSetChanged()
    }


}