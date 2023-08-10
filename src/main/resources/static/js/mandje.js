"use strict"
import {byId, toon, verberg} from "./util.js";
const mandje = sessionStorage.getItem("mandje");

const resp = await fetch(`mandje/${mandje}`);
if (resp.ok) {
    const filmsInMandje = await resp.json();
    const tableBody = byId("tableBody");
    let totalePrijs = 0;

    for (const film of filmsInMandje){
        const tr = tableBody.insertRow();
        tr.insertCell().innerText = film.titel;
        tr.insertCell().innerText = film.prijs;
        totalePrijs = totalePrijs + film.prijs;

    }
    const tr = tableBody.insertRow();
    tr.insertCell().innerText = "Totaal";
    tr.insertCell().innerText = totalePrijs;
}