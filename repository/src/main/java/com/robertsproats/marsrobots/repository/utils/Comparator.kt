package com.robertsproats.marsrobots.repository.utils

import com.robertsproats.marsrobots.repository.model.SimpleMarsRobotsModel

object Comparator {

    fun compareSimpleModels(modelA: SimpleMarsRobotsModel?, modelB: SimpleMarsRobotsModel?): Boolean {
        if (modelA == null) return false
        if (modelB == null) return false

        return compareLists(modelA.itemList, modelB.itemList)
    }

    private fun compareLists(listA: List<SimpleMarsRobotsModel.SimpleMarsRobotsItem>?,
                             listB: List<SimpleMarsRobotsModel.SimpleMarsRobotsItem>?): Boolean {

        if (listA == null) return false
        if (listB == null) return false
        if (listA.isEmpty()) return false
        if (listB.isEmpty()) return false

        val arrayA = listA.toTypedArray()
        val arrayB = listB.toTypedArray()

        return arrayA.contentDeepEquals(arrayB)
    }
}