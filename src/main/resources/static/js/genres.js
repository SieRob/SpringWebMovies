"use strict"
import {byId, toon, verwijderChildElementenVan} from "./util.js";


const resp = await fetch("genres");
if (resp.ok) {
    const genres = await resp.json();
    const genreDiv = byId("genreSection")

    for (const genre of genres) {
        const span = document.createElement("span")
        const a = document.createElement("a");
        a.innerText = genre.naam;
        a.href = "javascript:";
        a.onclick = function () {
            sessionStorage.setItem("genreId", JSON.stringify(genre.id));

            const selectedGenre = byId("selectedGenre");
            selectedGenre.innerText = genre.naam

            getFilmsFromGenre();
            toon("select")
        };

        span.appendChild(a)
        genreDiv.appendChild(span);
    }
}

async function getFilmsFromGenre() {
    const genreId = sessionStorage.getItem("genreId");
    const resp2 = await fetch(`genres/${genreId}`);
    if (resp2.ok) {
        const films = await resp2.json()
        const imageList = byId("imageList");

        verwijderChildElementenVan(imageList);

        for (const film of films) {
            if(film.titel!=null) {
                const img = document.createElement("img");
                const a = document.createElement("a");

                img.src = "images/" + film.filmId + ".jpg";
                img.alt = film.titel;

                a.href = "film.html";
                a.onclick= function () {
                    sessionStorage.setItem("filmId", JSON.stringify(film.filmId ));
                }

                    a.appendChild(img);
                imageList.appendChild(a);
            }else{
                var p = document.createElement("p")
                p.innerText="Geen films van dit genre gevonden"

                imageList.appendChild(p);
            }
        }
    }
}