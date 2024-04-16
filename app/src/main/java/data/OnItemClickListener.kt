package data

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onUnlikeClick(position: Int)
    fun onLikeClick(position: Int)
    fun onAddFlatClick()
}