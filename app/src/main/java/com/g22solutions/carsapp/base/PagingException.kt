package com.g22solutions.carsapp.base

class PagingException constructor(msg:String?): Exception(msg) {

    var type:String = "app"

    constructor():this("")

}