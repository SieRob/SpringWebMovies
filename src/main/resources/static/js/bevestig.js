"use strict"
import {byId, toon, verberg} from "./util.js";

const klantId = sessionStorage.getItem("klantId");
const mandje = sessionStorage.getItem("mandje");

const resp = await fetch(`klanten/${klantId}`);
if (resp.ok) {
    const klanten = await resp.json();
    const bevestigH2Loc = byId("bevestigH2Loc");
    const btnBevestig = byId("btnBevestig");

    var count = 0;
    if(mandje.length>0) {
        for (var i = 0; i < mandje.length; i++) {
            if (mandje.charAt(i) == ',')
                count++;
        }
        count++;
    }
    bevestigH2Loc.innerText = count + " film(s) voor " + klanten.familienaam + " " + klanten.voornaam;
    console.log(klanten)

    btnBevestig.onclick = async function (){
        bevestig();
        btnBevestig.disabled=true;
        verberg("mandje");
        sessionStorage.removeItem("mandje");
    }
}

async function bevestig(){
    console.log("Bevestig bestelling:" + mandje);

    const resp = await fetch(`reservaties/${klantId}/${mandje}`);
    if (resp.ok) {
        const bevestigData = await resp.json();
        console.log(bevestigData)

        const resp2 = await fetch(`mandje/${mandje}`);
        if (resp2.ok) {
            const filmData = await resp2.json();
            //let counter = bevestigData.length -1;
            let counter = 0;
            for (const film of filmData) {
                const bevestigList = byId("bevestigList");
                toon("bevestigList");


                var li = document.createElement("li");
                li.innerText = bevestigData[counter];
                counter++;

                bevestigList.appendChild(li)
            }
        }
    }
}