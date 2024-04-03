package data

class AdModel(private var flatId: String, private var price: String) {
    // Getter and Setter
    fun getFlatId(): String {
        return flatId
    }

    fun setFlatId(flatId: String) {
        this.flatId = flatId
    }

    fun getFlatPrice(): String {
        return price
    }

    fun setCourse_rating(price: String) {
        this.price = price
    }
}