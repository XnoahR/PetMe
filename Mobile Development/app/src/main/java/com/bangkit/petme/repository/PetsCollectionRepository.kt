package com.bangkit.petme.repository

import android.app.Application
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.model.PetsCollection

class PetsCollectionRepository(application: Application) {
    fun getPetsCollection(): List<PetCollection> {
        return PetsCollection.petsCollection
    }
}