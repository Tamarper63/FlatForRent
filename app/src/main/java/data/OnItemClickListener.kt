package data

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onImageClick(position: Int)
    fun onLikeClick(position: Int)
}