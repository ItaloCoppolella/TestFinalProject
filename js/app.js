const playersGrid = document.querySelector('.players-grid');

        playersGrid.addEventListener('dragstart', (e) => {
            e.dataTransfer.setData('text/plain', e.target.id);
        });
        function allowDrop(event) {
            event.preventDefault();
        }

        function drag(event) {
            event.dataTransfer.setData('text/plain', event.target.id);
        }

        function drop(event) {
            event.preventDefault();
            const playerId = event.dataTransfer.getData('text/plain');
            const player = document.getElementById(playerId);
            event.target.appendChild(player);
        }


// Aggiungi un ascoltatore per l'evento 'drop' su playersGrid
playersGrid.addEventListener('drop', (event) => {
    event.preventDefault();
    const playerId = event.dataTransfer.getData('text/plain');
    const player = document.getElementById(playerId);
    // Aggiunge l'elemento giocatore al players-grid invece che all'area di destinazione originale
    playersGrid.appendChild(player);
});

// Aggiungi un ascoltatore per l'evento 'dragover' su playersGrid
playersGrid.addEventListener('dragover', (event) => {
    event.preventDefault();
});

// Rileva il clic sul pulsante di salvataggio
const salvaButton = document.getElementById('salvaButton');
salvaButton.addEventListener('click', () => {
    // In questo punto, puoi scrivere il codice per salvare le modifiche
    // Esempio: Puoi inviare una richiesta AJAX per salvare i dati sul server

    // Aggiungi qui il tuo codice di salvataggio
    alert('Modifiche salvate con successo!'); // Esempio di messaggio di successo
});




// FORMAZIONI LINK

function changeColor(backgroundColor) {
    document.querySelectorAll('.option1').style.background = backgroundColor;
    document.body.style.background = backgroundColor;
}

function showPosition() {
    changeColor('red');
}