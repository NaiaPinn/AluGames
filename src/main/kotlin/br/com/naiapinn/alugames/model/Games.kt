package br.com.naiapinn.alugames.model

class Games(val title: String?, val cover: String?) {

    var description = ""
    var price = 0.0

    constructor(title: String?, cover: String?, description: String, price: Double ): this(title, cover) {
        this.price = price
        this.description = description
    }

    override fun toString(): String {
        return "My Games: \n" +
                "Title: $title \n" +
                "Cover: $cover \n" +
                "Description: $description" +
                "Price: $price"
    }
}