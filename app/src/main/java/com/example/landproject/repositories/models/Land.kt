package com.example.landproject.repositories.models

import javax.xml.bind.annotation.*

@XmlRootElement(name = "fields")
@XmlAccessorType(XmlAccessType.FIELD)
data class Land(

        @field:XmlElementWrapper(name = "ladfrlVOList")
        @field:XmlElement(name = "ldCodeNm")
        val ldCodeNm:String,//법정동명
        @field:XmlElement(name = "pnu")
        val pnu:String,//고유번호
        @field:XmlElement(name = "posesnSeCodeNm")
        val posesnSeCodeNm:String  //소유구분명
//        val mnnmSlno:String,//지번
)
