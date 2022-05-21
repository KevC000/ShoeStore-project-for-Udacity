package com.udacity.shoestore

import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.udacity.shoestore.models.Shoe
import kotlinx.coroutines.launch

class ActivityViewModel : ViewModel() {

    //Live data for all the shoe data
    val listOfShoes = MutableLiveData(ArrayList<Shoe>())

    //List of shoes to be displayed in the list fragment

    val listOfShoesDisplay= MutableLiveData("")
    //Data to be added in add shoe details fragment
    val newName = MutableLiveData<String>("")
    val newSize = MutableLiveData<String>("")
    val newCompany = MutableLiveData<String>("")
    val newDescription = MutableLiveData<String>("")

    //function to add shoe information
    fun addShoe() {
        viewModelScope.launch {
            val shoe = newSize.value?.let {
                Shoe(newName.value.toString(),
                    newSize.value!!.toDouble(),newCompany.value.toString(),newDescription.value.toString())
            }
            if (shoe != null) {
                listOfShoes.value?.add(shoe)
            }
            listOfShoesDisplay.value+=formatShoes(listOfShoes)
        }
    }

    //Format all the shoe data in a string
    fun formatShoes(shoes: LiveData<ArrayList<Shoe>>): String {
        var str = ""
        if(shoes.value!=null) {
            for (shoe in shoes.value!!) {
                str += formatShoe(shoe)+"\n"
            }
        }
        return str
    }

    //Format individual shoe data into a string
    fun formatShoe(shoe: Shoe): String {
        var str = ""
        str += "Shoe Name: ${shoe.name}\n" +
                "Size: ${shoe.size}\n" +
                "Company: ${shoe.company}\n" +
                "Description ${shoe.description}+\n"
        return str
    }

}