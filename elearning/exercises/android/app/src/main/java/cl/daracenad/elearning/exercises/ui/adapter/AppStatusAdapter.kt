package cl.daracenad.elearning.exercises.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.daracenad.elearning.exercises.databinding.CardStatusBinding


import cl.daracenad.elearning.exercises.domain.model.AppStatus


class AppStatusAdapter: RecyclerView.Adapter<AppStatusAdapter.AppStatusViewHolder>()  {
    var listAppStatus = emptyList<AppStatus>()

    class AppStatusViewHolder(val binding: CardStatusBinding) : RecyclerView.ViewHolder(binding.root)

    fun collection(newData: List<AppStatus>){
        listAppStatus = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppStatusViewHolder {
        return AppStatusAdapter.AppStatusViewHolder(
            CardStatusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listAppStatus.size
    }

    override fun onBindViewHolder(holder: AppStatusViewHolder, position: Int) {
        holder.binding.tvText.text = listAppStatus[position].text.toString()
        holder.binding.tvStatus.text = listAppStatus[position].text.toString()

    }

    fun insertStatus(entity: AppStatus){
        listAppStatus = listAppStatus + entity
        notifyDataSetChanged()


    }

}