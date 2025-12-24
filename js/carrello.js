var budget = 100000000;
var cartCount = 0;
var spesaTotale = 0;

// Funzione per aggiornare il budget
function updateBudget(price) {
    budget -= price;
    if (budget < 0) {
        budget = 0; // Impediamo al budget di diventare negativo
    }
    document.getElementById('budget').textContent = formatCurrency(budget);
}

// Funzione per aggiornare la spesa totale
function updateSpesaTotale(price) {
    spesaTotale += price;
    document.getElementById('spesaTotale').textContent = formatCurrency(spesaTotale);
}

// Funzione per formattare il valore del budget in una stringa con il simbolo dell'euro
function formatCurrency(value) {
    return new Intl.NumberFormat('it-IT', { style: 'currency', currency: 'EUR' }).format(value);
}

// Aggiungi un gestore di eventi per i pulsanti "Acquista"
var purchaseButtons = document.querySelectorAll('.purchaseButton');
purchaseButtons.forEach(function (button) {
    button.addEventListener('click', function () {
        var row = button.closest('tr');
        var priceString = row.querySelector('td:nth-child(6)').textContent;
        var price = parseFloat(priceString.replace(/\./g, '').replace(',', '.')); // Converte la stringa in un numero

        if (budget >= price) {
            updateBudget(price); // Aggiorna il budget solo se c'è abbastanza denaro
            updateSpesaTotale(price); // Aggiorna la spesa totale
            cartCount++;
            document.getElementById('cartCount').textContent = cartCount; // Aggiorna il contatore del carrello
        } else {
            alert('Budget insufficiente per l\'acquisto');
        }
    });
});

// Aggiungi un gestore di eventi per i pulsanti "Rimuovi"
var removeButtons = document.querySelectorAll('.removeButton');
removeButtons.forEach(function (button) {
    button.addEventListener('click', function () {
        var row = button.closest('tr');
        var priceString = row.querySelector('td:nth-child(6)').textContent;
        var price = parseFloat(priceString.replace(/\./g, '').replace(',', '.')); // Converte la stringa in un numero

        if (cartCount > 0) {
            updateBudget(-price); // Rimuovi il prezzo dal budget
            cartCount--;
            document.getElementById('cartCount').textContent = cartCount; // Aggiorna il contatore del carrello
            updateSpesaTotale(-price); // Aggiorna la spesa totale
        } else {
            alert('Il carrello è vuoto. Impossibile rimuovere altri elementi.');
        }
    });
});

// Inizializza il budget e la spesa totale nella pagina
document.getElementById('budget').textContent = formatCurrency(budget);
document.getElementById('spesaTotale').textContent = formatCurrency(spesaTotale);