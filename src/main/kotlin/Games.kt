package org.example

class Games(val title: String, val cover: String  ) {

    val description = ""
    override fun toString(): String {
        return "My Games: \n" +
                "Title: $title \n" +
                "Cover: $cover \n" +
                "Description: $description"
    }


}