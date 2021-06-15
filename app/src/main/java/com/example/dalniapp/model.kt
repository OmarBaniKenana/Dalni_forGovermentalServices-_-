package com.example.dalniapp

class model {



    lateinit var name: String
    var GovrId: String? = null
    var GovrId2: String? = null
    var GovrId3: String? = null
    lateinit var idt: String

    internal constructor() {
    }

    constructor(name: String, GovrId: String, GovrId2: String, GovrId3: String, idt: String) {
        this.name = name
        this.GovrId = GovrId
        this.GovrId2 = GovrId2
        this.GovrId3 = GovrId3
        this.idt = idt

    }

}