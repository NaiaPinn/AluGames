package br.com.naiapinn.alugames.model

import com.google.gson.annotations.Expose

data class Games(@Expose val title: String?, @Expose val cover: String?): Recommended {

    var description = ""
    var price = 0.0
    var id = 0
    private val listNotes = mutableListOf<Int>()
    override val media: Double
        get() = listNotes.average()

    override fun toRecommend(note: Int) {
        listNotes.add(note)
    }
    constructor(title: String?, cover: String?, description: String, price: Double, id: Int = 0) : this(title, cover) {
        this.price = price
        this.description = description
        this.id = id
    }

    override fun toString(): String {
        return "\nMy Games: \n" +
                "Title: $title \n" +
                "Cover: $cover \n" +
                "Description: $description\n" +
                "Price: $price\n" +
                "Recommend: $media\n" +
                "Id: $id"
    }
}