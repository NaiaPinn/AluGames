package org.example

class Games {

    var title = ""
    var cover = ""
    var description = ""

    override fun toString(): String {
        return "My Games: \n" +
                "Title: $title \n" +
                "Cover: $cover \n" +
                "Description: $description"
    }


}