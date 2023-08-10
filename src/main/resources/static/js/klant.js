"use strict"
import {byId, toon, verberg} from "./util.js";

byId("zoek").onclick = async function () {
    verbergKlantenEnFouten();
    const woordInput = byId("woord");
    if (woordInput.checkValidity()) {
        findKlantByWoord(woordInput.value);
    } else {
        toon("naamFout");
        woordInput.focus();
    }
};

function verbergKlantenEnFouten() {
    verberg("tableBody");
    verberg("naamFout");
    verberg("storing");
}

async function findKlantByWoord(woord) {
    const resp = await fetch(`klanten?naamBevat=${woord}`);
    if (resp.ok) {
        const klanten = await resp.json();
        const tableBody = byId("tableBody");
        toon("tableBody");
        for (const klant of klanten){
            console.log(klant)
            const tr = tableBody.insertRow();
            //tr.insertCell().innerText = klant.id;
            const a = document.createElement("a")
            a.href="bevestig.html";
            a.innerText=klant.familienaam+ " " + klant.voornaam;
            a.onclick = function (){
                sessionStorage.setItem("klantId", klant.id)
            }

            tr.insertCell().appendChild(a)
            tr.insertCell().innerText = klant.straatNummer;
            tr.insertCell().innerText = klant.postcode;
            tr.insertCell().innerText = klant.gemeente;
        }
    }
}