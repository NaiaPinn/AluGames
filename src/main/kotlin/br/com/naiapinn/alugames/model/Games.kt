package br.com.naiapinn.alugames.model

import com.google.gson.annotations.Expose

class Games(@Expose val title: String?, @Expose val cover: String?): Recommended {

    var description = ""
    var price = 0.0
    private val listNotes = mutableListOf<Int>()
    override val media: Double
        get() = listNotes.average()

    override fun toRecommend(note: Int) {
        listNotes.add(note)
    }
    constructor(title: String?, cover: String?, description: String, price: Double) : this(title, cover) {
        this.price = price
        this.description = description
    }

    override fun toString(): String {
        return "My Games: \n" +
                "Title: $title \n" +
                "Cover: $cover \n" +
                "Description: $description" +
                "Price: $price" +
                "Recommend: $media"
    }
}