package data

import android.os.Parcel
import android.os.Parcelable

class ApartmentModel(private var flatId: String, private var price: String): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(flatId)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApartmentModel> {
        override fun createFromParcel(parcel: Parcel): ApartmentModel {
            return ApartmentModel(parcel)
        }

        override fun newArray(size: Int): Array<ApartmentModel?> {
            return arrayOfNulls(size)
        }
    }

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

}