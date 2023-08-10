"use strict"
import {byId, toon, verberg} from "./util.js";
const filmId = sessionStorage.getItem("filmId");

const resp = await fetch(`film/${filmId}`);
if (resp.ok) {
    const film = await resp.json();
    const titelLoc = byId("titelLoc");
    const imageLoc = byId("imageLoc");
    const prijsLoc = byId("prijsLoc");
    const voorraadLoc = byId("voorraadLoc");
    const gereserveerdLoc = byId("gereserveerdLoc");
    const beschikbaarLoc = byId("beschikbaarLoc");
    const btnToevoegen = byId("btnToevoegen");

    titelLoc.innerText = film.titel;

    const img = document.createElement("img");
    img.src = "images/" + film.id + ".jpg"
    img.alt = film.titel
    imageLoc.appendChild(img);

    prijsLoc.innerText = film.prijs;
    voorraadLoc.innerText = film.voorraad;
    gereserveerdLoc.innerText =film.gereserveerd;

    let beschikbaar = film.voorraad + film.gereserveerd;
    beschikbaarLoc.innerText = beschikbaar;

    if(beschikbaar == 0){
        console.log("Film is niet beschikbaar, disabling button")
        btnToevoegen.disabled = true;
        btnToevoegen.innerText = "Niet in voorraad"
    }
    btnToevoegen.onclick = function (){
        if(sessionStorage.getItem("mandje")){
            if(!sessionStorage.getItem("mandje").includes(film.id)){
                sessionStorage.setItem("mandje", sessionStorage.getItem("mandje") +"," + film.id);

                window.location="mandje.html";
            }
            else{
                //toon("duplicateInMandje");
                window.location="mandje.html";
            }
        }
        else{
            sessionStorage.setItem("mandje", JSON.stringify(film.id));
            window.location="mandje.html";
        }
    }

}

